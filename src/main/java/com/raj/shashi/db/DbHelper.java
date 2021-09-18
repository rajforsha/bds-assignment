package com.raj.shashi.db;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class DbHelper {

    private MongoClient mongoClient;

    public DbHelper (){
        this.mongoClient = DbConnector.connect();
        System.out.println(mongoClient.getClusterDescription()+"\n"+mongoClient.listDatabaseNames());
        MongoDatabase mongoDatabase = mongoClient.getDatabase("mydb");
        //mongoDatabase.createCollection("record");
        MongoCollection<Document> mongoCollection = mongoDatabase.getCollection("record");

    }

    public void insert(){

    }

    public static void main(String [] args){
        DbHelper dbHelper = new DbHelper();
    }
}
