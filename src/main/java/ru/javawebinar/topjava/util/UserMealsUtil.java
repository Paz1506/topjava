package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * GKislin
 * 31.05.2015.
 */
public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
//        .toLocalDate();
//        .toLocalTime();
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        List<LocalDate> list_date_time_uniq = mealList.stream()
                .map((d)-> LocalDate.of(d.getDateTime().toLocalDate().getYear(), d.getDateTime().toLocalDate().getMonth(), d.getDateTime().toLocalDate().getDayOfMonth()))
                .distinct()
                .collect(Collectors.toList());

        Map<LocalDate, Integer> map_date_sum_calories = new HashMap<>();
        for (LocalDate localDate : list_date_time_uniq){
            map_date_sum_calories.put(localDate,mealList.stream().filter((d)->d.getDateTime().toLocalDate().isEqual(localDate)).mapToInt(UserMeal::getCalories).sum());
        }

        return mealList.stream()
                .filter((p) -> TimeUtil.isBetween(p.getDateTime().toLocalTime(), startTime, endTime))
                .map((m)-> new UserMealWithExceed(m.getDateTime(),m.getDescription(),m.getCalories(),map_date_sum_calories.get(m.getDateTime().toLocalDate())>caloriesPerDay))
                 .collect(Collectors.toList());
    }
}
