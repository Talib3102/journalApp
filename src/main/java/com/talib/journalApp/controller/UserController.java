package com.talib.journalApp.controller;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.UserRepository;
import com.talib.journalApp.service.JournalEntryService;
import com.talib.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")//now if i gave any endPoint to method the that endPoint is written after this endpoint basically this is use to apply mapping on whole class

public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser(){
        return userService.getAll();
    }
    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
    @PutMapping("/{username}")
    public ResponseEntity<?> updateUser(@RequestBody User user, @PathVariable String username){
        User userInDb = userService.findByUsername(username);
        if (userInDb != null){
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
