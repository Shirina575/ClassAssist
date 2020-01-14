package com.example.viserion.classassist.model;

import java.io.Serializable;

public class AttendModel implements Serializable {

    private int index;
    private String courseTitle;
    private String teacherName;

    public AttendModel(){

    }

    public AttendModel(int index, String courseTitle,String teacherName){
        this.index = index;
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
    }

    public AttendModel(String courseTitle,String teacherName){
        this.courseTitle = courseTitle;
        this.teacherName = teacherName;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String toString() {

        return courseTitle;
    }
}
