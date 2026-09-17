package com.talib.journalApp.controller;


import com.talib.journalApp.dto.UserDTO;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.service.UserDetailsServiceImpl;
import com.talib.journalApp.service.UserService;
import com.talib.journalApp.utills.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping("/public")
@Tag(name="Public APIs")
public class PublickController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;


    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")

    public String healthCheck(){
        return "ok";

    }


    @PostMapping("/signup")
    @Operation(summary = "Create a New user Account")
    public void signup(@RequestBody UserDTO userDTO) {
        User user = new User();
        user.setUserName(userDTO.getUserName());
        user.setPassword(userDTO.getPassword());
        user.setEmail(userDTO.getEmail());
        user.setSentimentAnalysis(userDTO.isSentimentAnalysis());
        userService.saveNewUser(user);
    }
    @PostMapping("/login")
    @Operation(summary = "Logging Existing account if it exists in DB")
    public ResponseEntity<String> login(@RequestBody UserDTO userDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDTO.getUserName(), userDTO.getPassword()));
            UserDetails userDetails = userDetailsService.loadUserByUsername(userDTO.getUserName());
            String jwt = jwtUtil.generateToken(userDetails.getUsername());
            return new ResponseEntity<>(jwt, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Exception occurred while createAuthenticationToken ", e);
            return new ResponseEntity<>("Incorrect username or password", HttpStatus.BAD_REQUEST);
        }
    }
}
