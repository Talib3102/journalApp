package com.talib.journalApp.controller;

import com.talib.journalApp.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")//now if i gave any endPoint to method the that endPoint is written after this endpoint basically this is use to apply mapping on whole class

public class journalEntryController {
    
    //i created a fieled because currently there is no database available this is like a table for now we can add jornal entry here
    private Map<Long , JournalEntry> JournelEntries=new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAll(){
        //we can take all journal entries and values and convert them into ArrayList  this is a method
        return new ArrayList<>(JournelEntries.values());
        
    }
    @PostMapping
    public boolean createEntry(@RequestBody JournalEntry myEntry){
        JournelEntries.put(myEntry.getId(),myEntry);
        return true;
    }

    @GetMapping("id/{myId}"/*this is varable*/)//in this GET API we try ro get Entry of user by specific id number rather than getting all user data
    public JournalEntry getJournalEntryById(@PathVariable Long myId){//@PathhVariable is use to get data through varable name like if we send request like http://localhost:8080/journal/id/vipul its called path variable
        //but if we send url like http://localhost:8080/journal/id?name=vipul its called @RequestParam =parameter
        return JournelEntries.get(myId);

    }
    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntryById(@PathVariable Long myId){
        return JournelEntries.remove(myId);

    }

    @PutMapping("/id/{id}")
    public JournalEntry putJournalEntry(@PathVariable Long id ,@RequestBody JournalEntry myEntry){
        return JournelEntries.put(id,myEntry);
    }
}
