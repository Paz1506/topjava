package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {

    public static final int FIRST_MEAL_ID = START_SEQ + 2;
    public static final int SECOND_MEAL_ID = START_SEQ + 3;
    public static final int THRID_MEAL_ID = START_SEQ + 4;
    public static final int FOURTH_MEAL_ID = START_SEQ + 5;
    public static final int FIFTH_MEAL_ID = START_SEQ + 6;
    public static final int SIXTH_MEAL_ID = START_SEQ + 7;

    public static final Meal firstMeal = new Meal(FIRST_MEAL_ID, LocalDateTime.of(2016, 3, 18, 12, 8, 22), "First meal", 800);
    public static final Meal secondMeal = new Meal(SECOND_MEAL_ID, LocalDateTime.of(2016, 3, 20, 17, 15, 22), "Second meal", 300);
    public static final Meal thridMeal = new Meal(THRID_MEAL_ID, LocalDateTime.of(2012, 6, 20, 17, 30, 22), "Thrid meal", 700);
    public static final Meal fourthMeal = new Meal(FOURTH_MEAL_ID, LocalDateTime.of(2017, 12, 22, 17, 30, 22), "Fourth meal", 1500);
    public static final Meal fifthMeal = new Meal(FIFTH_MEAL_ID, LocalDateTime.of(2017, 6, 20, 9, 30, 22), "Fifth meal", 500);
    public static final Meal sixthMeal = new Meal(SIXTH_MEAL_ID, LocalDateTime.of(2018, 8, 20, 17, 30, 22), "Sixth meal", 100);

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
