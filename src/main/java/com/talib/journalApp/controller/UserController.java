package com.talib.journalApp.controller;

import com.talib.journalApp.api.response.WeatherResponse;
import com.talib.journalApp.dto.UserDTO;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.UserRepository;
import com.talib.journalApp.service.UserService;
import com.talib.journalApp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")//now if i gave any endPoint to method the that endPoint is written after this endpoint basically this is use to apply mapping on whole class
@Tag(name="User APIs",description = "Read, Update & Delete User")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired//Dependency Inject Through Field
    private UserRepository userRepository;
    @Autowired
    private WeatherService weatherService;

    @PutMapping
    @Operation(summary ="Edit user credentials")
    public ResponseEntity<?> updateUser(@RequestBody UserDTO user) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);

        if (userInDb != null) {
            // 1. Update username if provided and not empty
            if (user.getUserName() != null && !user.getUserName().trim().isEmpty()) {
                userInDb.setUserName(user.getUserName());
            }

            // 2. Update email & sentiment analysis if provided
            if (user.getEmail() != null && !user.getEmail().trim().isEmpty()) {
                userInDb.setEmail(user.getEmail());
            }
            userInDb.setSentimentAnalysis(user.isSentimentAnalysis());

            // 3. Update password if provided
            if (user.getPassword() != null && !user.getPassword().trim().isEmpty()) {
                userInDb.setPassword(user.getPassword());
                // saveNewUser will encode the new password and save
                userService.saveNewUser(userInDb);
            } else {
                // If password is not changed, save directly without re-encoding
                userService.saveEntry(userInDb);
            }

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping
    @Operation(summary = "Delete User")
    public ResponseEntity<?> deleteById(){
        //when user became authenticat its credentials are store in security context folder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @Operation(summary = "Get single user details")
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
