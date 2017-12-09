package com.kondratenko.hitassistant;

import android.app.ListActivity;
import android.content.Intent;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.widget.ImageView;
import android.widget.Toast;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ToggleButton;

public class Light extends AppCompatActivity {

    private ToggleButton sbt;
    private Camera camera;
    private Parameters params;
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light);
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
    @Override
    public void onDestroy(){
        super.onDestroy();
    }

    public void flbt(View view) {
        image = (ImageView)findViewById(R.id.view_light);
        sbt=(ToggleButton) findViewById(R.id.toggleButton);
        if (sbt.isChecked())
        {
            flashws(true);
            image.setImageResource(R.drawable.light_on);
        }
        else
        {
            flashws(false);
            image.setImageResource(R.drawable.light_off);
        };
    }
    public void alert(String txt){
        Toast toast = Toast.makeText(getApplicationContext(),txt, Toast.LENGTH_SHORT);
        toast.show();
    }
    public void flashws(boolean sw){
        if (camera != null) {camera.release();};
        try {
            camera = Camera.open();
            params = camera.getParameters();
            if (sw) {
                params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                camera.setParameters(params);
                camera.startPreview();
                alert("Ліхтарик увімкнено!");
            } else {
                params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                camera.setParameters(params);
                camera.stopPreview();
                alert("Ліхтарик вимкнено!");
            };
        } catch (RuntimeException e) {
            alert("Помилка доступу до камери");
        }
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Light.this,UserHelp.class);
            startActivity(open);
        }
    };

}
