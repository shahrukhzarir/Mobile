package com.example.shahrukhzarir.lab7;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by amuna95 on 11/2/2017.
 */

public class GradeArrayAdapter extends ArrayAdapter<Grade> {
    private List<Grade> data;
    private Context context;

    public GradeArrayAdapter(Context context, List<Grade> data) {
        super(context, R.layout.grade_list_item, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View reusableView, ViewGroup parent) {
        Grade grade = data.get(position);

        Log.i("SQLite", "Showing - " + grade);

        if (reusableView == null) {
            // create a new view (this is the first item)
            LayoutInflater inflater = LayoutInflater.from(context);
            reusableView = inflater.inflate(R.layout.grade_list_item, parent, false);
        }

        TextView id = (TextView)reusableView.findViewById(R.id.lblId);
        id.setText(Integer.toString(grade.getStudentId()));

        TextView courseComponent = (TextView)reusableView.findViewById(R.id.lblCourse);
        courseComponent.setText(grade.getCourseComponet());

        TextView mark = (TextView)reusableView.findViewById(R.id.lblMark);
        mark.setText(String.valueOf(grade.getMark()));

        return reusableView;
    }
}
