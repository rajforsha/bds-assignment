package com.raj.shashi.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Record {

    @JsonProperty("URL")
    private String url;
    @JsonProperty("_id")
    private _id id;
    @JsonProperty("address")
    private String address;
    @JsonProperty("address line 2")
    private String addressLine2;
    private String name;
    private String outcode;
    private String postcode;
    private String rating;
    @JsonProperty("type_of_food")
    private String typeOfFood;


    @Setter @Getter @ToString @NoArgsConstructor
    private class _id{
        @JsonProperty("$oid")
        private String $oid;
    }
}
