package com.kondratenko.hitassistant;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CalendarView;
import android.widget.Toast;

public class Calendar extends AppCompatActivity {
    CalendarView calendar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        calendar=(CalendarView) findViewById(R.id.calendar1);
        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener()
        {
        @Override
        public void onSelectedDayChange (CalendarView view, int year, int month, int dayOfMonth)
        {
            month=month+1;
            Toast.makeText(getApplicationContext(),dayOfMonth + "/" + month + "/" +year, Toast.LENGTH_LONG).show();
        }
        });

               getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

}