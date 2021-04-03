package com.example.foodorder;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class OrderActivity extends AppCompatActivity {
    Button button;
    String order = "One Taco with:\n";
    String number = "123456";
    String size = "Size: Large";
    String tortilla = "Tortilla: Corn";
    String filled = "Filling : ";
    String beverage = "Beverge : ";
    ArrayList<String> filling = null;
    String[] filling_taco = {"Beef","Chicken","White Fish","Cheese","Rice","Beans","Guacamole"};
    String[] Beverage_taco = {"Soda","Cerveza","Margarita","Tequila"};
    List<CheckBox> checkBoxesFilling = new ArrayList<>();
    List<CheckBox> checkBoxesBeverage = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        filled = "Filling : ";
        button = findViewById(R.id.orderButton);
        filling = new ArrayList<>();
        option();
        button.setOnClickListener(clickItemListener);

        LinearLayout linearLayoutLeftFilling = (LinearLayout) findViewById(R.id.left_fillings);

        for (int i = 0; i <= filling_taco.length/2; i++) {
            CheckBox test = new CheckBox(this);
            test.setId(1000+i);

            test.setText(filling_taco[i]);
            test.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            test.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutLeftFilling.addView(test);
            checkBoxesFilling.add(test);
        }

        LinearLayout linearLayoutRightFilling = (LinearLayout) findViewById(R.id.right_fillings);

        for (int i = filling_taco.length/2+1; i < filling_taco.length; i++) {
            CheckBox test = new CheckBox(this);
            test.setId(1000+i);

            test.setText(filling_taco[i]);
            test.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            test.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutRightFilling.addView(test);
            checkBoxesFilling.add(test);
        }

        LinearLayout linearLayoutLeftBeverage = (LinearLayout) findViewById(R.id.beverage_left);

        for (int i = 0; i <= Beverage_taco.length/2; i++) {
            CheckBox test = new CheckBox(this);
            test.setId(2000+i);

            test.setText(Beverage_taco[i]);
            test.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            test.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutLeftBeverage.addView(test);
            checkBoxesBeverage.add(test);
        }

        LinearLayout linearLayoutRightBeverage = (LinearLayout) findViewById(R.id.beverage_right);

        for (int i = Beverage_taco.length/2+1; i < Beverage_taco.length; i++) {
            CheckBox test = new CheckBox(this);
            test.setId(2000+i);

            test.setText(Beverage_taco[i]);
            test.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            test.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutRightBeverage.addView(test);
            checkBoxesBeverage.add(test);
        }
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


    }
    public void createOrder()
    {
        order = "One Taco with:\n";
        order += size + "\n";
        order += tortilla + "\n";
        order += filled + "\n";
        order += beverage + "\n";


    }
    public void openActivity()
    {
        System.out.println("clicked");
        for (CheckBox checkBox : checkBoxesFilling)
        {
           filled += itemClicked(checkBox);
        }

        for (CheckBox checkBox : checkBoxesBeverage)
        {
            beverage += itemClicked(checkBox);
        }

        createOrder();
        Uri uri = Uri.parse("smsto: "+ number);
        Intent it = new Intent(Intent.ACTION_SENDTO, uri);
        it.putExtra("sms_body", order);
        startActivity(it);
        filled = "Filling : ";
        beverage = "Beverge : ";


    }

    public String itemClicked(View v) {
        String s = "";
        //code to check if this checkbox is checked!
        CheckBox checkBox = (CheckBox) v;
        if(checkBox.isChecked()){
            s+= checkBox.getText()+" ";
        }
        return s;
    }
}