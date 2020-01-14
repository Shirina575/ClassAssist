package com.example.viserion.classassist.RoutineActivity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.viserion.classassist.R;
import com.example.viserion.classassist.adapter.Adapter;
import com.example.viserion.classassist.data.DatabaseHelper;
import com.example.viserion.classassist.model.CourseInfo;

import java.util.ArrayList;

public class ListDataActivity extends AppCompatActivity {
    ListView listView;
    FloatingActionButton addBtn;

    DatabaseHelper myDatabase;
    private Adapter adapter;
    CourseInfo courseInfo;
    int serial;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        listView = findViewById(R.id.listView);
        addBtn = findViewById(R.id.fab);

        myDatabase = new DatabaseHelper(this);
        ArrayList<CourseInfo> dataList = new ArrayList<>();
        dataList= (ArrayList<CourseInfo>) myDatabase.getAllInfo();
        adapter = new Adapter(this,dataList);
        listView.setAdapter(adapter);

        final ArrayList<CourseInfo> finalDataList = dataList;
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                CourseInfo courseInfo = finalDataList.get(position);

                Intent intent = new Intent(ListDataActivity.this, UpdateCourse.class);
                intent.putExtra("Course",courseInfo);

                startActivity(intent);
            }
        });

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListDataActivity.this, AddCourse.class);
                startActivity(intent);
            }
        });
    }
}
