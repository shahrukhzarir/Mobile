package com.example.shahrukhzarir.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class AddGrade extends AppCompatActivity {
    private EditText id;
    private EditText courseComponet;
    private EditText mark;

    private GradeDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_grade);

        helper = new GradeDBHelper(this);

        id = (EditText) findViewById(R.id.txtId);
        courseComponet = (EditText) findViewById(R.id.txtCourseComponet);
        mark = (EditText) findViewById(R.id.txtMark);
    }

    public void onClickAddGrade(View view) {
        helper.createGrade(Integer.parseInt(id.getText().toString()),
                courseComponet.getText().toString(), Float.parseFloat(mark.getText().toString()));
        finish();
    }
}
