package com.nicolas.stoll.authservice.persistency;

import java.util.List;
import java.util.Optional;

public interface IDao<T, ID> {

    Optional<T> get(String value);
    List<T> getAll();
    boolean save(T t);
    void update(T old, T updated);
    void delete(T t);
}
