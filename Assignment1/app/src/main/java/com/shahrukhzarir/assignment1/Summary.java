package com.shahrukhzarir.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class Summary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        //Obtain and recieve intent, and information
        Intent sentIntent = getIntent();
        ArrayList<String> getResponse = sentIntent.getStringArrayListExtra("answer");

        TextView text = (TextView)findViewById(R.id.summary);

        //Builds and Displays Summary
        StringBuilder builder = new StringBuilder();
        for(String i:getResponse){
            builder.append(i+"\n");
        }
        text.setText(builder.toString());

    }
}
