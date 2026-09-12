package com.talib.journalApp.repository;

import com.talib.journalApp.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class UserRepositoryImpl {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<User> getUserForSA(){
        Query query=new Query();
        query.addCriteria(Criteria.where("email").regex("^[A-Za-z0-9+_.-]+@(.+)$"));

        query.addCriteria(Criteria.where("sentimentAnalysis").is(true));

        List<User> users = mongoTemplate.find(query, User.class);
        return users;
//        return mongoTemplate.find(query, User.class);
    }
}

//query.addCriteria(Criteria.where("userName").is("Sufiyan"));
//we can test criteria after query
//List<User> users = mongoTemplate.find(query, User.class);
//then return users


//instead of this we can use AND or or operator
//Criteria criteria=new Criteria();
//query.addCriteria(criteria.orOperator(Criteria.where("email").exists(true),
//                Criteria.where("sentimentAnalysis").is(true)));