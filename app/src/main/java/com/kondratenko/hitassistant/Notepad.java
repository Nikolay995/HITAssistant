package com.kondratenko.hitassistant;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class Notepad extends AppCompatActivity {
    private final static String FILENAME = "mytextfile.txt"; // ім'я файлу
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad);
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
        mEditText = (EditText) findViewById(R.id.editText3);
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Notepad.this,UserHelp.class);
            startActivity(open);
        }
    };
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_open:
                openFile(FILENAME);
                return true;
            case R.id.action_save:
                saveFile(FILENAME);
                return true;
            case R.id.action_settings:
                Intent intent = new Intent();
                intent.setClass(this, SettingsActivity.class); // preferences.xml
                startActivity(intent);
                return true;
            default:
                return true;
        }}
    // Метод для відкриття файлу
    private void openFile(String fileName) {
        try {
            InputStream inputStream = openFileInput(fileName);

            if (inputStream != null) {
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(isr);
                String line;
                StringBuilder builder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    builder.append(line + "\n");
                }
                inputStream.close();
                Toast.makeText(getBaseContext(), "Файл відкрито!", Toast.LENGTH_SHORT).show();
                mEditText.setText(builder.toString());

            }
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Помилка вiдкриття: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences prefs = PreferenceManager
                .getDefaultSharedPreferences(this);
        // читаємо встановлене значеня з CheckBoxPreference
        if (prefs.getBoolean(getString(R.string.pref_openmode), false)) {
            openFile(FILENAME);
        }
        // читаємо розмір шрифту з EditTextPreference
    float fSize = Float.parseFloat(prefs.getString(
            getString(R.string.pref_size), "20"));
        // читаємо стиль тексту з ListPreference
        String regular = prefs.getString(getString(R.string.pref_style), "");
        int typeface = Typeface.NORMAL;
        if (regular.contains("Звичайний"))
            typeface += Typeface.NORMAL;
        if (regular.contains("Напівжирний"))
            typeface += Typeface.BOLD;
        if (regular.contains("Курсив"))
            typeface += Typeface.ITALIC;

        // зміна настройок в EditText
        mEditText.setTypeface(null, typeface);
        // збереження налаштувань в текстовому полі
        mEditText.setTextSize(fSize); }
        // Метод для збереження файлу

    private void saveFile(String fileName) {
        try {
            OutputStream outputStream = openFileOutput(fileName, 0);
            OutputStreamWriter osw = new OutputStreamWriter(outputStream);
            osw.write(mEditText.getText().toString());
            osw.close();
            Toast.makeText(getBaseContext(), "Файл збережено!", Toast.LENGTH_SHORT).show();
        } catch (Throwable t) {
            Toast.makeText(getApplicationContext(),
                    "Помилка: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}

