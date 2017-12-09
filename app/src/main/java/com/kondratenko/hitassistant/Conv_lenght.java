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

public class Conv_lenght extends AppCompatActivity {

    private Spinner spinner_from;
    private Spinner spinner_to;
    private TextView textView_from;
    private TextView textView_to;
    private TextView textView_equal;
    private EditText editText_currency;
    private double unit1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv_lenght);
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

        spinner_from  =(Spinner) findViewById(R.id.spinner_from);
        spinner_to = (Spinner) findViewById(R.id.spinner_to);
        Button button_convert = (Button) findViewById(R.id.convert);
        textView_from = (TextView) findViewById(R.id.textView_from);
        textView_to = (TextView) findViewById(R.id.textView_to);
        textView_equal=(TextView) findViewById(R.id.textView11);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.lenght_units, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_from.setAdapter(adapter);
        spinner_to.setAdapter(adapter);

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_currency = (EditText) findViewById(R.id.input_value);
                if (editText_currency.getText().toString().equals("")) { // перевірка на "непорожність" :)
                    Toast toast = Toast.makeText(getApplicationContext(), "Будь ласка, введіть значення", Toast.LENGTH_LONG);
                    toast.show();
                    textView_equal.setText("");
                    textView_from.setText("");
                    textView_to.setText("");
                    return;
                }
                float input_value = Float.parseFloat(editText_currency.getText().toString());
                // Double input_value = Double.valueOf(editText_currency.toString());
                String select1 = spinner_from.getSelectedItem().toString();
                String select2 = spinner_to.getSelectedItem().toString();

                if (select1.equals("Кілометр"))
                {
                    if (select2.equals("Миля"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " км ");
                        unit1 = input_value * 0.621371;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " миль ");
                    } else if (select2.equals("Метр"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " км ");
                        textView_equal.setText("=");
                        unit1 = input_value * 1000.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " м ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " км "));
                        textView_equal.setText("=");
                        unit1 = input_value * 3280.84;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " км "));
                        textView_equal.setText("=");
                        unit1 = input_value * 39370.08;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " дм ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " км "));
                        textView_equal.setText("=");
                        unit1 = input_value * 39370.08;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    } else if (select2.equals("Кілометр")) {
                        textView_from.setText(String.valueOf(input_value + " км "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " км ");
                    }

                } else if (select1.equals("Миля")) {
                    if (select2.equals("Кілометр")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        unit1 = input_value * 1.60934;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " км ");
                    } else if (select2.equals("Миля")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " миль ");
                    } else if (select2.equals("Метр")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        unit1 = input_value * 1609.3;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " м ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        unit1 = input_value * 5280;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        unit1 = input_value * 63360;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " дм ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " миль "));
                        textView_equal.setText("=");
                        unit1 = input_value * 160934;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    }

                } else if (select1.equals("Метр")) {
                    if (select2.equals("Кілометр")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        unit1 = input_value * 1000;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " км ");
                    } else if (select2.equals("Миля")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.000621371;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " миль ");
                    } else if (select2.equals("Метр")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " м ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        unit1 = input_value * 3.28084;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        unit1 = input_value * 39.37008;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " дм ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " м "));
                        textView_equal.setText("=");
                        unit1 = input_value * 100;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    }

                } else if (select1.equals("Фут")) {
                    if (select2.equals("Кілометр")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0003048;
                        textView_to.setText(String.valueOf(roundUp(unit1, 5)) + " км ");
                    } else if (select2.equals("Миля")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.000189394;
                        textView_to.setText(String.valueOf(roundUp(unit1, 5)) + " миль ");
                    } else if (select2.equals("Метр")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        unit1 = input_value * 0.3048;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1, 3)) + " м ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " фт ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        textView_equal.setText("=");
                        unit1 = input_value * 12;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " дм ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " фт "));
                        textView_equal.setText("=");
                        unit1 = input_value * 30.48;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    }

                } else if (select1.equals("Дюйм")) {
                    if (select2.equals("Кілометр"))
                    {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0000254;
                        textView_to.setText(String.valueOf(roundUp(unit1, 6)) + " км ");
                    } else if (select2.equals("Миля"))
                    {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0000157;
                        textView_to.setText(String.valueOf(roundUp(unit1, 7)) + " миль ");
                    } else if (select2.equals("Метр")) {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        unit1 = input_value * 0.0254;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " м ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " дм ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0833333;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " дм "));
                        textView_equal.setText("=");
                        unit1 = input_value * 2.539;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    }

                } else if (select1.equals("Сантиметр")) {
                    if (select2.equals("Кілометр")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.000001;
                        textView_to.setText(String.valueOf(roundUp(unit1, 7)) + " км ");
                    } else if (select2.equals("Миля")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.00000621371;
                        textView_to.setText(String.valueOf(roundUp(unit1, 6)) + " миль ");
                    } else if (select2.equals("Метр")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        unit1 = input_value * 0.01;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1, 3)) + " м ");
                    } else if (select2.equals("Сантиметр")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " см ");
                    } else if (select2.equals("Фут")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.03280843;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт ");
                    } else if (select2.equals("Дюйм")) {
                        textView_from.setText(String.valueOf(input_value + " см "));
                        textView_equal.setText("=");
                        unit1 = input_value * 0.393701;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " см ");
                    }

                }


            }

        });
    }

    public BigDecimal roundUp(double value, int digits)
    {
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP);
        // округляє, має 2 параметри: 1) саме число 2) кількість знаків після коми
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Conv_lenght.this,UserHelp.class);
            startActivity(open);
        }
    };
}
