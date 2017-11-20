package com.iotrack.loginapi.controller;


import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import com.iotrack.loginapi.config.SecurityUtility;
import com.iotrack.loginapi.domain.Role;
import com.iotrack.loginapi.domain.User;
import com.iotrack.loginapi.domain.UserRole;
import com.iotrack.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/user")
public class RegisterController{

    @Autowired
    private UserService userService;



    @RequestMapping(value="/register" , method=RequestMethod.POST)
    public ResponseEntity registerUser(HttpServletRequest request , @RequestBody User user) throws Exception{

        String username=user.getUsername();
        String email= user.getEmail();

        if(userService.findByUsername(username)!=null){
            return new ResponseEntity("usernameExists",HttpStatus.BAD_REQUEST);
        }

        if(userService.findByEmail(email)!=null){
            return new ResponseEntity("emailExists",HttpStatus.BAD_REQUEST);
        }



        String encryptedPassword= SecurityUtility.passwordEncoder().encode(user.getPassword());


        user.setPassword(encryptedPassword);



        Role role=new Role();

        role.setRoleId(1);
        role.setName("ROLE_USER");
        Set<UserRole> userRoles=new HashSet<>();
        userRoles.add(new UserRole(user,role));

        userService.createUser(user, userRoles);

        return new ResponseEntity("User added susccessfully",HttpStatus.OK);

    }

}

