package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id and m.user.id=:user_id"),
        @NamedQuery(name = Meal.BETWEEN, query = "SELECT m FROM Meal m LEFT JOIN FETCH m.user WHERE m.user.id=:user_id AND m.dateTime BETWEEN :startDate AND :endDate ORDER BY m.dateTime DESC "),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m LEFT JOIN FETCH m.user WHERE m.user.id =:user_id ORDER BY m.dateTime desc "),
})
@Entity
@Table(name = "meals")
public class Meal extends AbstractBaseEntity {

    public static final String DELETE = "Meal.delete";
    public static final String BETWEEN = "Meal.getBetween";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    @NotNull
    private LocalDateTime dateTime;

    @Column(name = "description", nullable = true)
    @Size(max = 1024)
    private String description;

    @Column(name = "calories", columnDefinition = "int default 2000")
    @Range(min = 10, max = 10000)
    private int calories;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
