package com.iotrack.loginapi.service.impl;

import com.iotrack.loginapi.domain.User;
import com.iotrack.loginapi.domain.UserRole;
import com.iotrack.loginapi.repository.RoleRepository;
import com.iotrack.loginapi.repository.UserRepository;
import com.iotrack.loginapi.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService{

    private static final Logger LOG= LoggerFactory.getLogger(UserServiceImpl.class);


    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Transactional
    public User createUser(User user, Set<UserRole> userRoles) {

        User localUser=userRepository.findByUsername(user.getUsername());

        if (localUser!=null) {
            LOG.info("User with username {} already exist.", user.getUsername());

        }else{
            for (UserRole ur : userRoles) {
                roleRepository.save(ur.getRole());
            }
            user.getUserRoles().addAll(userRoles);

            localUser=userRepository.save(user);
        }

        return localUser;
    }

    @Override
    public User findByUsername(String username) {
        // TODO Auto-generated method stub
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) {

        return userRepository.findByEmail(email);
    }

    @Override
    public User findOne(Long id) {
        // TODO Auto-generated method stub
        return userRepository.findOne(id);
    }

}
