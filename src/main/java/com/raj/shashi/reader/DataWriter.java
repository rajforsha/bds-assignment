package com.raj.shashi.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.shashi.dto.Record;

import java.util.ArrayList;
import java.util.List;

public class DataWriter {

    private ObjectMapper objectMapper;
    private InputReader inputReader;

    public DataWriter (){
        this.objectMapper = new ObjectMapper();
        this.inputReader = new InputReader();
    }

    public void insertDataToDatabase(){

        List<Record> recordList = this.inputReader.read();
        List<com.raj.shashi.domain.Record> list = new ArrayList<>();
        recordList.parallelStream().forEach(record->{
            list.add(this.objectMapper.convertValue(record, com.raj.shashi.domain.Record.class));
        });

        System.out.println(list.get(0).toString());

    }

    public static void main(String [] args){
        DataWriter dataWriter = new DataWriter();
        dataWriter.insertDataToDatabase();
    }
}
