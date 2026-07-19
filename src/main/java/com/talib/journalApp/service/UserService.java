package com.talib.journalApp.service;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.JournalEntryRepository;
import com.talib.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {//here we write our business logit

    @Autowired//Dependency Inject Through Field
    private UserRepository userRepository;


    //method to save entry and post Mapping
    public void saveEntry (User user){

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
