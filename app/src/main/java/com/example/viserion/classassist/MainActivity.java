package com.example.viserion.classassist;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.viserion.classassist.RoutineActivity.AddCourse;
import com.example.viserion.classassist.RoutineActivity.DayActivity;
import com.example.viserion.classassist.RoutineActivity.ListDataActivity;
import com.example.viserion.classassist.data.DatabaseHelper;
import com.example.viserion.classassist.model.CourseInfo;

import java.util.Calendar;

import static com.example.viserion.classassist.data.DatabaseHelper.TABLE_NAME;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    DatabaseHelper databaseHelper;
    Context context;

    Button sunBtn, monBtn, tueBtn, wedBtn,thuBtn,friBtn,satBtn;

    TextView output;
    CourseInfo courseInfo;

    String day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sunBtn = findViewById(R.id.sunday);
        monBtn = findViewById(R.id.monday);
        tueBtn = findViewById(R.id.tuesday);
        wedBtn = findViewById(R.id.wednesday);
        thuBtn = findViewById(R.id.thursday);
        friBtn = findViewById(R.id.friday);
        satBtn = findViewById(R.id.saturday);
        day = "Sunday";

        context = this;
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase = databaseHelper.getWritableDatabase();

        output = findViewById(R.id.output);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase1 = databaseHelper.getReadableDatabase();
        //Cursor cursor = myDatabase.show(day);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(MainActivity.this, AddCourse.class);
                startActivity(intent);
            }
        });

        sunBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Cursor cursor = databaseHelper.show(day);
//                if (cursor != null){
//                    cursor.moveToFirst();
//                }
//
//                StringBuilder builder = new StringBuilder();
//                do {
//                    String course_Code = cursor.getString(1);
//                    String course_Teacher = cursor.getString(3);
//                    String room = cursor.getString(4);
//                    String day = cursor.getString(5);
//                    String time = cursor.getString(6);
//
//                    builder.append("Serial "+cursor.getInt(0)+"\n"+"Course Code "+course_Code+"\n"+"Course Teacher "+course_Teacher+"\n"+"Room No "+room+"\n"+"Weekday "+day+"\n"+"Class Time "+time+"\n");
//                }while (cursor.moveToNext());
//
//                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
//                alert.setTitle("Today's Classes");
//                alert.setMessage(builder.toString());
//                alert.setNegativeButton("Ok", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                      dialog.cancel();
//                    }
//                });
//                alert.show();

//                Button b = (Button)v;
//                String buttonText = b.getText().toString();
//
//                CourseInfo courseInfo = new CourseInfo();
//                courseInfo.setDay1(buttonText);
//
                Intent intent = new Intent(MainActivity.this,DayActivity.class);
//               // intent.putExtra("Day",buttonText);
                startActivity(intent);

            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_routine) {
            Intent intent = new Intent(MainActivity.this,ListDataActivity.class);
            startActivity(intent);
            return true;
        }else if (id == R.id.action_attendance) {
//            Intent intent = new Intent(MainActivity.this,CourseListForAttendance.class);
//            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_settings) {
            // Handle the settings action
            Intent intent = new Intent(MainActivity.this,NotificationActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_about) {

        } else if (id == R.id.notification) {

        }  else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
