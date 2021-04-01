package com.example.tempconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button convert;
    EditText input;
    EditText output;
    EditText histories;
    RadioButton radioCtoF;
    RadioButton radioFtoC;
    RadioGroup group;
    String history = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        convert = findViewById(R.id.convert_button);
        input = findViewById(R.id.input);
        output = findViewById(R.id.output);
        histories = findViewById(R.id.history);
        radioCtoF = findViewById(R.id.radio_CtoF);
        radioFtoC = findViewById(R.id.radio_FtoC);
        convert.setOnClickListener(clickConvertListener);
    }

    private final View.OnClickListener clickConvertListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            float result = 0F;
            if(radioCtoF.isChecked())
            {

                result = (float) ((Float.parseFloat(input.getText().toString())  * 9.0 / 5.0) + 32.0);
                history += "C to F "+ input.getText().toString() +" => "+ result +" \n";
                output.setText(String.valueOf(result));

                histories.setText(history);
            } else if (radioFtoC.isChecked())
            {

                result = (float) ((Float.parseFloat(input.getText().toString()) -32.0) * 5.0 / 9.0);
                history += "F to C "+ input.getText().toString() +" => "+ result +" \n";
                output.setText(String.valueOf(result));
                histories.setText(history);
            }
        }
    };


}