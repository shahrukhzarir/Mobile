package com.shahrukhzarir.lab_6;

/**
 * Created by shahrukhzarir on 2017-11-07.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddContact extends AppCompatActivity {
    int CONTACT_INFO = 111;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_contact);


    }

    public void onClickAdd(View view) {
        EditText firstName = (EditText)findViewById(R.id.fldFirstName);
        EditText surname = (EditText)findViewById(R.id.fldSurname);
        EditText phone = (EditText)findViewById(R.id.fldPhone);

        Intent output = new Intent();
        output.putExtra("FIRST_NAME", firstName.getText().toString());
        output.putExtra("SURNAME", surname.getText().toString());
        output.putExtra("PHONE", phone.getText().toString());

        setResult(CONTACT_INFO, output);
        finish();
    }
}