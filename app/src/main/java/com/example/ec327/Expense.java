package com.example.ec327;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Expense {
    protected String name;
    protected float cost;
    protected String timeStamp;

    public Expense(){
        name = " ";
        cost = 0;
        timeStamp = "";
    }

    public Expense(String name, float cost, String time){
        this.name = name;
        this.cost = cost;
        this.timeStamp = new SimpleDateFormat("yyy.MM.dd.HH.mm.ss").format(new Date());
    }

    public String getName() {
        return name;
    }

    public float getCost() {
        return cost;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}
