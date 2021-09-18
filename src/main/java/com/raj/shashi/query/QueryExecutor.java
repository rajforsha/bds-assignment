package com.raj.shashi.query;

import com.mongodb.client.*;
import com.raj.shashi.db.DbConnector;
import org.bson.Document;
import static com.mongodb.client.model.Filters.*;

import java.util.Iterator;

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
        //        System.out.println(this.mongoCollection.find().first().toJson());
    }


    public void queryBasedOnCuisineAndZipcode(String cuisine, String zipcode){

        MongoCursor<Document> result = this.mongoCollection.find(and(eq("typeOfFood", cuisine), eq("zipcode", zipcode)))
                .batchSize(5)
//                .sort(new BsonDocument().)
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void queryBasedOnAddressAndMinimumRating(String address, String rating){

        MongoCursor<Document> result = this.mongoCollection.find(and(eq("address", address), gte("rating", rating)))
//                .projection(BsonArray.parse("{""}"))
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void groupZipcodeByCuisine(String cuisine){

        MongoCursor<Document> result = this.mongoCollection.find(eq("typeOfFood", cuisine))
//                .projection(BsonArray.parse("{""}"))
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void averageRatingPerFood(){

    }

    public static void main(String [] args){

        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.queryBasedOnCuisineAndZipcode("Chinese", "NE163DS");
        queryExecutor.queryBasedOnAddressAndMinimumRating("Unit 4 Spencer House", "1");
        queryExecutor.groupZipcodeByCuisine("Chinese");
    }

}
