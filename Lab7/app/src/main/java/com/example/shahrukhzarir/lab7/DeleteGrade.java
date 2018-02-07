package com.example.shahrukhzarir.lab7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DeleteGrade extends AppCompatActivity {
    private ArrayList<Grade> gradeList = new ArrayList<>();
    ArrayList<String> stringGradeList;

    private ArrayAdapter<String> adapter;
    private Spinner gradeSpinner;
    private GradeDBHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_grade);

        gradeSpinner = (Spinner) findViewById(R.id.spinner);
        helper = new GradeDBHelper(this);
        gradeList = helper.getAllGrades();

        adapter = new ArrayAdapter<String>(getApplicationContext(),
                android.R.layout.simple_spinner_item, getStringGrades(gradeList));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        gradeSpinner.setAdapter(adapter);


    }

    public ArrayList<String> getStringGrades(ArrayList<Grade> data) {
        stringGradeList = new ArrayList<>();
        String entry;
        Grade grade;

        for (int i = 0; i < data.size(); i++) {
            grade = data.get(i);
            entry = Integer.toString(grade.getStudentId()) + "    " + grade.getCourseComponet() +
                    "    " + Float.toString(grade.getMark());
            stringGradeList.add(entry);
        }

        return stringGradeList;
    }

    public void onClickDelete(View view) {
        String[] deleteItem = gradeSpinner.getSelectedItem().toString().split("\\s+");

        helper.deleteGrade(Integer.parseInt(deleteItem[0]));
        finish();
    }
}
