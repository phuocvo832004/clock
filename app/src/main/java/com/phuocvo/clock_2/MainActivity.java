package com.phuocvo.clock_2;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private ImageView iconClock, iconAlarm;
    private LinearLayout clockModeLayout, alarmModeLayout;
    private ListView alarmListView;
    private FloatingActionButton addAlarmButton;
    private CustomAnalogClock customAnalogClock;
    private TextView cityName, timeDisplay;

    // List to hold alarm times
    private List<String> alarmList = new ArrayList<>();
    private AlarmListAdapter alarmListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        iconClock = findViewById(R.id.iconClock);
        iconAlarm = findViewById(R.id.iconAlarm);
        clockModeLayout = findViewById(R.id.clockModeLayout);
        alarmModeLayout = findViewById(R.id.alarmModeLayout);
        alarmListView = findViewById(R.id.alarmListView);
        addAlarmButton = findViewById(R.id.addAlarmButton);
        customAnalogClock = findViewById(R.id.customAnalogClock);
        cityName = findViewById(R.id.cityName);
        timeDisplay = findViewById(R.id.timeDisplay);

        // Initially show Clock mode
        showClockMode();

        // Set onClick listeners for the Clock and Alarm icons
        iconClock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClockMode();
            }
        });

        iconAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlarmMode();
            }
        });

        // Set up Alarm List Adapter
        alarmListAdapter = new AlarmListAdapter(this, alarmList);
        alarmListView.setAdapter(alarmListAdapter);

        // Add Alarm Button click listener
        addAlarmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alarmList.size() >= 5) {
                    Toast.makeText(MainActivity.this, "Bạn chỉ có thể đặt tối đa 5 báo thức.", Toast.LENGTH_SHORT).show();
                } else {
                    showTimePickerDialog();
                }
            }
        });
    }

    private void showClockMode() {
        clockModeLayout.setVisibility(View.VISIBLE);
        alarmModeLayout.setVisibility(View.GONE);
    }

    private void showAlarmMode() {
        clockModeLayout.setVisibility(View.GONE);
        alarmModeLayout.setVisibility(View.VISIBLE);
    }

    // Show TimePicker dialog to add new alarm
    private void showTimePickerDialog() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(MainActivity.this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        String alarmTime = String.format("%02d:%02d", hourOfDay, minute);
                        alarmList.add(alarmTime);
                        alarmListAdapter.notifyDataSetChanged();
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

}
