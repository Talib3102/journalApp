package com.talib.journalApp.repository;

import com.talib.journalApp.entity.JournalEntry;
import com.talib.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
    User findByUsername(String username);
}
