package com.example.a100536625.finalproject;

/**
 * Created by 100536625 on 12/9/2017.
 */

public class Appointment {
    private String name;
    private String date;
    private String time;
    private String notes;
    private long id;

    public Appointment(String name, String date, String time, String notes) {
        this.name = name;
        this.date = date;
        this.time = time;
        this.notes = notes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return name + " " + date + " (" + time + ", " + notes + ")";
    }
}


