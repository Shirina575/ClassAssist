package com.example.viserion.classassist.model;

import java.io.Serializable;

public class CourseInfo implements Serializable {

    private int index;
    private String courseCode;
    private String courseTitle;
    private String courseTeacher;
    private String classRoom;
    private String day;
    private String day1;
    private String startTime;

    private  int hour;
    private  int minute;

    public CourseInfo(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    public CourseInfo(){

    }

    public CourseInfo(String courseCode, String courseTitle, String courseTeacher, String classRoom, String day, String startTime){

        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseTeacher = courseTeacher;
        this.classRoom = classRoom;
        this.day = day;
        this.startTime = startTime;
    }


    public CourseInfo(int index, String courseCode, String courseTitle, String courseTeacher, String classRoom, String day, String startTime){

        this.index = index;
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.courseTeacher = courseTeacher;
        this.classRoom = classRoom;
        this.day = day;
        this.startTime = startTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    public String getCourseTeacher() {
        return courseTeacher;
    }

    public void setCourseTeacher(String courseTeacher) {
        this.courseTeacher = courseTeacher;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String toString() {

        return courseCode;
    }

    public String getDay1() {
        return day1;
    }

    public void setDay1(String day1) {
        this.day1 = day1;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getMinute() {
        return minute;
    }

    public void setMinute(int minute) {
        this.minute = minute;
    }
}
