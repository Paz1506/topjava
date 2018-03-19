package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;


    @Test
    public void get() throws Exception {
        //throws NFE - it's work:)
        //Meal testMeal = service.get(100004, 100001);
        Meal testMeal = service.get(100004, 100000);
        assertMatch(testMeal, thridMeal);
    }

    @Test
    public void delete() throws Exception {
        service.delete(100004, 100000);
        //Содержатся ли, после удаления, кроме третьей
        assertThat(service.getAll(100000)).containsExactlyInAnyOrder(firstMeal, secondMeal);
    }

    @Test
    public void getBetweenDates() throws Exception {
        assertMatch(service.getBetweenDates(LocalDate.of(2016, 3, 20), LocalDate.of(2016, 3, 25), 100000), secondMeal);
    }

    @Test
    public void getBetweenDateTimes() throws Exception {
        assertMatch(service.getBetweenDateTimes(LocalDateTime.of(2016, 3, 18, 12, 8, 22),
                LocalDateTime.of(2016, 3, 18, 12, 8, 25),100000),firstMeal);
    }

    @Test
    public void getAll() throws Exception {
        List<Meal> list = service.getAll(100000);
        assertThat(list).containsExactlyInAnyOrder(firstMeal, secondMeal, thridMeal);//Содержатся ли, без сорт.
    }

    @Test
    public void update() throws Exception {
        //throws NFE - it's work:)
        //Meal testMeal = new Meal(100005, LocalDateTime.of(2018, 8, 20, 17, 30, 22), "Test meal", 100);
        Meal testMeal = new Meal(null, LocalDateTime.of(2018, 8, 20, 17, 30, 22), "Test meal", 100);
        service.update(testMeal, 100000);
        assertMatch(service.get(testMeal.getId(), 100000), testMeal);
    }

    @Test
    public void create() throws Exception {
        Meal testMeal = new Meal(null, LocalDateTime.of(2018, 8, 20, 17, 30, 22), "Test meal", 100);
        Meal created = service.create(testMeal, 100000);
        testMeal.setId(created.getId());
        assertMatch(service.getAll(100000), testMeal, secondMeal, firstMeal, thridMeal);
    }

}