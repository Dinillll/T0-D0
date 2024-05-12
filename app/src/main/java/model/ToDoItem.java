package model;

import java.io.Serializable;

public class ToDoItem implements Serializable {

    private int id;
    private String name;
    private int year;
    private int month;
    private int day;
    private int isCompleted;
    private String urgency;

    public ToDoItem() {
        id=-1;
    }

    public ToDoItem(int id, String name, int year, int month, int day, String urgency) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.urgency = urgency;
    }

    public ToDoItem(int id, String name, int year, int month, int day, int isCompleted, String urgency) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.month = month;
        this.day = day;
        this.isCompleted = isCompleted;
        this.urgency = urgency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(int isCompleted) {
        this.isCompleted = isCompleted;
    }

    public String getUrgency() {
        return urgency;
    }

    public void setUrgency(String urgency) {
        this.urgency = urgency;
    }
}
