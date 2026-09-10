package com.talib.journalApp.repository;

import com.talib.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, ObjectId> {
//    User findByUsername(String username);
//    void deleteByUsername(String username);
      User findByUserName(String name);
      void deleteByUserName(String name);
}
