package com.iotrack.loginapi.service;

import com.iotrack.loginapi.domain.User;
import com.iotrack.loginapi.domain.UserRole;

import java.util.Set;

public interface UserService {


    User createUser(User user , Set<UserRole> userRole);
    User findByUsername(String username);
    User findByEmail(String email);
    User findOne(Long id);

}
