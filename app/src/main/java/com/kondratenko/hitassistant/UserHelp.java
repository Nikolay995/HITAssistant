package com.kondratenko.hitassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class UserHelp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_help);
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
    }
}
