package com.example.foodorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class OrderActivity extends AppCompatActivity {
    Button button;
    String order = "One Taco with:\n";
    String number = "123456";
    String size = "Size: Large";
    String tortilla = "Tortilla: Corn";
    ArrayList<String> filling = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        button = findViewById(R.id.orderButton);
        filling = new ArrayList<>();
        option();
        button.setOnClickListener(clickItemListener);

    }

    private final View.OnClickListener clickItemListener = new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            openActivity();
        }
    };

    public void option()
    {
        RadioButton size_medium = findViewById(R.id.size_meidum);
        RadioButton size_large= findViewById(R.id.size_large);
        RadioGroup rgSize = (RadioGroup) findViewById(R.id.rg_size);

        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.size_meidum:
                        size = "Size: medium";
                        break;
                    case R.id.size_large:
                        size = "Size: large";
                        break;
                }
            }
        });

        RadioGroup rgTortilla = (RadioGroup) findViewById(R.id.rg_tortilla);
        rgTortilla.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.tortilla_corn:
                        tortilla = "Tortilla: Corn";
                        break;
                    case R.id.tortilla_flour:
                        tortilla = "Tortilla: Flour";
                        break;
                }
            }
        });

        CheckBox filling_beef = (CheckBox)findViewById(R.id.fillings_beef);
        filling_beef.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                   filling.add("Beef");
                } else filling.remove("Beef");
            }
        });

        CheckBox filling_rice = (CheckBox)findViewById(R.id.fillings_rice);
        filling_rice.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("Rice");
                } else filling.remove("Rice");
            }
        });

        CheckBox filling_chicken = (CheckBox)findViewById(R.id.fillings_chicken);
        filling_chicken.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("Chicken");
                } else filling.remove("Chicken");
            }
        });

        CheckBox filling_beans = (CheckBox)findViewById(R.id.fillings_beans);
        filling_beans.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("Beans");
                } else filling.remove("Beans");
            }
        });

        CheckBox filling_whiteFish = (CheckBox)findViewById(R.id.fillings_white_fish);
        filling_whiteFish.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("White Fish");
                } else filling.remove("White Fish");
            }
        });

        CheckBox filling_guacamole = (CheckBox)findViewById(R.id.fillings_guacamole);
        filling_guacamole.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("Guacamole");
                } else filling.remove("Guacamole");
            }
        });

        CheckBox filling_cheese = (CheckBox)findViewById(R.id.fillings_cheese);
        filling_cheese.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    filling.add("Cheese");
                } else filling.remove("Guacamole");
            }
        });

    }
    public void createOrder()
    {
        order = "One Taco with:\n";
        order += size + "\n";
        order += tortilla + "\n";

        if (filling.isEmpty()) order += "Filling: none";
        else
        {
            order += "Filling: \n";
            for (int i = 0; i < filling.size(); i++)
                order += filling.get(i)+ "\n";
        }

    }
    public void openActivity()
    {
        createOrder();
        Uri uri = Uri.parse("smsto: "+ number);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", order);
        startActivity(it);

    }


}