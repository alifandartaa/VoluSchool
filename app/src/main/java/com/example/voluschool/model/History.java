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

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }
}
