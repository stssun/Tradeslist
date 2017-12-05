package com.example.hasaa.tradeslist;

/**
 * Created by Steven on 11/13/2017.
 */

public class Profile {
    private String full_name;
    private String phone_number;

    public Profile(){
        full_name = "";
        phone_number = "";

        //required for calls to DataSnapshot.get
    }

    public Profile(String name, String phone){
        full_name = name;
        phone_number = phone;
    }

    public void setName(String name){
        full_name = name;
    }

    public void setNumber(String number){
        phone_number = number;
    }

    public String getName(){return full_name; }

    public String getNumber(){return phone_number; }

}
