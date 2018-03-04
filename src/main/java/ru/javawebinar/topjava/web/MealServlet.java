package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.MealDao;
import ru.javawebinar.topjava.dao.MealDaoMemoryImpl;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealInit;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {

    private static final Logger log = getLogger(MealServlet.class);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
    private MealDao mealDao = new MealDaoMemoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");//Решение проблемы с кириллицей

        LocalDateTime dateTime = LocalDateTime.of(
                LocalDateTime.now().getYear(),
                LocalDateTime.now().getMonth(),
                LocalDateTime.now().getDayOfMonth(),
                LocalDateTime.now().getHour(),
                LocalDateTime.now().getMinute());
        if (req.getParameter("date_meal") != null && !req.getParameter("date_meal").isEmpty()) {
            dateTime = LocalDateTime.parse(req.getParameter("date_meal"), formatter);
        }

        String description = req.getParameter("desc_meal");

        int calories = 0;
        if (req.getParameter("cals_meal") != null && !req.getParameter("cals_meal").isEmpty()) {
            calories = Integer.parseInt(req.getParameter("cals_meal"));
        }

        Meal meal = new Meal(dateTime, description, calories);

        //Если айдишник не пустой - редактируем
        if (req.getParameter("id_meal") != null && !req.getParameter("id_meal").isEmpty()) {
            meal.setId(Integer.parseInt(req.getParameter("id_meal")));
            mealDao.update(meal);
            resp.sendRedirect("meals");
        }
        //Иначе - добавление нового
        else {
            mealDao.add(meal);
            resp.sendRedirect("meals");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");//Решение проблемы с кириллицей

        //удаление
        if (request.getParameter("delete") != null && Boolean.valueOf(request.getParameter("delete"))) {
            mealDao.delete(Integer.parseInt(request.getParameter("id")));
            response.sendRedirect("meals");
            return;
        }

        //редактирование
        if (request.getParameter("edit") != null && Boolean.valueOf(request.getParameter("edit"))) {
            Meal editedMeal = mealDao.findById(Integer.parseInt(request.getParameter("id")));
            request.setAttribute("edited_id", editedMeal.getId());
            request.setAttribute("edited_description", editedMeal.getDescription());
            request.setAttribute("edited_dateTime", editedMeal.getDateTime());
            request.setAttribute("edited_calories", editedMeal.getCalories());
            request.setAttribute("editedMeal", true);
        }

        //Получаем список еды со значением exceed, без фильтрации
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceededInOnePass2(new MealInit().getMealList(), LocalTime.MIN, LocalTime.MAX, 2000);

        //Кладем список в запрос
        request.setAttribute("mylist", mealWithExceeds);

        log.debug("forwarding to meals");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
        //response.sendRedirect("meals");
    }
}
