package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

//Инициализация списка еды для HW01 + счетчик
public class MealInit {

    private static List<Meal> mealList = new CopyOnWriteArrayList<Meal>();//потокобезопасно
    private static AtomicInteger countMeals = new AtomicInteger(1);

    public static List<Meal> getMealList() {
        return mealList;
    }

    public static int getCountMeals() {
        return countMeals.get();
    }

    public static AtomicInteger getCountMealsAtomic() {
        return countMeals;
    }

    static {
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500));
        countMeals.getAndIncrement();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000));
        countMeals.getAndIncrement();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500));
        countMeals.getAndIncrement();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000));
        countMeals.getAndIncrement();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500));
        countMeals.getAndIncrement();
        mealList.add(new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510));
        countMeals.getAndIncrement();
    }
}
