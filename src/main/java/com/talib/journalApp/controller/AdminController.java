package com.talib.journalApp.controller;

import com.talib.journalApp.cache.AppCache;
import com.talib.journalApp.dto.UserDTO;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@Tag(name="Admin APIs")
public class AdminController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppCache appCache;


    
    @GetMapping("/all-user")
    @Operation(summary = "Get all users and their Detail by ADMIN Only")
    public ResponseEntity<?> getAllUser(){
        List<User> all = userService.getAll();
        if (all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create-admin-user")
    @Operation(summary = "Create an new Admin Through existing Admin")
    public void createUser(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        userService.saveAdmin(user);
    }

    @GetMapping("clear-app-cache")
    @Operation(summary = "This was build for Test case")
    public void clearAppCache(){
        appCache.init();//to initialize old api key and avoid duplication of key
    }
}
