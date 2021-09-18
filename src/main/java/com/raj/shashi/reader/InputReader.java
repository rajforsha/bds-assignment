package com.raj.shashi.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import con.raj.shashi.dto.Record;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class InputReader {

    public void read(){

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = Record.class.getResourceAsStream("/res.json");
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, Record.class);
            ArrayList<Record> recordArrayList = objectMapper.readValue(inputStream, collectionType);

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

    }

    public static void main (String [] args){

        InputReader inputReader = new InputReader();
        inputReader.read();
    }

}

