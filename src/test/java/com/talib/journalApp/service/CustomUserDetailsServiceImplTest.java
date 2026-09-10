package com.talib.journalApp.service;

import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class CustomUserDetailsServiceImplTest {
    //@Autowired instead of autoeired we sue @InjectMocks
    @InjectMocks
    private CustomUserDetailsServiceImpl customUserDetailsService;


    @Mock
    //@MockBean//this mock repository is replace by actual repository
    private UserRepository userRepository;
    @BeforeEach
    void setUp(){
        //method to initialize mocks because we work out of the spring context
        MockitoAnnotations.initMocks(this);
    }
    @Test
    void loadUserByUsernameTest(){
        when(userRepository.findByUserName(ArgumentMatchers.anyString())).thenReturn(User.builder().userName("Suleman").password("abcd").roles(new ArrayList<>()).build());
        UserDetails user = customUserDetailsService.loadUserByUsername("Suleman");
        Assertions.assertNotNull(user);
    }
}
