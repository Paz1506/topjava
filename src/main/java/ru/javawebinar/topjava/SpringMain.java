package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 Automatic resource management
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));

            //HW02
            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            //System.out.println("Вся коллекция: " + mealRestController.getAll().size());

            //NPE TESTS
            //System.out.println("GET NPE: " + mealRestController.get(10));

            //System.out.println("UPDATE NPE: " + mealRestController.save(new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500,1),10));

            //System.out.println("DELETE NPE: "); mealRestController.delete(10);

            //TEST ALL
            //System.out.println(mealRestController.getAll());

            //TEST id=3 - не принадлежит к User 1
            //System.out.println(mealRestController.get(3));

            //2.3 пробуем изменить чужую еду
            //Meal newMeal = new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "TESST", 7500,AuthorizedUser.id());
            //newMeal.setId(3);
            //mealRestController.save(newMeal);
            //System.out.println(mealRestController.get(newMeal.getId()));
            //System.out.println("Вся коллекция: " + mealRestController.getAll().size());
        }
    }
}
