package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.List;

public class UsersUtil {

    public static final List<User> USERS = Arrays.asList(
            new User(1, "Pavel", "Paz1506@gmail.com", "123456", Role.ROLE_ADMIN),
            new User(2, "Ivan", "Ivan@gmail.com", "123456", Role.ROLE_USER),
            new User(3, "Viktor", "Viktor@gmail.com", "123456", Role.ROLE_USER)

    );
}
