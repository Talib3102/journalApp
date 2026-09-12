package com.talib.journalApp.service;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.repository.JournalEntryRepository;

import org.bson.types.ObjectId;
import org.slf4j.Logger;//we can use Ligback using slf4j.simple logging fassad for java is a fullform of slf4j
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class JournalEntryService {//here we write our business logit
    @Autowired
    private UserService userService;

    @Autowired//Dependency Inject Through Field
    private JournalEntryRepository JournalEntryRepository;




    //method to save entry and post Mapping
    // @Transactional // if anything is crash in this method then all the thing gonna rollback because lin 34 gonna crash so we can rollback all transection that happen before line 34
    public void saveEntry (JournalEntry JournalEntry,String userName) {
        try {
            User user = userService.findByUsername(userName);
            JournalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = JournalEntryRepository.save(JournalEntry);
            // Add this safety check before calling .add()
            if (user.getJournalEntries() == null) {
                user.setJournalEntries(new ArrayList<>());
            }
            user.getJournalEntries().add(saved);
            //user.setUsername(null);
            userService.saveEntry(user);
            //userService.saveNewUser(user);
        } catch (Exception e) {
            throw new RuntimeException("en error occure while saving the entry",e);
        }
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

    // @Transactional
    //method for delete mapping
    public boolean  deleteById(ObjectId id, String userName){
        boolean removed=false;
        try {
            User user = userService.findByUsername(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if (removed){
                userService.saveEntry(user);
                JournalEntryRepository.deleteById(id);
            }
        } catch (Exception e) {
            log.error("Error from Journal EntryService ",e);
            throw new RuntimeException("An error occured while deleting the entry.",e);
        }return removed;

    }
//    public List<JournalEntry> findByUserName(String username){
//
//
//    }


}
