package com.spring.rest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.spring.rest.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

}
