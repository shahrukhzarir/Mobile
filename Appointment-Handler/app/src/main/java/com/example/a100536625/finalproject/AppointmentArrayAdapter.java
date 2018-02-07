package com.example.a100536625.finalproject;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class AppointmentArrayAdapter extends ArrayAdapter<Appointment> {

    private List<Appointment> data;
    private Context context;

    public AppointmentArrayAdapter(Context context, List<Appointment> data) {
        super(context, R.layout.appointment_list_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Appointment appointment = data.get(position);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.appointment_list_item, parent, false);
        }

        TextView lblName = (TextView)reusableView.findViewById(R.id.lblItemName);
        lblName.setText(appointment.getName());

        TextView lblDate = (TextView)reusableView.findViewById(R.id.lblItemDate);
        lblDate.setText(appointment.getDate());

        TextView lblTime = (TextView)reusableView.findViewById(R.id.lblItemTime);
        lblTime.setText(appointment.getTime());

        TextView lblNotes = (TextView)reusableView.findViewById(R.id.lblItemNotes);
        lblNotes.setText(appointment.getNotes());

        return reusableView;
    }
}
