package com.raj.shashi;

import com.raj.shashi.query.QueryExecutor;

import java.util.Scanner;

public class MainApplication {

    private QueryExecutor queryExecutor;

    public MainApplication(){
        this.queryExecutor = new QueryExecutor();
    }

    public static void main(String[] args){

        MainApplication mainApplication = new MainApplication();

        Scanner scanner = new Scanner(System.in);

        while(true){

            System.out.println("Hello!!" +"\n"+ "Press 1 to query queryBasedOnCuisineAndZipcode"+
                    "\n Press 2 to query queryBasedOnAddressAndMinimumRating"+
                    "\n Press 3 to query groupZipcodeByCuisine"+
                    "\n Press 4 to get averageRatingPerFood"+
                    "\n Press any key to exit");

            int input = scanner.nextInt();

            switch(input){

                case 1:
                    System.out.println("please enter cuisine and zipcode");
                    String cuisine = scanner.next();
                    String zipcode = scanner.next();

                    mainApplication.queryExecutor.queryBasedOnCuisineAndZipcode(cuisine, zipcode);
                    break;

                case 2:
                    System.out.println("please enter address and rating");
                    String address = scanner.next();
                    String rating = scanner.next();

                    mainApplication.queryExecutor.queryBasedOnAddressAndMinimumRating(address, rating);
                    break;

                case 3:

                    System.out.println("please enter cuisine");
                    String cuisine1 = scanner.next();
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
}
