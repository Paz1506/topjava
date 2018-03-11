package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;

@Controller
public class MealRestController {

    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService service;

    public Meal save(Meal meal){
        log.info("update/create meal {}", meal);
        return service.save(meal, AuthorizedUser.id());
    }

    public void delete(int id){
        log.info("delete meal {}", id);
        if (service.get(id, AuthorizedUser.id())!=null) {
            service.delete(id, AuthorizedUser.id());
        }
    }

    public Meal get(int id){
        log.info("get meal {}", id);
        return service.get(id, AuthorizedUser.id());
    }

    public Collection<Meal> getAll(){
        log.info("getAll(Meal)");
        return service.getAll(AuthorizedUser.id());
    }

    public Collection<Meal> getAllOfFilter(String beginDate, String endDate, String beginTime, String endTime){

        log.info("getAllOfFilter(Meal)");

        DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        DateTimeFormatter formatterTime = DateTimeFormatter.ofPattern("HH:mm");

        LocalDate ldBeginDate = LocalDate.MIN;
        LocalDate ldEndDate = LocalDate.MAX;
        LocalTime ltBeginTime = LocalTime.MIN;
        LocalTime ltEndTime = LocalTime.MAX;

        if (!(beginDate==null) && !beginDate.isEmpty()){
            ldBeginDate = LocalDate.parse(beginDate, formatterDate);
        }

        if (!(endDate==null) && !endDate.isEmpty()){
            ldEndDate = LocalDate.parse(endDate, formatterDate);
        }

        if (!(beginTime==null) && !beginTime.isEmpty()){
            ltBeginTime = LocalTime.parse(beginTime, formatterTime);
        }

        if (!(endTime==null) && !endTime.isEmpty()){
            ltEndTime = LocalTime.parse(endTime, formatterTime);
        }
        return service.getAllOfFilter(ldBeginDate, ldEndDate, ltBeginTime, ltEndTime, AuthorizedUser.id());
    }
}