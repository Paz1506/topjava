package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.Profiles;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;

@ActiveProfiles({Profiles.POSTGRES_DB,Profiles.DATAJPA})
public class UserServiceTest extends AbstractUserServiceTest {

    //Optional 7.1
    @Test
    public void getByIdWithMeals(){
        User test = super.repository.getByIdWithMeals(UserTestData.ADMIN_ID);
        User adminTest = UserTestData.ADMIN;
        adminTest.setMeals(Arrays.asList(MealTestData.ADMIN_MEAL1,MealTestData.ADMIN_MEAL2));
        UserTestData.assertMatch(adminTest, test);
    }

}