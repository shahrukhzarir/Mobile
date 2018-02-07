package com.shahrukhzarir.lab_6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by shahrukhzarir on 2017-11-07.
 */

public class DeleteContact extends AppCompatActivity {
    private ArrayList<String> contactList = new ArrayList<String>();
    private String path;
    private File contactDataFile;
    private String filename = "contactData.txt";

    private ArrayAdapter<String> adapter;
    private Spinner contactSpinner;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_contact);

        contactSpinner = (Spinner) findViewById(R.id.spinner);

        path = this.getFilesDir().getPath() + "/" + filename;
        contactDataFile = new File(path);

        String[] split;
        String line = null;
        try {
            FileReader in = new FileReader(contactDataFile);
            BufferedReader br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                Log.i("SCANNER_READ", line);
                split = line.split("\\s+");
                contactList.add((Integer.parseInt(split[0])+ "\t" + split[1] + " " + split[2]));
            }

            adapter = new ArrayAdapter<String>(getApplicationContext(),
                    android.R.layout.simple_spinner_item, contactList);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            contactSpinner.setAdapter(adapter);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onClickDeleteContact(View view) {
        String[] deleteItem = contactSpinner.getSelectedItem().toString().split("\\s+");
        Log.i("YAYA", deleteItem[0]);

        Intent output = new Intent();
        output.putExtra("ID", deleteItem[0]);

        setResult(112, output);
        finish();
    }


}