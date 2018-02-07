package com.shahrukhzarir.lab_6;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public int listSize = 0;
    String TAG = "YAYAYAY";
    public int ADD_CONTACT = 111;
    public int DELETE_CONTACT = 112;

    ArrayList<Contact> contactList = new ArrayList<Contact>();
    ContactArrayAdapter  adapter;
    ListView listView;

    String filename = "contactData.txt";
    String path;
    File contactDataFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.ListView);
        adapter = new ContactArrayAdapter(this, contactList);
        listView.setAdapter(adapter);

        path = this.getFilesDir().getPath() + "/" + filename;
        contactDataFile = new File(path);

    }


    @Override
    protected void onStart() {
        super.onStart();

        contactList.clear();
        Log.i(TAG, "START");

        String[] split;
        String line = null;
        try {
            FileReader in = new FileReader(contactDataFile);
            BufferedReader br = new BufferedReader(in);
            while ((line = br.readLine()) != null) {
                Log.i("SCANNER_READ", line);
                split = line.split("\\s+");
                contactList.add(new Contact(Integer.parseInt(split[0]), split[1], split[2], split[3]));
            }
            Log.i("SIZEE", Integer.toString(contactList.size()));
            adapter.notifyDataSetChanged();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listSize = contactList.size();
    }

    @Override
    protected void onStop() {
        super.onStop();
        writeFile();
    }

    public void onClickAdd(View v) {
        Intent intent = new Intent(this, AddContact.class);
        startActivityForResult(intent, ADD_CONTACT);
    }

    public void onClickDelete(View v) {
        Intent intent = new Intent(this, DeleteContact.class);
        startActivityForResult(intent, DELETE_CONTACT);
    }

    public void addContactFile(Contact contact) {
        FileOutputStream outputStream;
        String str;
        try {
            int i = 0;
            outputStream = openFileOutput(filename, Context.MODE_APPEND);
            str = contact.getId() + " " + contact.getFirstName() + " " + contact.getSurname() + " " + contact.getPhone() + "\n";
            Log.i("SCANNER_WRITING:", str);
            outputStream.write(str.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeFile() {
        FileOutputStream outputStream;
        String str;
        try {
            File f = new File(getFilesDir(), filename);
            f.delete();
            outputStream = openFileOutput(filename, Context.MODE_PRIVATE);
            for (int i = 0; i < contactList.size(); i++) {
                str = Integer.toString(i+1) + " " + contactList.get(i).getFirstName() +
                        " " + contactList.get(i).getSurname() + " " + contactList.get(i).getPhone() + "\n";
                Log.i("SCANNER_WRITING:", str);
                outputStream.write(str.getBytes());
            }
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.i(TAG, "RESULT");
        if(resultCode == ADD_CONTACT) {
            Contact contact = new Contact(listSize+1, data.getStringExtra("FIRST_NAME"), data.getStringExtra("SURNAME"),
                    data.getStringExtra("PHONE"));
            contactList.add(contact);
            addContactFile(contact);
            adapter.notifyDataSetChanged();
        }
        if(requestCode == DELETE_CONTACT) {
            int id = Integer.parseInt(data.getStringExtra("ID"));
            for (int i = 0; i < contactList.size(); i++) {
                if (id == contactList.get(i).getIntId()) {
                    contactList.remove(i);
                    writeFile();
                }
            }
        }
    }
}