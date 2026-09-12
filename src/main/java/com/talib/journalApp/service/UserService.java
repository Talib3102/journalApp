package com.talib.journalApp.service;

import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService {//here we write our business logic


    @Autowired//Dependency Inject Through Field
    private UserRepository userRepository;
    private static final PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    //private static final Logger logger= LoggerFactory.getLogger(UserService.class);


    //method to save entry and post Mapping
    public void saveEntry (User user){
        userRepository.save(user);

    }
    //I make it boolean for testing othervise it is void and keep it in try catch
    public boolean saveNewUser (User user){
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("user"));
            userRepository.save(user);
            return true;
        }catch (Exception e){
            log.info("gskhjhavbhjbah");
            log.debug("hbchj");
            log.error("Error Occur for {} ",user.getUserName(),e);
            return false;
        }

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
        return userRepository.findByUserName(username);
    }
}
