package com.example.viserion.classassist.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.viserion.classassist.model.CourseInfo;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper  extends SQLiteOpenHelper {
        public static final String DATABASE_NAME = "Class_Routine.db";
        public static final String TABLE_NAME = "Class_Routine";
        public static final String SERIAL = "Serial";
        public static final String COURSE_CODE = "Course_Code";
        public static final String COURSE_TITLE = "Course_Title";
        public static final String COURSE_TEACHER = "Course_Teacher";
        public static final String CLASS_ROOM = "Class_Room";
        public static final String DAY = "Day";
        public static final String START_TIME = "Start_Time";
        public static final int version = 1;

        Context context;
        public DatabaseHelper( Context context) {
            super(context, DATABASE_NAME, null, version);
            this.context = context;
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            String sql = "create table "+TABLE_NAME+
                    "( "+SERIAL+" integer primary key autoincrement, "+COURSE_CODE+
                    " varchar(50), "+COURSE_TITLE+
                    " varchar(50), "+COURSE_TEACHER+
                    " varchar(50), "+CLASS_ROOM+
                    " varchar(50), "+DAY+
                    " varchar(50), "+START_TIME+
                    " varchar(50) )";

            try {
                db.execSQL(sql);
                //Toast.makeText(context, "Table create successful!!", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(context, "Failed!!", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

            String sql = "drop table if exists "+TABLE_NAME;

            try {
                db.execSQL(sql);
                Toast.makeText(context, "On Upgrade is called", Toast.LENGTH_SHORT).show();
            }catch (Exception e){
                Toast.makeText(context, "Exception : "+e, Toast.LENGTH_SHORT).show();
            }

        }

        public void insertData(CourseInfo courseInfo){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            String sql = " insert into "+TABLE_NAME+
                    "(Serial, Course_Code, Course_Title, Course_Teacher, Class_Room, Day, Start_Time) values(null, ' " +courseInfo.getCourseCode()+ " ',' " +courseInfo.getCourseTitle()+ " ',' "+courseInfo.getCourseTeacher()+ " ',' "+courseInfo.getClassRoom()+ " ',' "+courseInfo.getDay()+" ',' "+courseInfo.getStartTime()+" ' );";


            try {

                sqLiteDatabase.execSQL(sql);

               // Toast.makeText(context, "Data insert Successful!!", Toast.LENGTH_SHORT).show();

            }catch (Exception e){

                Toast.makeText(context, "Failed!!", Toast.LENGTH_SHORT).show();

            }

        }


        public Cursor showAllData(){

            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

            String sql = "select * from "+TABLE_NAME;

            Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

            return cursor;
        }

        public Cursor updateData(int serial){
            SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
            String sql = "update "+TABLE_NAME+" where serial = "+serial;
            Cursor cursor = sqLiteDatabase.rawQuery(sql,null);
            return  cursor;

        }


    public void updateFinal(int serial,String Course_Code,String Course_Title,String Course_Teacher,String Class_Room,String Day, String Start_Time){

        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        String sql = "update "+TABLE_NAME+" set Course_Code = '"+Course_Code+"',Course_Title = '"+Course_Title+"',Course_Teacher = '"+Course_Teacher+"',Class_Room = '"+Class_Room+"',Day = '"+Day+"',Start_Time = '"+Start_Time+"' where serial = "+serial;


        sqLiteDatabase.execSQL(sql);



    }
        public void deleteData(int serial){

            SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
            String sql = "delete from "+TABLE_NAME+" where Serial = "+serial;
            try{

                sqLiteDatabase.execSQL(sql);
                Toast.makeText(context, "Deleted successfully!!!", Toast.LENGTH_SHORT).show();

            }catch (Exception e){

                Toast.makeText(context, "Failed!!", Toast.LENGTH_SHORT).show();

            }



        }

        public List<CourseInfo> getAllInfo() {

            List<CourseInfo> courseInfoList = new ArrayList<>();
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME,null);

            if (cursor.moveToFirst()){

                do {

                    int serial = cursor.getInt(0);
                    String  courseCode = cursor.getString(1);
                    String  courseTitle = cursor.getString(2);
                    String  courseTeacher = cursor.getString(3);
                    String  classRoom = cursor.getString(4);
                    String  day = cursor.getString(5);
                    String  startTime = cursor.getString(6);

                    CourseInfo courseInfo = new CourseInfo(serial, courseCode, courseTitle, courseTeacher, classRoom, day, startTime);
                    courseInfoList.add(courseInfo);
                }while (cursor.moveToNext());
            }

            return courseInfoList;

        }
        public  Cursor show(String day){
            SQLiteDatabase sqLiteDatabase = getReadableDatabase();
            String sql = "select * from Class_Routine where Day ="+day;

            Cursor cursor = sqLiteDatabase.rawQuery(sql,null);

            return cursor;
        }
    }
