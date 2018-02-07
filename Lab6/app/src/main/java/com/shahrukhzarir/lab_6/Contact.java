package com.shahrukhzarir.lab_6;

/**
 * Created by shahrukhzarir on 2017-11-07.
 */

public class Contact {
    private int id;
    private String firstName;
    private String surname;
    private String phone;

    public Contact(int _id, String _firstname, String _surname, String _phone) {
        id = _id;
        firstName = _firstname;
        surname = _surname;
        phone = _phone;
    }

    public String getId() {
        return Integer.toString(this.id);
    }

    public int getIntId() {
        return this.id;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getSurname() {
        return this.surname;
    }

    public String getPhone() {
        return this.phone;
    }
}

