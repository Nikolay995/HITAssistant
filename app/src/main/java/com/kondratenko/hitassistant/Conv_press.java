package com.kondratenko.hitassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class Conv_press extends AppCompatActivity {

    private Spinner spinner_from;
    private Spinner spinner_to;
    private TextView textView_from;
    private EditText editText_currency;
    private TextView textView_to;
    private TextView textView_equal;
    private double unit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv_press);
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

        spinner_from = (Spinner) findViewById(R.id.spinner_from);
        spinner_to = (Spinner) findViewById(R.id.spinner_to);
        Button button_convert = (Button) findViewById(R.id.convert);
        textView_from = (TextView) findViewById(R.id.textView_from);
        textView_to = (TextView) findViewById(R.id.textView_to);
        textView_equal = (TextView) findViewById(R.id.textView11);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.press_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
        spinner_from.setAdapter(adapter);
        spinner_to.setAdapter(adapter);

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                editText_currency = (EditText) findViewById(R.id.input_value);
                if (editText_currency.getText().toString().equals(""))
                { // перевірка на "непорожність" :)
                    Toast toast = Toast.makeText(getApplicationContext(), "Будь ласка, введіть значення", Toast.LENGTH_LONG);
                    toast.show();
                    textView_equal.setText("");
                    textView_from.setText("");
                    textView_to.setText("");
                    return;
                }
                float input_value = Float.parseFloat(editText_currency.getText().toString());
                String select1 = spinner_from.getSelectedItem().toString();
                String select2 = spinner_to.getSelectedItem().toString();


            if (select1.equals("Паскаль"))
            {
                if (select2.equals("Атмосфера"))
                {
                    textView_from.setText(String.valueOf(input_value) + " Па ");
                    unit1 = input_value * 0.00000986923;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 4)) + " ат. ");
                } else if (select2.equals("Бар"))
                {
                    textView_from.setText(String.valueOf(input_value) + " Па ");
                    textView_equal.setText("=");
                    unit1 = input_value * 0.00001;
                    textView_to.setText(String.valueOf(roundUp(unit1, 5)) + " бар ");
                } else if (select2.equals("Паскаль"))
                {
                    textView_from.setText(String.valueOf(input_value + " Па "));
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(input_value) + " Па ");
                } else if (select2.equals("Торр"))
                {
                    textView_from.setText(String.valueOf(input_value) + " Па ");
                    textView_equal.setText("=");
                    unit1 = input_value * 0.00750062;
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " тр. ");
                }
            } else if(select1.equals("Атмосфера"))
            {
                if (select2.equals("Паскаль"))
                {
                    textView_from.setText(String.valueOf(input_value) + " ат. ");
                    unit1 = input_value * 101325.0;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " Па ");
                } else if (select2.equals("Бар"))
                {
                    textView_from.setText(String.valueOf(input_value) + " ат. ");
                    unit1 = input_value * 1.01325;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " бар ");
                }else if (select2.equals("Атмосфера"))
                {
                    textView_from.setText(String.valueOf(input_value + " ат. "));
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(input_value) + " ат. ");
                } else if (select2.equals("Торр"))
                {
                    textView_from.setText(String.valueOf(input_value) + " ат. ");
                    textView_equal.setText("=");
                    unit1 = input_value *760;
                    textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " тр. ");
                }
            }else if(select1.equals("Бар"))
            {
                if (select2.equals("Атмосфера"))
                {
                    textView_from.setText(String.valueOf(input_value) + " бар ");
                    unit1 = input_value * 0.986923;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " ат. ");
                }else if (select2.equals("Паскаль"))
                {
                    textView_from.setText(String.valueOf(input_value) + " бар ");
                    unit1 = input_value * 100;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " кПа ");
                }
                else if (select2.equals("Бар"))
                {
                    textView_from.setText(String.valueOf(input_value + " бар "));
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(input_value) + " бар ");
                }
                else if (select2.equals("Торр"))
                {
                    textView_from.setText(String.valueOf(input_value) + " бар ");
                    unit1 = input_value * 750.062;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " тр. ");
                }

            }else if(select1.equals("Торр"))
            {
                if (select2.equals("Атмосфера"))
                {
                    textView_from.setText(String.valueOf(input_value) + " тр. ");
                    unit1 = input_value * 0.00131579;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " ат. ");
                }else if (select2.equals("Паскаль"))
                {
                    textView_from.setText(String.valueOf(input_value) + " тр. ");
                    unit1 = input_value * 133.322;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " Па ");
                }else if (select2.equals("Бар"))
                {
                    textView_from.setText(String.valueOf(input_value) + " тр. ");
                    unit1 = input_value * 0.00133322;
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " бар ");
                }else if (select2.equals("Торр"))
                {
                    textView_from.setText(String.valueOf(input_value + " тр. "));
                    textView_equal.setText("=");
                    textView_to.setText(String.valueOf(input_value) + " тр. ");
                }
            }


 }});}
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP); // округляє, має 2 параметри: 1) саме число 2) кількість знаків після коми
            }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Conv_press.this,UserHelp.class);
            startActivity(open);
        }
    };
}
