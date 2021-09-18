package com.raj.shashi.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import java.util.Objects;

public class DbConnector {

    private static MongoClient mongoClient;

    public static MongoClient connect(){
        synchronized (DbConnector.class){
            if(Objects.isNull(mongoClient)){
                synchronized (DbConnector.class){
                    if(Objects.isNull(mongoClient)){
                        mongoClient = MongoClients.create();
                    }
                }
            }

            return mongoClient;
        }
    }
}
