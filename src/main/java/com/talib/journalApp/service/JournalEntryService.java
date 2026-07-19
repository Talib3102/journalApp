package com.talib.journalApp.service;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.JournalEntryRepository;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
@Slf4j
public class JournalEntryService {//here we write our business logit
    @Autowired
    private UserService userService;

    @Autowired//Dependency Inject Through Field
    private JournalEntryRepository JournalEntryRepository;


    //method to save entry and post Mapping
    public void saveEntry (JournalEntry JournalEntry,String userName) {
        User user = userService.findByUsername(userName);
        JournalEntry.setDate(LocalDateTime.now());
        JournalEntry saved = JournalEntryRepository.save(JournalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }

    public void saveEntry (JournalEntry JournalEntry) {
        JournalEntryRepository.save(JournalEntry);
    }

    //method for get Mapping
    //this methhod return the list of journal entry
    public List<JournalEntry> getAll(){
        return JournalEntryRepository.findAll();
    }

    //method for getMappinby Id
    //optional : it is like a box in which data will be present or not
    public Optional<JournalEntry> findById(ObjectId id){
        return JournalEntryRepository.findById(id);
    }

    //method for delete mapping
    public void  deleteById(ObjectId id, String userName){
        User user = userService.findByUsername(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        JournalEntryRepository.deleteById(id);

    }


}
