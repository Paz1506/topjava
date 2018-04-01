package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.DATAJPA})
public class MealServiceTest extends AbstractMealServiceTest {

    //Optional 7.2
    @Test
    public void getByIdWithUser() {
        Meal test = super.repository.getByIdWithUser(MealTestData.MEAL1_ID);
        Meal userMeal = MealTestData.MEAL1;
        userMeal.setUser(UserTestData.USER);
        MealTestData.assertMatchWithUser(userMeal, test);
    }
}