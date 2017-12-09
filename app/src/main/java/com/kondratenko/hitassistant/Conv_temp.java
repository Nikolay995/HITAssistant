package com.kondratenko.hitassistant;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;

public class Conv_temp extends AppCompatActivity {

    private EditText editText_currency;
    private Spinner spinner_from;
    private Spinner spinner_to;
    private Button button_convert;
    private TextView textView_from;
    private TextView textView_to;
    private double unit1;
    private TextView textView_equal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conv_temp);
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
        button_convert = (Button) findViewById(R.id.convert);
        textView_from = (TextView) findViewById(R.id.textView_from);
        textView_to = (TextView) findViewById(R.id.textView_to);
        textView_equal=(TextView) findViewById(R.id.textView11);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.temp_units, android.R.layout.simple_spinner_item);
                // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner_from.setAdapter(adapter);
        spinner_to.setAdapter(adapter);

        button_convert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_currency = (EditText) findViewById(R.id.input_value);
                if (editText_currency.getText().toString().equals("")) { // перевірка на "непорожність" :)
                    Toast toast = Toast.makeText(getApplicationContext(),"Будь ласка, введіть значення", Toast.LENGTH_LONG);
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

                if (select1.equals("Цельсій")) //из Ц.
                {
                    if (select2.equals("Фаренгейт")) // в Ф.
                    {
                        textView_from.setText(String.valueOf(input_value)+" °C ");
                        unit1 = ((input_value*9.0)/5.0)+32.0;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" °F ");
                    }
                    else if (select2.equals("Кельвін"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value)+" °C ");
                        textView_equal.setText("=");
                        unit1 = input_value+273.15;
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" K ");
                    }
                    else if (select1.equals("Цельсій")) { // в Ц.
                        textView_from.setText(String.valueOf(input_value+" °C "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value)+" °C ");}
                }
                else if (select1.equals("Фаренгейт"))
                {
                    if (select2.equals("Цельсій"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" °F");
                        unit1 = ((input_value*5.0)/9.0)-32.0;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" °C ");
                    }
                    else  if (select2.equals("Кельвін"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" °F");
                        unit1 = (((input_value-32)*5.0)/9.0)+273.15;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" K");
                    }
                    else  if (select2.equals("Фаренгейт"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" °F");
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value)+" °F ");
                    }

                }
                else if (select1.equals("Кельвін"))
                {
                    if (select2.equals("Цельсій"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" K");
                        unit1 = input_value-273.15;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" °C ");
                    }
                    else if (select2.equals("Фаренгейт"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" K");
                        unit1 = 1.8*(input_value-273.15)+32.0;
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(unit1,1))+" °F ");
                    }
                    else if (select2.equals("Кельвін"))
                    {
                        textView_from.setText(String.valueOf(input_value)+" K");
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(roundUp(input_value,1))+" K");
                    }
                }

            }
    });

}

    public BigDecimal roundUp(double value, int digits){
        return new BigDecimal(""+value).setScale(digits, BigDecimal.ROUND_HALF_UP); // округляє, має 2 параметри: 1) саме число 2) кількість знаків після коми
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Conv_temp.this,UserHelp.class);
            startActivity(open);
        }
    };

}










//String from_currency = String.valueOf(spinner_from.getSelectedItem());
//String to_currency = String.valueOf(spinner_to.getSelectedItem());