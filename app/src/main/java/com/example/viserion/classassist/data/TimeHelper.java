package com.example.viserion.classassist.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.example.viserion.classassist.model.CourseInfo;

public class TimeHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Alarm_Time.db";
    public static final String TABLE_NAME = "Time";
    public static final String SERIAL = "Serial";
    public static final String HOUR = "Hour";
    public static final String MINUTE = "Minute";
    public static final int VERSION = 1;

    Context context;
    public TimeHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table "+TABLE_NAME+
                "( "+SERIAL+" integer primary key autoincrement, "+HOUR+
                " integer, "+MINUTE+
                " integer )";

        try {
            db.execSQL(sql);
            Toast.makeText(context, "Time_Table create successful!!", Toast.LENGTH_SHORT).show();
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
                "(Serial, Hour, Minute) values(null, ' " +courseInfo.getHour()+ " ',' " +courseInfo.getMinute()+ " ' );";


        try {

            sqLiteDatabase.execSQL(sql);

            Toast.makeText(context, "Data insert Successful!!", Toast.LENGTH_LONG).show();

        }catch (Exception e){

            Toast.makeText(context, "Failed!!", Toast.LENGTH_SHORT).show();

        }

    }
}
