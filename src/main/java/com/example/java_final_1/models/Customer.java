package com.example.java_final_1.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.*;

@Validated
@Document("customers")

public class Customer {
    @Id
    private String id;
    @Indexed(unique = true)
    @NotNull(message = "email_phonenumber field can not be null")
    private String email_phoneNumber;
    @Size(min=8, message ="Password should be atleast 8 character long")
    @NotNull(message = "password can not be blank")
    private String password;



    public Customer(String email_phoneNumber,  String password) {
        this.email_phoneNumber=email_phoneNumber;
        this.password = password;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail_phoneNumber(){
        return email_phoneNumber;

    }
    public void setEmail_phoneNumber(String email_phoneNumber){
        this.email_phoneNumber=email_phoneNumber;
    }

    public Customer(){


    }
    @Override
    public String toString() {

        return String.format("Customer[id='%s', email_phoneNumber='%s',password='%s']", id, email_phoneNumber ,password);}
}


