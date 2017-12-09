package com.kondratenko.hitassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Converter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_converter);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view, "Відкрити довідник користувача?", Snackbar.LENGTH_SHORT)
                        .setAction("Так", snackbarOnClickListener).show();

            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    public void mytemp(View view){
        Intent opentemp;
        opentemp = new Intent(this,Conv_temp.class);
        startActivity(opentemp);
    }
    public void mylenght(View view){
        Intent openlenght;
        openlenght = new Intent(this,Conv_lenght.class);
        startActivity(openlenght);
    }
    public void myweight(View view){
        Intent openweight;
        openweight = new Intent(this,Conv_weight.class);
        startActivity(openweight);}

    public void mypress(View view){
        Intent openpress;
        openpress = new Intent(this,Conv_press.class);
        startActivity(openpress);}

    public void myspeed(View view){
        Intent openspeed;
        openspeed = new Intent(this,Conv_speed.class);
        startActivity(openspeed);}
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Converter.this,UserHelp.class);
            startActivity(open);
        }
    };



}
