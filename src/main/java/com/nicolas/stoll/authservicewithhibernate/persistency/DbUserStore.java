package com.nicolas.stoll.authservicewithhibernate.persistency;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component("sqlstore")
public class DbUserStore implements IDao<User, String>{

    @Autowired
    private UserRepo userRepo;

    @Override
    public Optional<User> get(String value) {
        return this.userRepo.findById(value);
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public boolean save(User user) {

        System.out.println(user);
        this.userRepo.save(user);
        return this.userRepo.findById(user.getEmail()).isPresent();
    }

    @Override
    public void update(User old, User updated) {
        this.userRepo.save(updated);
    }

    @Override
    public void delete(User user) {
        this.userRepo.delete(user);
    }
}
