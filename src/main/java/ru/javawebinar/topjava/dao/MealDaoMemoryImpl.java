package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealInit;

import java.util.List;

public class MealDaoMemoryImpl implements MealDao {
    @Override
    public Meal findById(int id) {
        for (Meal meal : MealInit.getMealList()) {
            if (meal.getId() == id) {
                return meal;
            }
        }
        return null;
    }

    @Override
    public List<Meal> findAll() {
        return MealInit.getMealList();
    }

    @Override
    public void add(Meal meal) {
        MealInit.getMealList().add(meal);
        MealInit.getCountMealsAtomic().getAndIncrement();
    }

    @Override
    public void update(Meal meal) {
        delete(meal.getId());
        MealInit.getMealList().add(meal);
    }

    @Override
    public void delete(int id) {
        MealInit.getMealList().removeIf(next -> next.getId() == id);
    }
}
