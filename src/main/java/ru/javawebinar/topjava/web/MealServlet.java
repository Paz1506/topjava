package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealInit;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalTime;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Получаем список еды со значением exceed, без фильтрации
        List<MealWithExceed> mealWithExceeds = MealsUtil.getFilteredWithExceeded(new MealInit().getMealList(), LocalTime.MIN, LocalTime.MAX, 2000);

        //Кладем список в запрос
        request.setAttribute("mylist", mealWithExceeds);

        log.debug("forwarding to meals");
        request.getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
