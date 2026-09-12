package com.talib.journalApp.controller;

import com.talib.journalApp.api.response.WeatherResponse;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.UserRepository;
import com.talib.journalApp.service.UserService;
import com.talib.journalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")//now if i gave any endPoint to method the that endPoint is written after this endpoint basically this is use to apply mapping on whole class

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired//Dependency Inject Through Field
    private UserRepository userRepository;
    @Autowired
    private WeatherService weatherService;

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        //when user became authenticat its credentials are store in security context folder how we fetch the data from security context
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @DeleteMapping
    public ResponseEntity<?> deleteById(){
        //when user became authenticat its credentials are store in security context folder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting(){
        //when user became authenticat its credentials are store in security context folder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Mumbai");
        String greeting="";
        if(weatherResponse != null){
            greeting=", Weather feels like "+ weatherResponse.getMain().getFeelsLike();
        }
        return new ResponseEntity<>("Hi "+authentication.getName()+greeting,HttpStatus.OK);
    }


}
