package com.phuocvo.clock_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class AlarmListAdapter extends ArrayAdapter<String> {

    public AlarmListAdapter(Context context, List<String> alarmList) {
        super(context, 0, alarmList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.alarm_item, parent, false);
        }

        String alarmTime = getItem(position);
        TextView alarmTimeTextView = convertView.findViewById(R.id.alarmTimeTextView);
        alarmTimeTextView.setText(alarmTime);

        return convertView;
    }
}

