package com.example.viserion.classassist.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.viserion.classassist.R;
import com.example.viserion.classassist.model.CourseInfo;

import java.util.ArrayList;
import java.util.List;

public class Adapter extends ArrayAdapter<CourseInfo> {
    private Context mContext;
    private List<CourseInfo> dataList = new ArrayList<>();
    public Adapter( Context context, ArrayList<CourseInfo> list) {
        super(context, 0,list);

        mContext = context;
        dataList = list;
    }
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItem = convertView;
        if (listItem == null){
            listItem = LayoutInflater.from(mContext).inflate(R.layout.custom_layout,parent,false);

            CourseInfo courseInfo = dataList.get(position);
            TextView code = (TextView) listItem.findViewById(R.id.code);
            code.setText(courseInfo.getCourseCode());

            TextView start = (TextView) listItem.findViewById(R.id.start);
            start.setText(courseInfo.getStartTime());

            TextView teacher = (TextView) listItem.findViewById(R.id.teacher);
            teacher.setText(courseInfo.getCourseTeacher());

            TextView day = (TextView) listItem.findViewById(R.id.day);
            day.setText(courseInfo.getDay());

            TextView room = (TextView) listItem.findViewById(R.id.room);
            room.setText(courseInfo.getClassRoom());

        }
        return listItem;

    }
}
