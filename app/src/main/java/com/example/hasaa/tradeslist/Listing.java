package com.example.hasaa.tradeslist;

/**
 * Created by Steven on 12/4/2017.
 */

public class Listing {
    private String title;
    private String description;
    private String request;

    public Listing(){
        //need default
        title = "";
        description = "";
        request = "";
    }

    public Listing(String _title, String _description, String _request){
        title = _title;
        description = _description;
        request = _request;
    }

    public void setTitle(String _title){
        title = _title;
    }

    public void setDescription(String _description){
        description = _description;
    }

    public void setRequest(String _request){
        request = _request;
    }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getRequest(){
        return request;
    }
}
