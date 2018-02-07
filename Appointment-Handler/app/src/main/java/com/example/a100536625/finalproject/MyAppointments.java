package com.example.a100536625.finalproject;

import android.app.Activity;
import android.content.Intent;
import android.icu.util.Calendar;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import com.example.a100536625.finalproject.Appointment;
import com.example.a100536625.finalproject.AppointmentHelper;

public class MyAppointments extends AppCompatActivity
        implements AdapterView.OnItemClickListener {
    public static int APPOINTMENT_DETAILS_REQUEST = 1;

    private AppointmentArrayAdapter appointmentArrayAdapter;
    private ListView appointmentList;
    private AppointmentHelper appointmentHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_appointments);

        appointmentList = (ListView)findViewById(R.id.lstAppointments);

        appointmentHelper = new AppointmentHelper(this);
        updateAppointmentList();
        appointmentList.setOnItemClickListener(this);
    }


    private void updateAppointmentList() {
        List<Appointment> appointments = appointmentHelper.getAllAppointments();
        appointmentArrayAdapter = new AppointmentArrayAdapter(this, appointments);
        appointmentList.setAdapter(appointmentArrayAdapter);
    }


    private void showEditAppointment(long id) {
        Intent editAppointmentIntent = new Intent(this, EditAppointmentActivity.class);
        editAppointmentIntent.putExtra("id", id);
        startActivityForResult(editAppointmentIntent, APPOINTMENT_DETAILS_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode,
                                 int responseCode,
                                 Intent result) {
        if (requestCode == APPOINTMENT_DETAILS_REQUEST) {
            updateAppointmentList();
        }
    }

    @Override
    public void onItemClick(AdapterView aView, View source, int position, long id) {
        Appointment appointment = (Appointment)appointmentArrayAdapter.getItem(position);
        // show the edit appointment activity
        showEditAppointment(appointment.getId());
    }

    public void add(View source) {
        // show the edit appointment activity
        showEditAppointment(-1);
    }


}