package com.talib.journalApp.controller;


import com.talib.journalApp.entity.User;
import com.talib.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublickController {
    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck(){
        return "ok";

    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user){
        //userService.saveEntry(user);
        userService.saveNewUser(user);
    }
//    @GetMapping
//    public List<User> getAllUser(){
//        return userService.getAll();
//    }
}
