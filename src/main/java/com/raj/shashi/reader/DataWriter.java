package com.raj.shashi.reader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.raj.shashi.db.DbHelper;
import com.raj.shashi.dto.Record;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DataWriter {

    private ObjectMapper objectMapper;
    private InputReader inputReader;
    private DbHelper dbHelper;

    public DataWriter (){
        this.objectMapper = new ObjectMapper();
        this.inputReader = new InputReader();
        this.objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.dbHelper = new DbHelper();
    }

    public void insertDataToDatabase(){

        List<Record> recordList = this.inputReader.read();
        List<com.raj.shashi.domain.Record> list = new ArrayList<>();
        recordList.stream().forEach(record->{
            com.raj.shashi.domain.Record record1 = this.objectMapper.convertValue(record, com.raj.shashi.domain.Record.class);
            if(Objects.nonNull(record1)){
                list.add(record1);
            }
        });

        this.dbHelper.save(list);
    }

    public static void main(String [] args){
        DataWriter dataWriter = new DataWriter();
        dataWriter.insertDataToDatabase();
    }
}
