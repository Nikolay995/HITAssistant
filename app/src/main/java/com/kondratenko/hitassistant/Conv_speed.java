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

public class Conv_speed extends AppCompatActivity {

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
        setContentView(R.layout.activity_conv_speed);
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

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.speed_units, android.R.layout.simple_spinner_item);
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

                if (select1.equals("Метри за секунду")) //из Ц.
                {
                    if (select2.equals("Метри за секунду")) // в Ф.
                    {
                        textView_from.setText(String.valueOf(input_value + " м/с "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " м/с ");
                    } else if (select2.equals("Кілометри за годину"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value) + " м/с ");
                        textView_equal.setText("=");
                        unit1 = input_value * 3.6;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " км/год ");

                    } else if (select2.equals("Милі за годину"))  // в К.
                    {
                        textView_from.setText(String.valueOf(input_value) + " м/с ");
                        textView_equal.setText("=");
                        unit1 = input_value * 2.23694;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " миль/год ");
                    }

                }else if(select1.equals("Кілометри за годину"))
                {
                    if (select2.equals("Кілометри за годину"))
                    {
                        textView_from.setText(String.valueOf(input_value + " км/год "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " км/год ");
                    } else if (select2.equals("Метри за секунду"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " км/год ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.277778;
                        textView_to.setText(String.valueOf(roundUp(unit1, 1)) + " м/с");

                    } else if (select2.equals("Милі за годину"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " км/год ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.6213714;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " миль/год ");
                    }
                }else if(select1.equals("Милі за годину"))
                {
                    if (select2.equals("Милі за годину"))
                    {
                        textView_from.setText(String.valueOf(input_value + " миль/год "));
                        textView_equal.setText("=");
                        textView_to.setText(String.valueOf(input_value) + " миль/год ");
                    } else if (select2.equals("Метри за секунду"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " миль/год ");
                        textView_equal.setText("=");
                        unit1 = input_value * 0.44704;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " м/с");

                    } else if (select2.equals("Кілометри за годину"))
                    {
                        textView_from.setText(String.valueOf(input_value) + " миль/год ");
                        textView_equal.setText("=");
                        unit1 = input_value * 1.60934;
                        textView_to.setText(String.valueOf(roundUp(unit1, 2)) + " км/год ");
                    }
                }

            }

});}
    public BigDecimal roundUp(double value, int digits) {
        return new BigDecimal("" + value).setScale(digits, BigDecimal.ROUND_HALF_UP);
    }
    View.OnClickListener snackbarOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent open = new Intent(Conv_speed.this,UserHelp.class);
            startActivity(open);
        }
    };

}
