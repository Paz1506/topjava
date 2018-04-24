package ru.javawebinar.topjava.web.meal;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.TestUtil;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;

public class MealRestControllerTest extends AbstractControllerTest {

    @Test
    public void testGet() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(MealRestController.REST_URL+"/100002"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(MEAL1))
        );
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(MealRestController.REST_URL+"/100002"))
                .andExpect(status().is2xxSuccessful());
        assertMatch(mealService.getAll(AuthorizedUser.id()),MEAL6,MEAL5,MEAL4,MEAL3,MEAL2);

    }

    @Test
    public void testGetAll() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(MealRestController.REST_URL))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(MEALS))
        );
    }

    @Test
    @Transactional
    public void testCreate() throws Exception {
        Meal testMeal = MealTestData.getCreated();
        //mealService.create(testMeal, AuthorizedUser.id());
        mockMvc.perform(put(MealRestController.REST_URL+"/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(testMeal)))
                .andDo(print())
                .andExpect(status().isOk());

        List<Meal> curList = new ArrayList<>();
        testMeal.setId(100010);
        curList.add(testMeal);
        curList.addAll(MEALS);
        assertMatch(mealService.getAll(AuthorizedUser.id()),curList);

    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        Meal testMeal = new Meal(MEAL1_ID, of(2015, Month.MAY, 30, 10, 0), "newTitle", 500);
        //mealService.create(testMeal, AuthorizedUser.id());
        mockMvc.perform(put(MealRestController.REST_URL+"/update/100002")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(testMeal)))
                .andDo(print())
                .andExpect(status().isOk());

        assertMatch(mealService.get(100002, AuthorizedUser.id()),testMeal);

    }

    @Test
    public void testGetBetween() throws Exception {
        TestUtil.print(
                mockMvc.perform(get(MealRestController.REST_URL+"/between?startDateTime=2014-05-29T20:00:00&endDateTime=2015-05-31T20:00:00"))
                        .andExpect(status().isOk())
                        .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                        .andExpect(contentJson(MEAL3,MEAL6))
        );
    }


}
