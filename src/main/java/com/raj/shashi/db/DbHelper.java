package com.raj.shashi.db;

import com.google.gson.Gson;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.raj.shashi.domain.Record;
import org.bson.Document;

import java.util.List;

public class DbHelper {

    private MongoClient mongoClient;

    public DbHelper (){
        this.mongoClient = DbConnector.connect();
    }

    public void save(List<Record> recordList){

        Gson gson = new Gson();
        MongoCollection<Document> collection = this.mongoClient.getDatabase("mydb").getCollection("record");
        recordList.stream().forEach(record->{
            Document document = Document.parse(gson.toJson(record));
            collection.insertOne(document);
        });

    }

    public static void main(String [] args){
        DbHelper dbHelper = new DbHelper();
    }
}
