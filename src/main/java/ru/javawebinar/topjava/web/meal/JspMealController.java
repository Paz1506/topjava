package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;
import static ru.javawebinar.topjava.util.Util.orElse;

@Controller
public class JspMealController {

    @Autowired
    protected MealService mealService;

    @GetMapping("/meals/update/{id}")
    public String updateMeal(@PathVariable(value = "id", required = false) Integer id, Model model) {
        model.addAttribute("meal", mealService.getWithUser(id, AuthorizedUser.id()));
        return "mealForm";
    }

    @GetMapping("/meals/add")
    public String addMeal(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/meals/update")
    public String updateMeal(
            @RequestParam(value = "id", required = false) Integer id,
            @RequestParam("dateTime") String dateTime,
            @RequestParam("description") String description,
            @RequestParam("calories") Integer calories
    ) {
        Meal meal = new Meal(LocalDateTime.parse(dateTime), description, calories);
        if (id == null) {
            mealService.create(meal, AuthorizedUser.id());
        } else {
            meal.setId(id);
            mealService.update(meal, AuthorizedUser.id());
        }
        return "redirect:/meals";
    }

    @GetMapping("/meals/filter")
    public String filterMeal(@RequestParam(value = "startDate", required = false) String startDate,
                             @RequestParam(value = "endDate", required = false) String endDate,
                             @RequestParam(value = "startTime", required = false) String startTime,
                             @RequestParam(value = "endTime", required = false) String endTime, Model model) {
        LocalDate ld_startDate = parseLocalDate(startDate);
        LocalDate ld_endDate = parseLocalDate(endDate);
        LocalTime lt_startTime = parseLocalTime(startTime);
        LocalTime lt_endTime = parseLocalTime(endTime);

        List<Meal> mealsDateFiltered = mealService.getBetweenDates(orElse(ld_startDate, DateTimeUtil.MIN_DATE), orElse(ld_endDate, DateTimeUtil.MAX_DATE), AuthorizedUser.id());
        model.addAttribute("meals", MealsUtil.getFilteredWithExceeded(mealsDateFiltered, AuthorizedUser.getCaloriesPerDay(), orElse(lt_startTime, LocalTime.MIN), orElse(lt_endTime, LocalTime.MAX)));

        return "/meals";
    }

    @GetMapping("/meals/delete/{id}")
    public String deleteMeal(@PathVariable("id") Integer id) {
        mealService.delete(id, AuthorizedUser.id());
        return "redirect:/meals";
    }

    /*@PostMapping("/meals/between")
    public String mealBetween(HttpServletRequest request) throws UnsupportedEncodingException {
        request.setCharacterEncoding("UTF-8");
        LocalDate startDate = parseLocalDate(request.getParameter("startDate"));
        LocalDate endDate = parseLocalDate(request.getParameter("endDate"));
        LocalTime startTime = parseLocalTime(request.getParameter("startTime"));
        LocalTime endTime = parseLocalTime(request.getParameter("endTime"));
        request.setAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "redirect:meals";
    }*/

}
