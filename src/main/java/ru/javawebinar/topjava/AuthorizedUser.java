package ru.javawebinar.topjava;

import static ru.javawebinar.topjava.util.MealsUtil.DEFAULT_CALORIES_PER_DAY;

public class AuthorizedUser {

    private static int authUserId = 1;

    public static int id() {
        return authUserId;
    }

    public static int getCaloriesPerDay() {
        return DEFAULT_CALORIES_PER_DAY;
    }

    public static void setAuthUserId(int authUserId) {
        AuthorizedUser.authUserId = authUserId;
    }
}