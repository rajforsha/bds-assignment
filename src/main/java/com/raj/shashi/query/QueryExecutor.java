package com.raj.shashi.query;

import com.mongodb.DBObject;
import com.mongodb.client.*;
import com.raj.shashi.db.DbConnector;
import com.raj.shashi.db.DbHelper;
import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.Document;

import java.util.Iterator;
import java.util.List;

public class QueryExecutor {

    private MongoClient mongoClient;
    private MongoCollection<Document> mongoCollection;

    public QueryExecutor(){
        this.mongoClient = DbConnector.connect();
        this.mongoCollection = this.mongoClient.getDatabase("mydb").getCollection("record");
        MongoIterable<String> dbs = this.mongoClient.listDatabaseNames();
        Iterator<String> itr = dbs.iterator();
        while(itr.hasNext()){
            System.out.println(itr.next());
        }

        MongoIterable<String> list = this.mongoClient.getDatabase("mydb").listCollectionNames();
        Iterator<String> itr1 = list.iterator();
        while(itr1.hasNext()){
            System.out.println(itr1.next());
        }

        System.out.println(this.mongoCollection.countDocuments());
    }


    public void queryBasedOnCuisineAndZipcode(String cuisine, String zipcode){

        BsonDocument bsonDocument = new BsonDocument();
        bsonDocument.append("typeOfFood", new BsonString(cuisine))
                .append("zipcode", new BsonString(zipcode));

        MongoCursor<Document> result = this.mongoCollection.find(bsonDocument)
                .iterator();
//                .sort(new BsonDocument().append("rating", new Bso));

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }

        System.out.println(this.mongoCollection.find().first().toJson());

    }

    public static void main(String [] args){

        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.queryBasedOnCuisineAndZipcode("Curry", "PA237HG");
    }

}
