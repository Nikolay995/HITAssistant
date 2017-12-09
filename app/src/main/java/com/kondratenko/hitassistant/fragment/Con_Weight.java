package com.kondratenko.hitassistant.fragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;
import com.kondratenko.hitassistant.R;
import android.content.Intent;

/**
 * A simple {@link Fragment} subclass.
 */
public class Con_Weight extends Fragment {

    EditText e1,e2,e3,e4,e5,e6;
    Button b1;
    public Con_Weight() {

        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        /**e1=(EditText) getView().findViewById(R.id.editText2);
        e2=(EditText) getView().findViewById(R.id.editText4);
        e3=(EditText) getView().findViewById(R.id.editText5);
        e4=(EditText) getView().findViewById(R.id.editText6);
        e5=(EditText) getView().findViewById(R.id.editText7);
        e6=(EditText) getView().findViewById(R.id.editText8);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double kg = Double.parseDouble(e1.getText().toString());
                double mlg = kg*1000000;
                double karat = kg*5000;
                double gramm = kg*1000;
                double unciya = kg*35;
                double tonna = kg/1000;
                e1.setText(""+kg);
                e2.setText(""+mlg);
                e3.setText(""+karat);
                e4.setText(""+gramm);
                e5.setText(""+unciya);
                e5.setText(""+tonna);


            }
        });
         **/


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_con__weight, container, false);


    }
}

