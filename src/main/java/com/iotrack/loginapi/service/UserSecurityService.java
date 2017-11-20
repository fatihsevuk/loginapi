package com.iotrack.loginapi.service;

import com.iotrack.loginapi.domain.User;
import com.iotrack.loginapi.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserSecurityService implements UserDetailsService {

    private static final Logger LOG= LoggerFactory.getLogger(UserSecurityService.class);

    @Autowired
    private UserRepository userRepository;

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user=userRepository.findByUsername(username);

        if(null==user){
            LOG.warn("Username {} not found"+username);
            throw new UsernameNotFoundException("Username "+username);
        }

        return user;
    }*/


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user=userRepository.findByEmail(email);

        if(null==user){
            LOG.warn("Email {} not found"+email);
            throw new UsernameNotFoundException("Username "+email);
        }

        return user;
    }




}
