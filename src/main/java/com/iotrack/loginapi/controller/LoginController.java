package com.iotrack.loginapi.controller;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.iotrack.loginapi.domain.User;
import com.iotrack.loginapi.domain.UserRole;
import com.iotrack.loginapi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class LoginController{


    @Autowired
    private UserService userService;

    @RequestMapping("/token")
    public Map<String , String> token(HttpSession session , HttpServletRequest request , Principal principal){

        System.out.println(request.getRemoteHost());

        User u =this.userService.findByUsername(principal.getName());

        Set<UserRole> userRoles=u.getUserRoles();

        Iterator<UserRole> iterator = userRoles.iterator();


        while(iterator.hasNext()){
            System.out.println(iterator.next().getRole().getName());
        }


        System.out.println();

        String remoteHost=request.getRemoteHost();
        int portNumber=request.getRemotePort();

        System.out.println(remoteHost+" "+portNumber);
        System.out.println(request.getRemoteAddr());

        return Collections.singletonMap("token", session.getId());
    }






    @RequestMapping("/checkSession")
    public ResponseEntity checkSession(){
        return new ResponseEntity("Session Active!",HttpStatus.OK);
    }

    @RequestMapping(value="/user/logout" , method=RequestMethod.POST)
    public ResponseEntity logout(){
        System.out.println("logout worked");
        SecurityContextHolder.clearContext();
        return new ResponseEntity("Logout Successfully!" , HttpStatus.OK);
    }


}

