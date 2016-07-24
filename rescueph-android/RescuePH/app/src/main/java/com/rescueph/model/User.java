package com.rescueph.model;

/**
 * Created by Jm on 23/07/2016.
 */
public class User {

    private String userid;
    private String email;
    private String password;
    private Boolean isloggedin;
    //private String imgId;
    private String contactnumber;
    private Boolean isverified;
    private String fullname;

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

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Boolean getIsloggedin() {
        return isloggedin;
    }

    public void setIsloggedin(Boolean isloggedin) {
        this.isloggedin = isloggedin;
    }

    public String getContactnumber() {
        return contactnumber;
    }

    public void setContactnumber(String contactnumber) {
        this.contactnumber = contactnumber;
    }

    public Boolean getIsverified() {
        return isverified;
    }

    public void setIsverified(Boolean isverified) {
        this.isverified = isverified;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
}
