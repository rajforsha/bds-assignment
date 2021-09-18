package com.raj.shashi.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.raj.shashi.dto.Record;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class InputReader {

    public List<Record> read(){

        try{
            ObjectMapper objectMapper = new ObjectMapper();

            InputStream inputStream = this.getClass().getResourceAsStream("/res.json");
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, Record.class);
            return objectMapper.readValue(inputStream, collectionType);

        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        return null;
    }

    public static void main (String [] args){

        InputReader inputReader = new InputReader();
        inputReader.read();
    }

}
