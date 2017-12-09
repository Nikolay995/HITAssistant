package com.kondratenko.hitassistant;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

       // Typeface customfont = Typeface.createFromAsset(getAssets(),"light.ttf");
       // TextView toptext = (TextView)findViewById(R.id.textView3);
        //toptext.setTypeface(customfont);

        ImageView logo1 = (ImageView) findViewById(R.id.imageView9);
        Animation logo_scale = AnimationUtils.loadAnimation(this, R.anim.scale_fast);
        logo1.startAnimation(logo_scale);

        ImageView menu = (ImageView) findViewById(R.id.imageView3);
        Animation menu_alpha = AnimationUtils.loadAnimation(this, R.anim.alpha);
        menu.startAnimation(menu_alpha);

        ImageView notes = (ImageView) findViewById(R.id.imageView2);
        Animation notes_scale = AnimationUtils.loadAnimation(this, R.anim.scale);
        notes.startAnimation(notes_scale);

        TextView txt_up1 = (TextView) findViewById(R.id.textView12);
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        txt_up1.startAnimation(anim);

        TextView txt_up2 = (TextView) findViewById(R.id.textView3);
        Animation animation1 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        txt_up2.startAnimation(animation1);

        TextView txt_low = (TextView) findViewById(R.id.textView4);
        Animation animation2 = AnimationUtils.loadAnimation(this, R.anim.alpha);
        txt_low.startAnimation(animation2);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(MainActivity.this,UserHelp.class);
            startActivity(open);
        }
    };
private static long back_pressed;
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);

        } else if (back_pressed + 2000 > System.currentTimeMillis())
            super.onBackPressed();
                else
            Toast.makeText(getBaseContext(), "Натисніть ще раз щоб вийти ",
                    Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
    }
    public void AppExit()
    {
        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId())
        {
            case R.id.action_info:
                Intent info;
                info = new Intent(this,ActivityAboutProgramm.class); // виклик Activity2
                startActivity(info);
                return true;
            case R.id.action_share:
                Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"HIT Assistant");
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Чудовий додаток, рекомендую!");
                startActivity(Intent.createChooser(sharingIntent, "Поділитися!"));
                return true;
            default: return true;
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.calc) {
            Intent opencalc;
            opencalc = new Intent(this,Calculator.class);
            startActivity(opencalc);

        } else if (id == R.id.conv) {
            Intent converter;
           converter = new Intent(this,Converter.class);
            startActivity(converter);

        } else if (id == R.id.light) {
            Intent light;
            light = new Intent(this,Light.class);
            startActivity(light);

        } else if (id == R.id.calend) {
            Intent opencalend;
            opencalend = new Intent(this,Calendar.class);
            startActivity(opencalend);

        } else if (id == R.id.note) {
            Intent notepad;
            notepad = new Intent(this, Notepad.class);
            startActivity(notepad);

        } else if (id == R.id.todo) {
            Intent todo;
            todo = new Intent(this, Organizer.class);
            startActivity(todo);

        } else if (id == R.id.usergiude) {
            Intent userguide;
            userguide = new Intent(this,UserHelp.class);
            startActivity(userguide);

        } else if (id == R.id.aboutprog) {
            Intent info;
            info = new Intent(this,ActivityAboutProgramm.class);
            startActivity(info);

        } else if (id == R.id.quit) {
            AppExit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
