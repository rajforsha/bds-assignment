package com.raj.shashi.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Objects;

@Getter
@Setter
@ToString
public class Record {

    @JsonProperty("address")
    private String address;
    @JsonProperty("address line 2")
    private String addressLine2;
    private String name;
    private String outcode;
    private String postcode;
    private Double rating;
    @JsonProperty("type_of_food")
    private String typeOfFood;
    private String zipcode;

    public void setRating(String rating) {
        if(rating.equalsIgnoreCase("Not yet rated")){
            this.rating = 0.0;
        }
        else{
            this.rating = Double.parseDouble(rating);
        }
    }

    public void setOutcode(String outcode) {
        this.outcode = outcode;
        if(Objects.nonNull(this.postcode)){
            this.zipcode = this.postcode + this.outcode;
        }
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
        if(Objects.nonNull(this.outcode)){
            this.zipcode = this.postcode + this.outcode;
        }
    }
}