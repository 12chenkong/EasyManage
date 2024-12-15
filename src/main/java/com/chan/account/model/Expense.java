package com.chan.account.model;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Expense {
    private String expsense_id;
    private double amount;
    private String date;
    private String category;
    public String time;

    public Expense(){

    }
    public Expense(String expsense_id, double amount,  String category) {
        this.expsense_id = expsense_id;
        this.amount = amount;
        LocalDate today=LocalDate.now();
        String currentDate=String.valueOf(today);
        this.date = currentDate;
        this.category = category;
        LocalTime now=LocalTime.now();
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("hh:mm a");
        String formattedTime=now.format(formatter);
        this.time=formattedTime;
        this.time = time;
    }
    public String getExpsense_id() {
        return expsense_id;
    }

    public void setExpsense_id(String expsense_id) {
        this.expsense_id = expsense_id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expsense_id=" + expsense_id +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                ", category='" + category + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

}
