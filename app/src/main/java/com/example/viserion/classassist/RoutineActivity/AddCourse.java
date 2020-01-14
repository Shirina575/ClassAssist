package com.example.viserion.classassist.RoutineActivity;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.viserion.classassist.R;
import com.example.viserion.classassist.data.DatabaseHelper;
import com.example.viserion.classassist.data.TimeHelper;
import com.example.viserion.classassist.model.CourseInfo;

public class AddCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText courseCodeEt, courseTitleEt, courseTeacherEt, classRoomEt,startTimeHourEt, startTimeMinEt;
    Spinner daySpinner, startTimeSpinner;
    Button okBtn;

    TextView startTime;

    String item, start;
    CourseInfo courseInfo,courseInfo1;

    DatabaseHelper myDatabase;
    TimeHelper timeHelper;
    TimePickerDialog timePickerDialog;

    int cHour,cMin,hr,mn;
    String amPm;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);
        myDatabase = new DatabaseHelper(this);
        timeHelper = new TimeHelper(this);
        SQLiteDatabase sqLiteDatabase = myDatabase.getWritableDatabase();

        TimePicker timePicker = new TimePicker(this);

        courseCodeEt = findViewById(R.id.courseCode);
        courseTitleEt = findViewById(R.id.courseTitle);
        courseTeacherEt = findViewById(R.id.courseTeacher);
        classRoomEt = findViewById(R.id.classRoom);
        daySpinner = findViewById(R.id.spinner);
        //startTimeSpinner = findViewById(R.id.spinner2);
        okBtn = findViewById(R.id.button);
        startTimeHourEt = findViewById(R.id.startTimeHourEt);
        startTime = findViewById(R.id.startTime);

        daySpinner.setOnItemSelectedListener(this);
        //startTimeSpinner.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.WeekDays, android.R.layout.simple_spinner_dropdown_item);
        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.StartTime, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter);
        //startTimeSpinner.setAdapter(adapter2);

        cHour = timePicker.getHour();
        cMin = timePicker.getMinute();

        startTimeHourEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(AddCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hr = hourOfDay;
                        mn = minute;
                        Toast.makeText(AddCourse.this, " "+hr, Toast.LENGTH_SHORT).show();
                        if (hourOfDay>=12){
                            amPm = "PM";
                        }else {
                            amPm = "AM";
                        }
                        startTimeHourEt.setText(hourOfDay+" : "+minute+" "+amPm);

                    }
                },cHour,cMin,false);

                timePickerDialog.show();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String courseCode;
                String courseTitle;
                String courseTeacher;
                String classRoom;
                String day;
                String startTime;

                courseCode = courseCodeEt.getText().toString();
                courseTitle = courseTitleEt.getText().toString();
                courseTeacher = courseTeacherEt.getText().toString();
                classRoom = classRoomEt.getText().toString();
                day = item;
                startTime =startTimeHourEt.getText().toString();



                courseInfo = new CourseInfo(courseCode, courseTitle,courseTeacher,classRoom,day,startTime);
                courseInfo1 = new CourseInfo(hr,mn);
                myDatabase.insertData(courseInfo);
                timeHelper.insertData(courseInfo1);

                Intent intent = new Intent(AddCourse.this, ListDataActivity.class);
                startActivity(intent);

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()){
            case R.id.spinner:
                item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, "Select "+item, Toast.LENGTH_SHORT).show();
                break;
//            case R.id.spinner2:
//                startTimeSp = parent.getItemAtPosition(position).toString();
//                //Toast.makeText(this, "Select "+startTimeSp, Toast.LENGTH_SHORT).show();
//                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

}