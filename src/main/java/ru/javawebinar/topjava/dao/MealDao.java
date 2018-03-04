package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    Meal findById(int id);

    List<Meal> findAll();

    void add(Meal meal);

    void update(Meal meal);

    void delete(int id);
}
