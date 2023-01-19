package com.nicolas.stoll.authservice.persistency;

import com.nicolas.stoll.authservice.services.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component("inmemory")
public class InMemoryUserStore implements IDao<User, String> {

    @Autowired PasswordService passwordService;
    private final Map<String, User> users = new HashMap<>();
    @Override
    public Optional<User> get(String value) {
        if(this.users.containsKey(value)) {

            return Optional.of(this.users.get(value));
        }

        return Optional.empty();
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User userModel) {
        if(this.users.containsKey(userModel.getEmail())) {
            return false;
        }

        this.users.put(userModel.getEmail(), userModel);
        return true;
    }

    @Override
    public void update(User old, User updated) {
        //TODO implement update to update userPassword
    }

    @Override
    public void delete(User userModel) {
        this.users.remove(userModel.getEmail());
    }
}
