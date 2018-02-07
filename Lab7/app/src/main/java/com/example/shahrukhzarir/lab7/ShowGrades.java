package com.example.shahrukhzarir.lab7;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ShowGrades extends AppCompatActivity {
    private int ADD_GRADE = 111;
    private int DELETE_GRADE = 112;

    private GradeArrayAdapter gradeArrayAdapter;
    private GradeDBHelper helper;
    private List<Grade> grades;
    private ListView gradeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_grades);

        helper = new GradeDBHelper(this);
        grades = new ArrayList<>();
        gradeList = (ListView)findViewById(R.id.listView);
    }

    @Override
    protected void onStart() {
        super.onStart();

        grades = helper.getAllGrades();
        gradeArrayAdapter = new GradeArrayAdapter(this, grades);
        gradeList.setAdapter(gradeArrayAdapter);
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void onClickAdd(View view) {
        Intent i = new Intent(this, AddGrade.class);
        startActivity(i);
    }

    public void onClickDelete(View view) {
        Intent i = new Intent(this, DeleteGrade.class);
        startActivity(i);
    }
}
