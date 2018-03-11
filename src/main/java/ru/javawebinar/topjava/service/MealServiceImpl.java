package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Service
public class MealServiceImpl implements MealService {

    private final MealRepository repository;

    @Autowired
    public MealServiceImpl(MealRepository repository) {
        this.repository = repository;
    }

    @Override
    public Meal save(Meal meal, int userId) {
        return repository.save(meal, userId);
    }

    @Override
    public void delete(int id, int userId) {
        repository.delete(id, userId);
    }

    @Override
    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.getAll(userId);
    }

    public Collection<Meal> getAllOfFilter(LocalDate beginDate, LocalDate endDate, LocalTime beginTime, LocalTime endTime, int userId){
        return repository.getAllOfFilter(beginDate, endDate, beginTime, endTime, userId);
    }
}