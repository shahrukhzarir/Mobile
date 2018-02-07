package com.shahrukhzarir.lab_6;

/**
 * Created by shahrukhzarir on 2017-11-07.
 */

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ContactArrayAdapter extends ArrayAdapter<Contact> {

    private List<Contact> data;
    private Context context;

    public ContactArrayAdapter(Context context, List<Contact> data) {
        super(context, R.layout.contact_list_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Contact contact = data.get(position);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.contact_list_item, parent, false);
        }

        TextView lblId = (TextView)reusableView.findViewById(R.id.lblItemId);
        lblId.setText(contact.getId());

        TextView lblFirstName = (TextView)reusableView.findViewById(R.id.lblItemFirstName);
        lblFirstName.setText(contact.getFirstName());

        TextView lblLastName = (TextView)reusableView.findViewById(R.id.lblItemSurname);
        lblLastName.setText(contact.getSurname());

        TextView lblPhone = (TextView)reusableView.findViewById(R.id.lblItemPhone);
        lblPhone.setText(contact.getPhone());

        return reusableView;
    }
}

