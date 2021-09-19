package com.raj.shashi.query;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Projections;
import com.raj.shashi.db.DbConnector;
import org.bson.Document;
import org.bson.conversions.Bson;

import static com.mongodb.client.model.Filters.*;
import static com.mongodb.client.model.Accumulators.*;
import static com.mongodb.client.model.Aggregates.*;

import java.util.Arrays;
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
    }


    public void queryBasedOnCuisineAndZipcode(String cuisine, String zipcode){

        MongoCursor<Document> result = this.mongoCollection.find(and(eq("typeOfFood", cuisine), eq("zipcode", zipcode)))
                .projection(Projections.fields(Projections.include("name", "address", "rating"), Projections.excludeId()))
                .sort(new BasicDBObject("rating", -1))
                .limit(5)
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void queryBasedOnAddressAndMinimumRating(String address, String rating){

        MongoCursor<Document> result = this.mongoCollection.find(and(eq("address", address), gte("rating", rating)))
                .projection(Projections.fields(Projections.excludeId(), Projections.include("name", "typeOfFood", "score")))
                .sort(new BasicDBObject("score", -1))
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void groupZipcodeByCuisine(String cuisine){

        Bson group = group("$zipcode", Accumulators.sum("count", 1));
        Bson match = match(eq("typeOfFood", cuisine));
        Bson sort = sort(new BasicDBObject("count", -1));

        MongoCursor<Document> result = this.mongoCollection.aggregate(Arrays.asList(match, group, sort))
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }
    }

    public void averageRatingPerFood(){

        Bson group = group("$typeOfFood", avg("rating", "$rating"));
        Bson sort = sort(new BasicDBObject("rating", 1));
        MongoCursor<Document> result = this.mongoCollection.aggregate(Arrays.asList(group, sort))
                .iterator();

        while(result.hasNext()){
            System.out.println(result.next().toJson());
        }


    }

    public static void main(String [] args){

        QueryExecutor queryExecutor = new QueryExecutor();
        queryExecutor.queryBasedOnCuisineAndZipcode("African", "1HRE11");
        queryExecutor.queryBasedOnAddressAndMinimumRating("Unit 4 Spencer House", "1");
        queryExecutor.groupZipcodeByCuisine("Chinese");
        queryExecutor.averageRatingPerFood();
    }

}
