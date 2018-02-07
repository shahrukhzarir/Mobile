package com.shahrukhzarir.assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AskQuestion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_question);

        //constants

        final int YES =1;
        final int NO=0;
        //  Setup Intent
        Intent getQuestionIntent = getIntent();


        //Recieve information from intent and output
        String newQuestion = getQuestionIntent.getStringExtra("question");
        TextView textView = (TextView)findViewById(R.id.question);
        textView.setText(newQuestion);


        //AskQuestion will provide two buttons, Yes and No
        Button yes = (Button) findViewById(R.id.yes);
        Button no = (Button) findViewById(R.id.no);


        yes.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent resultIntent = new Intent();
                setResult(YES,resultIntent);
                finish();
            }
        });


        no.setOnClickListener(new View.OnClickListener(){
            public void onClick(View view){
                Intent resultIntent = new Intent();
                setResult(NO,resultIntent);
                finish();
            }
        });

    }
}
