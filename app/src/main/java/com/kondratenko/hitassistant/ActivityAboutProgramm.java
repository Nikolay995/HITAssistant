package com.kondratenko.hitassistant;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class ActivityAboutProgramm extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_programm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Служба підтримки: striker.kms@gmail.com", Snackbar.LENGTH_LONG)
                        .setAction("Написати", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent email = new Intent(Intent.ACTION_SEND);
                                email.setType("message/rfc822");
                                email.putExtra(Intent.EXTRA_EMAIL, "striker.kms@gmail.com");
                                email.putExtra(Intent.EXTRA_SUBJECT, "HiT Assistant");
                                email.putExtra(Intent.EXTRA_TEXT, "*Текст повідомлення*");
                                startActivity(email);
                            }
                        }).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView conv = (ImageView) findViewById(R.id.imageView13);
        Animation convert = AnimationUtils.loadAnimation(this, R.anim.scale);
        conv.startAnimation(convert);

        ImageView image_vk = (ImageView) findViewById(R.id.imageView14);
        Animation animation_vk = AnimationUtils.loadAnimation(this, R.anim.scale);
        image_vk.startAnimation(animation_vk);

        ImageView image_g = (ImageView) findViewById(R.id.imageView15);
        Animation animation_g = AnimationUtils.loadAnimation(this, R.anim.scale);
        image_g.startAnimation(animation_g);

        ImageView image_tw = (ImageView) findViewById(R.id.imageView16);
        Animation animation_tw = AnimationUtils.loadAnimation(this, R.anim.scale);
        image_tw.startAnimation(animation_tw);


    }
    public void govk(View v) {
        Uri uri = Uri.parse("http://www.vk.com/seiz3d/"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void gogoogle(View v) {
        Uri uri = Uri.parse("https://plus.google.com/u/0/118205145994034695388"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void gotwitter (View v) {
        Uri uri = Uri.parse("https://twitter.com/TomSeized"); // missing 'http://' will cause crashed
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

}
