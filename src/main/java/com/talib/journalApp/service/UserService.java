package com.talib.journalApp.service;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.JournalEntryRepository;
import com.talib.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {//here we write our business logic


    @Autowired//Dependency Inject Through Field
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();


    //method to save entry and post Mapping
    public void saveEntry (User user){
        userRepository.save(user);

    }
    public void saveNewUser (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("user"));
        userRepository.save(user);
    }
    public void saveAdmin (User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("user","ADMIN"));
        userRepository.save(user);
    }



    //method for get Mapping
    //this methhod return the list of journal entry
    public List<User> getAll(){
        return userRepository.findAll();
    }

    //method for getMappinby Id
    //optional : it is like a box in which data will be present or not
    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    //method for delete mapping
    public void  deleteById(ObjectId id){
        userRepository.deleteById(id);
    }
    public User findByUsername(String username){
        return userRepository.findByUsername(username);
    }
}
