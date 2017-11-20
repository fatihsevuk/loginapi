package com.iotrack.loginapi.repository;


import java.util.List;

import com.iotrack.loginapi.domain.User;
import org.springframework.data.repository.CrudRepository;



public interface UserRepository extends CrudRepository<User, Long> {

    User findByUsername(String username);

    User findByEmail(String email);

    List<User> findAll();


}
