package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger log = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    //init user repo of non-static block
    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        log.info("delete {}", id);
        return repository.remove(id) != null;
    }

    @Override
    public User save(User user) {
        log.info("save {}", user);

        if (user.isNew()){
            user.setId(counter.incrementAndGet());
            repository.put(user.getId(), user);
            return user;
        }
        //инчае обновляем - только если значение присутствует (без лишних проверок)
        else {
            repository.put(user.getId(), user);
            return user;
        }
    }

    @Override
    public User get(int id) {
        log.info("get {}", id);
        if (repository.get(id)!= null){
            return repository.get(id);
        }
        return null;
    }

    @Override
    public List<User> getAll() {
        log.info("getAll");
        return new ArrayList<User>(repository.values()).stream().sorted(Comparator.comparing(User::getName)).collect(Collectors.toList());
    }

    @Override
    public User getByEmail(String email) {
        log.info("getByEmail {}", email);
        return new ArrayList<User>(repository.values()).stream().filter((u)->u.getEmail().equals(email)).findFirst().get();
    }
}
