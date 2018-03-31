package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    private static final Sort SORT_DESC = new Sort(Sort.Direction.DESC, "id");

    @Autowired
    private CrudMealRepository crudRepository;

    @Autowired
    private CrudUserRepository crudUserRepository;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (!meal.isNew() && get(meal.getId(), userId) == null) {
            return null;
        }
        meal.setUser(crudUserRepository.getOne(userId));
        return crudRepository.save(meal);
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id, userId) != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        //Meal meal = crudRepository.getOne(id);//Аналог em.getReference in JpaMealRepository//Not working
        //if (meal.getUser().getId()==userId) return crudRepository.findById(id).orElse(null);//Not working
        //return meal != null && meal.getUser().getId() == userId ? meal : null; //Not working
        return crudRepository.findById(id, userId).orElse(null);
        /*Meal meal = crudRepository.getOne(id);
        Hibernate.initialize(meal);
        return meal.getUser().getId() == userId ? meal : null;*/
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.findAll(userId, SORT_DESC);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getBetween(startDate, endDate, userId);
    }
}
