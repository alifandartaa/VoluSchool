package com.example.voluschool.model;

public class History {
    private String schoolName;
    private String cost;
    private String Date;

    public History(String schoolName, String cost, String date) {
        this.schoolName = schoolName;
        this.cost = cost;
        Date = date;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public String getCost() {
        return cost;
    }

    public String getDate() {
        return Date;
    }

}
