package com.springapp.mylms.ajaxClasses;

/**
 * Created by harout on 2017-08-22.
 */
public class Greeting {

    private String firstname;
    private String lastname;

    public Greeting(){}

    public Greeting(String firstname, String lastname) {
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}
