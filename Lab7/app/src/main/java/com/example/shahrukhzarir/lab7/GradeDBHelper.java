package com.example.shahrukhzarir.lab7;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by amuna95 on 11/2/2017.
 */

public class GradeDBHelper extends SQLiteOpenHelper {
    static final int DATABASE_VERSION = 1;
    static final String TABLE = "grades";
    static final String CREATE_STATEMENT = "CREATE TABLE Grades(\n" +
            " studentId int primary key,\n" +
            " courseComponent varchar(100) not null,\n" +
            " mark decimal not null);\n";

    public GradeDBHelper(Context context) {
        super(context, "grades", null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_STATEMENT);
    }


    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Not required for lab
    }

    // DELETE
    public boolean deleteGrade(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        int numRows = db.delete(TABLE, "studentId = ?", new String[] { "" + id });

        return (numRows == 1);
    }

    public Grade createGrade(int studentId,
                                 String courseComponent,
                                 float mark) {
        // create a new entity object (Grade)
        Grade grade = new Grade(studentId, courseComponent, mark);

        // put that data into the database
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues newValues = new ContentValues();
        newValues.put("studentId", studentId);
        newValues.put("courseComponent", courseComponent);
        newValues.put("mark", mark);

        long id = db.insert(TABLE, null, newValues);

        return grade;
    }

    public ArrayList<Grade> getAllGrades() {
        ArrayList<Grade> grades = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String[] columns = new String[] {"studentId", "courseComponent", "mark"};
        String where = "";
        String[] whereArgs = new String[] {  };
        Cursor cursor = db.query(TABLE, columns, where, whereArgs, "", "", "mark");

        cursor.moveToFirst();
        do {
            if (!cursor.isAfterLast()) {
                int id = cursor.getInt(0);
                String courseCompnent = cursor.getString(1);
                Float mark = cursor.getFloat(2);

                Grade grade = new Grade(id, courseCompnent, mark);

                grades.add(grade);
            }

            cursor.moveToNext();
        } while (!cursor.isAfterLast());

        Log.i("SQLite", "getAllGrades(): num = " + grades.size());

        return grades;
    }

}
