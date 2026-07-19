package com.talib.journalApp.controller;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import com.talib.journalApp.service.JournalEntryService;
import com.talib.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")//now if i gave any endPoint to method the that endPoint is written after this endpoint basically this is use to apply mapping on whole class

public class journalEntryControllerV2 {

    @Autowired//we inject JournalEntryService Through field
    private JournalEntryService JournalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("{userName}")
    public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String userName) {
        User user = userService.findByUsername(userName);
        List<JournalEntry> all = user.getJournalEntries();
        if (all != null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(all, HttpStatus.NOT_FOUND);
    }

    @PostMapping("{userName}")
    public ResponseEntity<JournalEntry>/*JournalEntry*/ createEntry(@RequestBody JournalEntry myEntry, @PathVariable String userName) {

        //because we return ResponseEntity so we can replace JournalEntry to ResponseEntity
        try {
            JournalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("id/{myId}"/*this is varable*/)
//in this GET API we try ro get Entry of user by specific id number rather than getting all user data
    public ResponseEntity<JournalEntry>/*JournalEntry*/ getJournalEntryById(@PathVariable ObjectId myId) {//@PathhVariable is use to get data through varable name like if we send request like http://localhost:8080/journal/id/vipul its called path variable
        //because it use optional we cannot directly return it
        //because we return ResponseEntity so we can replace JournalEntry to ResponseEntity
        Optional<JournalEntry> journalEntry = JournalEntryService.findById(myId);
        if (journalEntry.isPresent()) {
            return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{userName}/{myId}")
    //The meaning of ? mark is wild cart pattern its not necessary that alway we have to give an entity class we can return any other class object through ResponseEntity
    public ResponseEntity<?> deleteJournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName) {
        JournalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @PutMapping("/id/{userName}/{id}")
    public ResponseEntity<?> putJournalEntry(@PathVariable ObjectId id ,@RequestBody JournalEntry newEntry,@PathVariable String userName){
        JournalEntry old = JournalEntryService.findById(id).orElse(null);
        if(old != null){
            //agar newEntry ka title not equals to null or not empty to new title st kardo or else remain the old title
            old.setTitle(newEntry.getTitle() != null && !newEntry.getTitle().equals("") ? newEntry.getTitle(): old.getTitle());
            old.setContent(newEntry.getContent() != null && !newEntry.equals("") ? newEntry.getContent() : old.getContent());
            JournalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }
        return new ResponseEntity<>( HttpStatus.NOT_FOUND);

//        JournalEntryService.saveEntry(old);
//        return old;
    }
}
