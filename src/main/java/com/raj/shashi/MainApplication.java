package com.raj.shashi;

import com.raj.shashi.query.QueryExecutor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class MainApplication {

    private QueryExecutor queryExecutor;

    public MainApplication(){
        this.queryExecutor = new QueryExecutor();
    }

    public static void main(String[] args){

        try{

            MainApplication mainApplication = new MainApplication();
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            while(true){

                System.out.println("Hello!!" +"\n"+ "Press 1 to query queryBasedOnCuisineAndZipcode"+
                        "\n Press 2 to query queryBasedOnAddressAndMinimumRating"+
                        "\n Press 3 to query groupZipcodeByCuisine"+
                        "\n Press 4 to get averageRatingPerFood"+
                        "\n Press any key to exit");

                int input = Integer.parseInt(reader.readLine());

                switch(input){

                    case 1:
                        System.out.println("please enter cuisine");
                        String cuisine = reader.readLine();
                        System.out.println("please enter zipcode");
                        String zipcode = reader.readLine();

                        mainApplication.queryExecutor.queryBasedOnCuisineAndZipcode(cuisine, zipcode);
                        break;

                    case 2:
                        System.out.println("please enter address");
                        String address = reader.readLine();
                        System.out.println("please enter rating");
                        String rating = reader.readLine();
                        mainApplication.queryExecutor.queryBasedOnAddressAndMinimumRating(address, rating);
                        break;

                    case 3:

                        System.out.println("please enter cuisine");
                        String cuisine1 = reader.readLine();
                        mainApplication.queryExecutor.groupZipcodeByCuisine(cuisine1);
                        break;

                    case 4:
                        mainApplication.queryExecutor.averageRatingPerFood();
                        break;

                    default:
                        System.exit(0);
                }

            }

        }
        catch(IOException e){

            System.out.println(e.getMessage());
        }

    }
}
