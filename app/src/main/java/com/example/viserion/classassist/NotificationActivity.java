package com.example.viserion.classassist;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.viserion.classassist.model.CourseInfo;

import java.util.Calendar;

public class NotificationActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    Switch notification;
    CourseInfo courseInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        notification = findViewById(R.id.switch1);
        notification.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (notification.isChecked()){

            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 21);
            calendar.set(Calendar.SECOND, 0);

            Intent notificationmassage = new Intent(getApplicationContext(),NotificationClass.class);

            //This is alarm manager
            PendingIntent pi = PendingIntent.getBroadcast( this, 100 , notificationmassage, PendingIntent.FLAG_UPDATE_CURRENT);
            AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
            am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                    AlarmManager.INTERVAL_DAY, pi);

            Toast.makeText(this, "Start Alarm", Toast.LENGTH_LONG).show();


        }else {

            Toast.makeText(this, "No Alarm", Toast.LENGTH_LONG).show();

        }
    }
}
