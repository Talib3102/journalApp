//package com.talib.journalApp.service;
//
//import com.talib.journalApp.entity.User;
//import com.talib.journalApp.repository.UserRepository;
//import org.junit.jupiter.api.Disabled;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class UserServiceTest {
//    @Autowired
//    private UserRepository userRepository;
//    @Autowired
//    private  UserService userService;
//
//    @ParameterizedTest
//    @ArgumentsSource(UserArgumentProvider.class)
//    public void testArgument(User user){
//        assertTrue(userService.saveNewUser(user));
//    }
////    @Disabled
//    //@Test
//    @ParameterizedTest
////    @CsvSourc
//    //@EnumSource
//      @ValueSource(strings = {
//            "Talib",
//            "Sufiyan",
//            "Sakina"
//    })
//    public void testFindByUserName(String name){
//        assertNotNull(userRepository.findByUsername(name));
//        User user = userRepository.findByUsername("Sufiyan");
//        assertTrue(!user.getJournalEntries().isEmpty());
//        assertNotNull(userRepository.findByUsername("Sufiyan"));
//    }
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,2,4",
//            "3,4,7"
//    })
//
//
//    public void test(int a,int b,int expected){
//        assertEquals(expected,a+b);
//    }
//}
