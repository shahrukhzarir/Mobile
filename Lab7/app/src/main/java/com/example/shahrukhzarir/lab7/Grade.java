package com.example.shahrukhzarir.lab7;

/**
 * Created by amuna95 on 11/2/2017.
 */

public class Grade {
    private int studentId;
    private String courseComponet;
    private float mark;

    public Grade(int _studentId, String _courseComponet, float _mark) {
        this.studentId = _studentId;
        this.courseComponet = _courseComponet;
        this.mark = _mark;
    }

    public int getStudentId() {
        return studentId;
    }

    public String getCourseComponet() {
        return courseComponet;
    }

    public float getMark() {
        return mark;
    }
}
