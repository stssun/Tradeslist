package com.example.hasaa.tradeslist;

/**
 * Created by Steven on 12/4/2017.
 */

public class Listing {
    private String title;
    private String description;
    private String request;
    private String phone;
    private String email;

    public Listing(){
        //need default
        title = "";
        description = "";
        request = "";
        phone = "";
        email = "";
    }

    public Listing(String _title, String _description, String _request, String _phone, String _email){
        title = _title;
        description = _description;
        request = _request;
        phone = _phone;
        email = _email;
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

    public void setPhone(String _phone) { phone = _phone; }

    public void setEmail(String _email) { email = _email; }

    public String getTitle(){
        return title;
    }

    public String getDescription(){
        return description;
    }

    public String getRequest(){
        return request;
    }

    public String getPhone(){ return phone; }

    public String getEmail() { return email; }
}
