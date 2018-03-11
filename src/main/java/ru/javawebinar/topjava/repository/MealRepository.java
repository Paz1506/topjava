package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;

public interface MealRepository {

    Meal save(Meal meal, int userId);

    void delete(int id, int userId);

    Meal get(int id, int userId);

    Collection<Meal> getAll(int userId);

    Collection<Meal> getAllOfFilter(LocalDate beginDate, LocalDate endDate, LocalTime beginTime, LocalTime endTime, int userId);
}
