package com.nicolas.stoll.authservicewithhibernate.persistency;

import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User, String> {
}
