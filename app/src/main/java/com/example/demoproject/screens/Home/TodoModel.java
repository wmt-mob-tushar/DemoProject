package com.example.demoproject.screens.Home;

import java.sql.Date;
public class TodoModel {
    public int id;
    public String title;
    public String description;
    public String date;
    public TodoModel() {}

//    getter and setter methods
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

//    validation
    public boolean title(){
        return title != null && title.length() > 0;
    }
    public boolean description(){
        return description != null && description.length() > 0;
    }
    public boolean isValid(){
        return title() && description();
    }
}
