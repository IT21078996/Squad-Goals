package com.example.getwheels1;

public class Car {
    private String id;
    private String car_Name;

    private String car_model;

    private String manu_year;

     private String reg_no;

     private String Add_details;


    public Car() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCar_Name() {
        return car_Name;
    }

    public void setCar_Name(String car_Name) {
        this.car_Name = car_Name;
    }

    public String getCar_model() {
        return car_model;
    }

    public void setCar_model(String car_model) {
        this.car_model = car_model;
    }

    public String getManu_year() {
        return manu_year;
    }

    public void setManu_year(String manu_year) {
        this.manu_year = manu_year;
    }

    public String getReg_no() {
        return reg_no;
    }

    public void setReg_no(String reg_no) {
        this.reg_no = reg_no;
    }

    public String getAdd_details() {
        return Add_details;
    }

    public void setAdd_details(String add_details) {
        Add_details = add_details;
    }
}
