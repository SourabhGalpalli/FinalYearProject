package com.example.recipies;

public class User {
    public String firstname,lastname;

    public User(){
    }

    public User(String firstname, String lastname){
        this.firstname=firstname;
        this.lastname=lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }
}
