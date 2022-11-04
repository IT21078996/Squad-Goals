package com.example.getwheels01;

public class Customer {
    private String userid;
    private String name;
    private String car;
    private String conNo;
    private String location;
    private String PDate;
    private String DDate;

    public Customer() {

    }

//    public User(String userid, String name, String car, String conNo)
//    {
//        this.userid = userid;
//        this.name = name;
//        this.car = car;
//        this.conNo = conNo;
//    }

    public Customer(String userid, String name, String car, String conNo, String location, String PDate, String DDate) {
        this.userid = userid;
        this.name = name;
        this.car = car;
        this.conNo = conNo;
        this.location = location;
        this.PDate = PDate;
        this.DDate = DDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPDate() {
        return PDate;
    }

    public void setPDate(String PDate) {
        this.PDate = PDate;
    }

    public String getDDate() {
        return DDate;
    }

    public void setDDate(String DDate) {
        this.DDate = DDate;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getConNo() {
        return conNo;
    }

    public void setConNo(String conNo) {
        this.conNo = conNo;
    }
}