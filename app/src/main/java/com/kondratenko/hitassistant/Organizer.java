package com.kondratenko.hitassistant;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.kondratenko.hitassistant.db.TaskContract;
import com.kondratenko.hitassistant.db.TaskDBHelper;

public class Organizer extends AppCompatActivity {

    private TaskDBHelper helper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_organizer);
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
        updateUI();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_todo,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Додати пункт");
                builder.setMessage("Що б ви хотіли зробити?");
                final EditText inputField = new EditText(this);
                builder.setView(inputField);
                builder.setPositiveButton("Додати", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (inputField.getText().toString().equals("")){
                            Toast toast = Toast.makeText(getApplicationContext(), "Порожні завдання видаляютсья автоматично", Toast.LENGTH_LONG);
                            toast.show();
                            return;
                        }
                        String task = inputField.getText().toString();
                        helper = new TaskDBHelper(Organizer.this);
                        SQLiteDatabase db = helper.getWritableDatabase();
                        ContentValues values = new ContentValues();
                        values.clear();
                        values.put(TaskContract.Columns.TASK,task);
                        db.insertWithOnConflict(TaskContract.TABLE,null,values,SQLiteDatabase.CONFLICT_IGNORE);
                        updateUI();
                    }
                });
                builder.setNegativeButton("Відміна",null);
                builder.create().show();
                return true;
            default:
                return false;
        }
    }

    private void updateUI() {
        helper = new TaskDBHelper(Organizer.this);
        SQLiteDatabase sqlDB = helper.getReadableDatabase();
        Cursor cursor = sqlDB.query(TaskContract.TABLE,
                new String[]{TaskContract.Columns._ID, TaskContract.Columns.TASK},
                null, null, null, null, null);

        ListAdapter listAdapter = new SimpleCursorAdapter(
                this,
                R.layout.task_view,
                cursor,
                new String[]{TaskContract.Columns.TASK},
                new int[]{R.id.taskTextView},
                0
        );

        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(listAdapter);
    }

    public void onDoneButtonClick(View view) {
        View v = (View) view.getParent();
        TextView taskTextView = (TextView) v.findViewById(R.id.taskTextView);
        String task = taskTextView.getText().toString();

        String sql = String.format("DELETE FROM %s WHERE %s = '%s'",
                TaskContract.TABLE,
                TaskContract.Columns.TASK,
                task);

        helper = new TaskDBHelper(Organizer.this);
        SQLiteDatabase sqlDB = helper.getWritableDatabase();
        sqlDB.execSQL(sql);
        updateUI();
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Organizer.this,UserHelp.class);
            startActivity(open);
        }
    };
}
