package com.talib.journalApp.repository;

import com.talib.journalApp.entity.ConfigJournalAppEntity;
import com.talib.journalApp.entity.User;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ConfigJournalAppRepository extends MongoRepository<ConfigJournalAppEntity, ObjectId> {

}
