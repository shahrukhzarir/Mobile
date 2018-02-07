package com.example.a100536625.finalproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class AppointmentHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;

    static final String TABLE = "appts";

    static final String CREATE_STATEMENT = "CREATE TABLE appts (\n" +
            "      _id integer primary key autoincrement,\n" +
            "      name text not null,\n" +
            "      date text not null,\n" +
            "      time text not null,\n" +
            "      notes text null\n" +
            ")\n";

    static final String DROP_STATEMENT = "DROP TABLE appts";

    public AppointmentHelper(Context context) {
        super(context, "appts", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database, using CREATE SQL statement
        db.execSQL(CREATE_STATEMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db,
                          int oldVersionNum,
                          int newVersionNum) {
        // delete the old database
        db.execSQL(DROP_STATEMENT);

        // re-create the database
        db.execSQL(CREATE_STATEMENT);
    }

    // CRUD functions

    // CREATE
    public Appointment createAppointment(String name,
                                 String date,
                                 String time,
                                 String notes) {
        // create a new entity object (Appointment)
        Appointment appointment = new Appointment(name, date, time, notes);

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("name", name);
        newValues.put("date", date);
        newValues.put("time", time);
        newValues.put("notes", notes);

        long id = db.insert(TABLE, null, newValues);

        // update the contact's id
        appointment.setId(id);

        return appointment;
    }

    // READ
    public Appointment getAppointment(long id) {
        Appointment appointment = null;

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name", "date", "time", "notes"};
        String where = "_id = ?";
        String[] whereArgs = new String[] { "" + id };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "");

        if (cursor.getCount() >= 1) {
            cursor.moveToFirst();

            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String time = cursor.getString(3);
            String notes = cursor.getString(4);

            appointment = new Appointment(name, date, time, notes);
            appointment.setId(id);
        }

        return appointment;
    }

    public List<Appointment> getAllAppointments() {
        List<Appointment> appointments = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"_id", "name", "date", "time", "notes"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "date");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                long id = cursor.getLong(0);
                String name = cursor.getString(1);
                String date = cursor.getString(2);
                String time = cursor.getString(3);
                String notes = cursor.getString(4);

                Appointment appointment = new Appointment(name, date, time, notes);
                appointment.setId(id);

                appointments.add(appointment);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        return appointments;
    }

    // UPDATE
    public boolean updateAppointment(Appointment appointment) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name", appointment.getName());
        values.put("date", appointment.getDate());
        values.put("time", appointment.getTime());
        values.put("notes", appointment.getNotes());

        int numRows = db.update(TABLE, values, "_id = ?", new String[] { "" + appointment.getId() });

        return (numRows == 1);
    }

    // DELETE
    public boolean deleteAppointment(long id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "_id = ?", new String[] { "" + id });

        return (numRows == 1);
    }

    public void deleteAllAppointments() {
        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE, "", new String[] { });
    }
}

