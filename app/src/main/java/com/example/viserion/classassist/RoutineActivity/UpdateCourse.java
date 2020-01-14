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
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.viserion.classassist.R;
import com.example.viserion.classassist.data.DatabaseHelper;
import com.example.viserion.classassist.model.CourseInfo;

public class UpdateCourse extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText courseCodeEt, courseTitleEt, courseTeacherEt, classRoomEt,startTimeEt;
    Spinner daySpinner, startTimeSpinner;
    Button okBtn;

    DatabaseHelper databaseHelper;

    String item, startTimeSp, endTimeSp;

    TimePickerDialog timePickerDialog;

    int cHour,cMin;
    String amPm, hr,mn;

    int serial;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_course);

        final CourseInfo courseInfo = (CourseInfo) getIntent().getExtras().getSerializable("Course");

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();
        TimePicker timePicker = new TimePicker(this);

        serial = courseInfo.getIndex();

        courseCodeEt = findViewById(R.id.courseCode);
        courseTitleEt = findViewById(R.id.courseTitle);
        courseTeacherEt = findViewById(R.id.courseTeacher);
        classRoomEt = findViewById(R.id.classRoom);
        daySpinner = findViewById(R.id.spinner);
        startTimeEt = findViewById(R.id.startTimeHourEt);
        okBtn = findViewById(R.id.button);

        courseCodeEt.setText(courseInfo.getCourseCode());
        courseTitleEt.setText(courseInfo.getCourseTitle());
        courseTeacherEt.setText(courseInfo.getCourseTeacher());
        classRoomEt.setText(courseInfo.getClassRoom());
        startTimeEt.setText(courseInfo.getStartTime());


        daySpinner.setOnItemSelectedListener(this);
        //startTimeSpinner.setOnItemSelectedListener(this);


        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.WeekDays, android.R.layout.simple_spinner_dropdown_item);
        //ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.StartTime, android.R.layout.simple_spinner_dropdown_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
       // adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        daySpinner.setAdapter(adapter);
        //startTimeSpinner.setAdapter(adapter2);

        cHour = timePicker.getHour();
        cMin = timePicker.getMinute();
        startTimeEt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog = new TimePickerDialog(UpdateCourse.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        hr = String.valueOf(hourOfDay);
                        mn = String.valueOf(minute);
                        Toast.makeText(UpdateCourse.this, " "+hr, Toast.LENGTH_SHORT).show();
                        if (hourOfDay>=12){
                            amPm = "PM";
                        }else {
                            amPm = "AM";
                        }
                        startTimeEt.setText(hourOfDay+" : "+minute+" "+amPm);

                    }
                },cHour,cMin,true);

                timePickerDialog.show();
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (parent.getId()) {
            case R.id.spinner:
                item = parent.getItemAtPosition(position).toString();
                //Toast.makeText(this, "Select "+item, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void delete(View view) {
        databaseHelper.deleteData(serial);

        Intent intent = new Intent(UpdateCourse.this, ListDataActivity.class);
        startActivity(intent);
    }

    public void update(View view) {
        String courseCode, courseTitle, courseTeacher, classRoom, day, startTime;
        courseCode = courseCodeEt.getText().toString();
        courseTitle = courseTitleEt.getText().toString();
        courseTeacher = courseTeacherEt.getText().toString();
        classRoom = classRoomEt.getText().toString();
        day = item;
        startTime = startTimeEt.getText().toString();

        CourseInfo courseInfo = new CourseInfo(courseCode,courseTitle,courseTeacher,classRoom,day,startTime);

        try{

            databaseHelper.updateFinal(serial,courseInfo.getCourseCode(),courseInfo.getCourseTitle(),courseInfo.getCourseTeacher(),courseInfo.getClassRoom(),courseInfo.getDay(),courseInfo.getStartTime());
            //databaseHelper.updateFinal(serial,courseCode,courseTitle,courseTeacher,classRoom,day,startTime);
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();

        }catch (Exception e){
            Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
        }

        Intent intent = new Intent(UpdateCourse.this, ListDataActivity.class);
        startActivity(intent);
    }

}
