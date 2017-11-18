package com.mjm.workflowkami.model_classes;

import java.io.Serializable;

/**
 * Created by Jasper on 1 Sep 2017.
 */

public class UserClass implements Serializable {


    private Integer userID;
    private String firstname;
    private String lastname;
    private String middlename;
    private String email;
    private String password;
    private Boolean userstatus;

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(Integer userID) {
        this.userID = userID;
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

    public String getMiddlename() {
        return middlename;
    }

    public void setMiddlename(String middlename) {
        this.middlename = middlename;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getUserstatus() {
        return userstatus;
    }

    public void setUserstatus(Boolean userstatus) {
        this.userstatus = userstatus;
    }

    public UserClass() { }

    public UserClass(Integer userID, String firstname, String lastname, String middlename, String email, String password, Boolean userstatus) {
        this.userID = userID;
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.password = password;
        this.userstatus = userstatus;
    }

    public UserClass(String firstname, String lastname, String middlename, String email, String password, Boolean userstatus) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.middlename = middlename;
        this.email = email;
        this.password = password;
        this.userstatus = userstatus;
    }


    @Override
    public String toString() {
        return "UserClass{" +
                "userID=" + userID +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", middlename='" + middlename + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userstatus='" + userstatus + '\'' +
                '}';
    }
}
