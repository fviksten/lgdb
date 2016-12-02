package com.lgdb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.sql.SQLException;
import java.util.Collections;
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

    @RequestMapping("/getUsername")
    public Map<String,Object> sendUsername(Principal user) {
        Map<String,Object> model = new HashMap<String,Object>();
        model.put("username", user.getName());
        System.out.println("Ojojoj: " + user.getName());
        return model;
    }

    @RequestMapping(value = "/addUser", method = RequestMethod.POST, produces = "application/json")
    public Object newUser(@RequestBody User user) {
        System.out.println(user.getUsername() + " " + user.getPassword());
        if(userRepository.userExists(user)){
            Object status = Collections.singletonMap("response", "ERROR");
            return status;
        }else{
            //adduser.
            try {
                userRepository.saveUser(user);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Object status = Collections.singletonMap("response", "OK");
            return status;
        }
    }




}
