package com.example.viserion.classassist.RoutineActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.viserion.classassist.R;
import com.example.viserion.classassist.adapter.Adapter;
import com.example.viserion.classassist.data.DatabaseHelper;
import com.example.viserion.classassist.model.CourseInfo;

import java.util.ArrayList;

import static com.example.viserion.classassist.data.DatabaseHelper.TABLE_NAME;

public class DayActivity extends AppCompatActivity {
    TextView output;
    DatabaseHelper myDatabase;
    CourseInfo courseInfo;

    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        output = findViewById(R.id.output);
        day = "Sunday";

        myDatabase = new DatabaseHelper(DayActivity.this);
        SQLiteDatabase sqLiteDatabase = myDatabase.getReadableDatabase();
        Cursor cursor = myDatabase.show(day);

        if (cursor != null){
            cursor.moveToFirst();
        }

        StringBuilder builder = new StringBuilder();
        do {
            String course_Code = cursor.getString(1);
            String course_Teacher = cursor.getString(3);
            String room = cursor.getString(4);
            String day_1 = cursor.getString(5);
            String time = cursor.getString(6);

            builder.append("Serial "+cursor.getInt(0)+"\n"+"Course Code "+course_Code+"\n"+"Course Teacher "+course_Teacher+"\n"+"Room No "+room+"\n"+"Weekday "+day_1+"\n"+"Class Time "+time+"\n");
        }while (cursor.moveToNext());

        output.setText(builder.toString());

    }
}
