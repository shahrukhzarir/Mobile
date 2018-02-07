package com.example.a100536625.finalproject;

/**
 * Created by shahrukhzarir on 2017-12-11.
 */

public class Doctor {
    private String firstName;
    private String lastName;
    private String location;
    private String city;

    public Doctor(String firstName, String lastName, String location, String city) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.location = location;
        this.city = city;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getLocation() {
        return this.location;
    }

    public String getCity() {
        return this.city;
    }
}
