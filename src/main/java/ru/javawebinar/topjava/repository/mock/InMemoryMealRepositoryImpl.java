package ru.javawebinar.topjava.repository.mock;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepositoryImpl implements MealRepository {
    private Map<Integer, Meal> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        for (Meal meal : MealsUtil.MEALS) this.save(meal, 1);
    }

    @Override
    public Meal save(Meal meal, int userId) {

        //Если еда новая
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.put(meal.getId(), meal);
            return meal;
        }

        //Иначе, если обновляем не свою
        if (repository.get(meal.getId()).getUserId() != userId) {
            throw new NotFoundException("Еда не существует или не принадлежит данному пользователю");
        }

        // treat case: update, but absent in storage
        return repository.computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public void delete(int id, int userId) {

        if (repository.get(id).getUserId() != userId) {
            throw new NotFoundException("Еда не существует или не принадлежит данному пользователю");
        }

        repository.remove(id);
    }

    @Override
    public Meal get(int id, int userId) {

        if (!repository.containsKey(id) || repository.get(id).getUserId() != userId) {
            throw new NotFoundException("Еда не существует или не принадлежит данному пользователю");
        }

        return repository.get(id);
    }

    @Override
    public Collection<Meal> getAll(int userId) {
        return repository.values().stream().filter((m) -> m.getUserId() == userId).sorted(Comparator.comparing(Meal::getDateTime, Comparator.reverseOrder())).collect(Collectors.toList());
    }


    @Override
    public Collection<Meal> getAllOfFilter(LocalDate beginDate, LocalDate endDate, LocalTime beginTime, LocalTime endTime, int userId) {
        return getAll(userId).stream()
                .filter((m2) -> DateTimeUtil.isBetweenDate(m2.getDate(), beginDate, endDate))
                .filter((m3) -> DateTimeUtil.isBetween(m3.getTime(), beginTime, endTime))
                .collect(Collectors.toList());
    }
}

