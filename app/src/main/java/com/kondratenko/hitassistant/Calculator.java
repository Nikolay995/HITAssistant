package com.kondratenko.hitassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Calculator extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
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

    ArrayList<String> arrayList = new ArrayList<String>();
    String string = "";
    String string1 = "";
    public void OnClick1 (View v){
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        Button button = (Button) v;
        string = (String) button.getText().toString();
        if(!string.contains("+") &&!string.contains("-")&& !string.contains("*")&& !string.contains("/")  ){
            string1 = string1+string;
            if(arrayList.size()>0){
                arrayList.remove((arrayList.size()-1));
            }
            arrayList.add(string1);
        }
        else {
            arrayList.add(string);
            arrayList.add(string);
            string1="";
        }
        textView10.setText(textView10.getText().toString()+string);
        //textView10.setText(arrayList.toString());
    }

    public void onClick (View v){
        TextView textView9 = (TextView)findViewById(R.id.textView9);
        int calc = 0;
        int c = arrayList.size();
        while (c!=1){
            if (c>3){
                if (arrayList.get(3).contains("*") ||arrayList.get(3).contains("/")){
                    if (arrayList.get(3).contains("*")){calc = Integer.parseInt(arrayList.get(2))*Integer.parseInt(arrayList.get(4));}
                    if (arrayList.get(3).contains("/")){calc = Integer.parseInt(arrayList.get(2))/Integer.parseInt(arrayList.get(4));}
                    arrayList.remove(2);
                    arrayList.remove(2);
                    arrayList.remove(2);
                    arrayList.add(2,Integer.toString(calc));
                    c= arrayList.size();
                }
                else {
                    if (arrayList.get(1).contains("+")){calc = Integer.parseInt(arrayList.get(0))+Integer.parseInt(arrayList.get(2));}
                    if (arrayList.get(1).contains("-")){calc = Integer.parseInt(arrayList.get(0))-Integer.parseInt(arrayList.get(2));}
                    if (arrayList.get(1).contains("*")){calc = Integer.parseInt(arrayList.get(0))*Integer.parseInt(arrayList.get(2));}
                    if (arrayList.get(1).contains("/")){calc = Integer.parseInt(arrayList.get(0))/Integer.parseInt(arrayList.get(2));}
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.remove(0);
                    arrayList.add(0,Integer.toString(calc));
                    c= arrayList.size();
                }

            }
            else {
                if (arrayList.get(1).contains("+")){calc = Integer.parseInt(arrayList.get(0))+Integer.parseInt(arrayList.get(2));}
                if (arrayList.get(1).contains("-")){calc = Integer.parseInt(arrayList.get(0))-Integer.parseInt(arrayList.get(2));}
                if (arrayList.get(1).contains("*")){calc = Integer.parseInt(arrayList.get(0))*Integer.parseInt(arrayList.get(2));}
                if (arrayList.get(1).contains("/")){calc = Integer.parseInt(arrayList.get(0))/Integer.parseInt(arrayList.get(2));}

                arrayList.remove(0);
                arrayList.remove(0);
                arrayList.remove(0);
                arrayList.add(0,Integer.toString(calc));
                c= arrayList.size();
            }
textView9.setText(Integer.toString(calc));
        }

    }
    public void clear(View v) {
        TextView textView9 = (TextView) findViewById(R.id.textView9);
        TextView textView10 = (TextView) findViewById(R.id.textView10);
        string1="";
        string="";
        textView9.setText("0");
        textView10.setText("");
        arrayList.clear();

    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Calculator.this,UserHelp.class);
            startActivity(open);
        }
    };
}
