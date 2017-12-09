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

public class Conv_weight extends AppCompatActivity {

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
        setContentView(R.layout.activity_conv_weight);
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.weight_units, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // Apply the adapter to the spinner
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
                String select1 = spinner_from.getSelectedItem().toString();
                String select2 = spinner_to.getSelectedItem().toString();

                if (select1.equals("Кілограм")) //из Ц.
                {
                    if (select2.equals("Грам")) // в Ф.
                    {
                        textView_from.setText(String.valueOf(input_value) + " кг. ");
                        unit1 = input_value * 1000.0;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " г. ");
                    } else if (select2.equals("Фунт"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value) + " кг. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 2.20462;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " фт. ");
                    } else if (select2.equals("Кілограм")) { // в Ц.
                        textView_from.setText(String.valueOf(input_value + " кг. "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " кг. ");
                    } else if (select2.equals("Карат"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value) + " кг. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 5000.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " кр. ");
                    } else if (select2.equals("Унція"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value) + " кг. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 35.274;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " ун. ");
                    }

                } else if (select1.equals("Грам")) {
                    if (select2.equals("Кілограм")) {
                        textView_from.setText(String.valueOf(input_value) + " г. ");
                        textView_equal.setText("=");
                        unit1 = input_value / 1000.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 3)) + " кг. ");
                    } else if (select2.equals("Грам")) {
                        textView_from.setText(String.valueOf(input_value + " г. "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " г. ");
                    } else if (select2.equals("Фунт")) {
                        textView_from.setText(String.valueOf(input_value) + " г. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.00220462;
                        textView_to.setText(String.valueOf(roundUp(unit1, 3)) + " фт. ");
                    } else if (select2.equals("Карат")) {
                        textView_from.setText(String.valueOf(input_value) + " г. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 5.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кр. ");
                    } else if (select2.equals("Унція")) {
                        textView_from.setText(String.valueOf(input_value) + " г. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.035274;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " . ");
                    }

                } else if (select1.equals("Фунт")) {
                    if (select2.equals("Кілограм")) {
                        textView_from.setText(String.valueOf(input_value) + " фт. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.453592;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кг. ");
                    } else if (select2.equals("Грам")) {
                        textView_from.setText(String.valueOf(input_value) + " фт. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 453.592;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " г. ");
                    } else if (select2.equals("Карат")) {
                        textView_from.setText(String.valueOf(input_value) + " фт. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 2267.96;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кр. ");
                    } else if (select2.equals("Фунт")) {
                        textView_from.setText(String.valueOf(input_value + " фт. "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " фт. ");
                    } else if (select2.equals("Унція")) {
                        textView_from.setText(String.valueOf(input_value) + " фт. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 16.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " ун. ");
                    }

                } else if (select1.equals("Карат")) {
                    if (select2.equals("Кілограм")) {
                        textView_from.setText(String.valueOf(input_value) + " кр. ");
                        textView_equal.setText("=");
                        unit1 = input_value / 5000.0;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кг. ");
                    } else if (select2.equals("Грам")) {
                        textView_from.setText(String.valueOf(input_value) + " кр. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.2;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " г. ");
                    } else if (select2.equals("Фунт")) {
                        textView_from.setText(String.valueOf(input_value) + " кр. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.000440925;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " фт. ");
                    } else if (select2.equals("Унція")) {
                        textView_from.setText(String.valueOf(input_value) + " кр. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.00705479;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " ун. ");
                    } else if (select2.equals("Карат")) {
                        textView_from.setText(String.valueOf(input_value + " кр. "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " кр. ");
                    }
                } else if (select1.equals("Унція")) {
                    if (select2.equals("Кілограм")) {
                        textView_from.setText(String.valueOf(input_value) + " ун. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0283495;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кг. ");
                    } else if (select2.equals("Грам")) {
                        textView_from.setText(String.valueOf(input_value) + " ун. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 28.3495;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " г. ");
                    } else if (select2.equals("Фунт")) {
                        textView_from.setText(String.valueOf(input_value) + " ун. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.0625;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " фт. ");
                    } else if (select2.equals("Карат")) {
                        textView_from.setText(String.valueOf(input_value) + " ун. ");
                        textView_equal.setText("=");
                        unit1 = input_value * 141.748;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " кр. ");
                    } else if (select2.equals("Унція")) {
                        textView_from.setText(String.valueOf(input_value + " ун. "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " ун. ");
                    }
                }
            }
        });
    }

            public BigDecimal roundUp(double value, int digits) {
                return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP); // округляє, має 2 параметри: 1) саме число 2) кількість знаків після коми
            }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Conv_weight.this,UserHelp.class);
            startActivity(open);
        }
    };

        }
