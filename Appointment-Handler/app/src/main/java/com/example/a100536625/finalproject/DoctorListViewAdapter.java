package com.example.a100536625.finalproject;

/**
 * Created by shahrukhzarir on 2017-12-11.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class DoctorListViewAdapter extends BaseAdapter {

    // Declare Variables
    Context mContext;
    LayoutInflater inflater;
    private List<Doctor> doctorlist = null;
    private ArrayList<Doctor> doctorArrayList;

    public DoctorListViewAdapter(Context context, List<Doctor> doctorlist) {
        mContext = context;
        this.doctorlist = doctorlist;
        inflater = LayoutInflater.from(mContext);
        this.doctorArrayList = new ArrayList<Doctor>();
        this.doctorArrayList.addAll(doctorlist);
    }

    public class ViewHolder {
        TextView firstName;
        TextView lastName;
        TextView location;
        TextView city;
    }

    @Override
    public int getCount() {
        return doctorlist.size();
    }

    @Override
    public Doctor getItem(int position) {
        return doctorlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (view == null) {
            viewHolder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // assign in xml file
            viewHolder.firstName = view.findViewById(R.id.first_name);
            viewHolder.lastName = view.findViewById(R.id.last_name);
            viewHolder.location = view.findViewById(R.id.location);
            viewHolder.city = view.findViewById(R.id.city);
            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }
        // set text into place
        viewHolder.firstName.setText(doctorlist.get(position).getFirstName());
        viewHolder.lastName.setText(doctorlist.get(position).getLastName());
        viewHolder.location.setText(doctorlist.get(position).getLocation());
        viewHolder.city.setText(doctorlist.get(position).getCity());

        // Listen for ListView Item Click
        view.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Send data to SingleDoctorView Class
                Intent intent = new Intent(mContext, SingleDoctorView.class);
                // Pass first name
                intent.putExtra("firstName",(doctorlist.get(position).getFirstName()));
                // Pass last name
                intent.putExtra("lastName",(doctorlist.get(position).getLastName()));
                // Pass location
                intent.putExtra("location",(doctorlist.get(position).getLocation()));
                // Pass city
                intent.putExtra("city",(doctorlist.get(position).getCity()));
                // Start Activity with data Class
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    // search feature
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        doctorlist.clear();
        if (charText.length() == 0) {
            doctorlist.addAll(doctorArrayList);
        } else {
            for (Doctor wp : doctorArrayList) {
                if (wp.getLastName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    doctorlist.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}