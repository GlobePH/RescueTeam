package com.rescueph.model;

/**
 * Created by Jm on 23/07/2016.
 */
public class Messages {

    private String userid;
    private String message;
    private String messageid;
    private double xcoord;
    private double ycoord;
    private Integer status;
    private String fullname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessageid() {
        return messageid;
    }

    public void setMessageid(String messageid) {
        this.messageid = messageid;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }
    public double getXcoord() {
        return xcoord;
    }

    public void setXcoord(double xcoord) {
        this.xcoord = xcoord;
    }

    public double getYcoord() {
        return ycoord;
    }

    public void setYcoord(double ycoord) {
        this.ycoord = ycoord;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
