package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2016-11-24.
 */
@RestController
@EnableZuulProxy
public class LgdbController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/user")
    public Principal user(Principal user) {
        return user;
    }


    @RequestMapping("/hello")
    public Map<String,Object> home() {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @PostMapping("/addUser")
    public void newUser(@RequestBody User user) {

        System.out.println(user.getUsername() + " " + user.getPassword());

        try {
            userRepository.saveUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




}
