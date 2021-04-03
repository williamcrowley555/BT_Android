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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class OrderActivity extends AppCompatActivity {
    Button button;
    String order = "One Taco with:\n";
    String number = "123456";
    String size = "Size: Large";
    String tortilla = "Tortilla: Corn";
    String filled = "Filling : ";
    String beverage = "Beverge : ";

    String[] filling_taco = {"Beef", "Chicken", "White Fish", "Cheese", "Rice", "Beans", "Guacamole"};
    String[] Beverage_taco = {"Soda", "Cerveza", "Margarita", "Tequila"};
    List<CheckBox> checkBoxesFilling = new ArrayList<>();
    List<CheckBox> checkBoxesBeverage = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);
        button = findViewById(R.id.orderButton);
        option();
        button.setOnClickListener(clickItemListener);

        Intent intent = getIntent();
        int buttonId = intent.getIntExtra("item_id", 0);
        switch (buttonId) {
            case R.id.taco: {
                checkBoxesFilling = new ArrayList<>();
                checkBoxesBeverage = new ArrayList<>();
                ImageView img = findViewById(R.id.imgFood);
                img.setImageResource(R.drawable.logo);
                TextView name = findViewById(R.id.brand_name);
                name.setText("Taco Pronto");
                fill(R.id.left_fillings, R.id.right_fillings, filling_taco, checkBoxesFilling);
                fill(R.id.beverage_left, R.id.beverage_right, Beverage_taco, checkBoxesBeverage);
                break;
            }

            case R.id.pizza: {
                ImageView img = findViewById(R.id.imgFood);
                img.setImageResource(R.drawable.pizza);
                TextView name = findViewById(R.id.brand_name);
                name.setText("Pizza Caronila");
                break;
            }

            case R.id.burito: {
                ImageView img = findViewById(R.id.imgFood);
                img.setImageResource(R.drawable.burito);
                TextView name = findViewById(R.id.brand_name);
                name.setText("Burito");
                break;
            }

            case R.id.sandwich: {
                ImageView img = findViewById(R.id.imgFood);
                img.setImageResource(R.drawable.sandwich);
                TextView name = findViewById(R.id.brand_name);
                name.setText("Sandwich");
                break;
            }
        }
    }

    public static int roundUp(int num, int divisor) {
        int sign = (num > 0 ? 1 : -1) * (divisor > 0 ? 1 : -1);
        return sign * (abs(num) + abs(divisor) - 1) / abs(divisor);
    }

    public void fill(int idLeft, int idRight, String[] content, List<CheckBox> checkBoxes) {

        LinearLayout linearLayoutLeft = (LinearLayout) findViewById(idLeft);

        for (int i = 0; i < content.length / 2; i++) {
            CheckBox box = new CheckBox(this);


            box.setText(content[i]);
            box.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            box.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutLeft.addView(box);
            checkBoxes.add(box);
        }

        LinearLayout linearLayoutRight = (LinearLayout) findViewById(idRight);

        for (int i = roundUp(content.length, 2); i < content.length; i++) {
            CheckBox box = new CheckBox(this);


            box.setText(content[i]);
            box.setTextAppearance(this, android.R.style.TextAppearance_Medium);
            box.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            linearLayoutRight.addView(box);
            checkBoxes.add(box);
        }
    }

    private final View.OnClickListener clickItemListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            openActivity();
        }
    };

    public void option() {
        RadioButton size_medium = findViewById(R.id.size_meidum);
        RadioButton size_large = findViewById(R.id.size_large);
        RadioGroup rgSize = (RadioGroup) findViewById(R.id.rg_size);

        rgSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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
        rgTortilla.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
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

    public void createOrder() {
        order = "One Taco with:\n";
        order += size + "\n";
        order += tortilla + "\n";
        order += filled + "\n";
        order += beverage + "\n";

    }

    public void openActivity() {
        System.out.println("clicked");
        for (CheckBox checkBox : checkBoxesFilling) {
            filled += itemClicked(checkBox);
        }

        for (CheckBox checkBox : checkBoxesBeverage) {
            beverage += itemClicked(checkBox);
        }

        createOrder();
        Uri uri = Uri.parse("smsto: " + number);
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
        if (checkBox.isChecked()) {
            s += checkBox.getText() + " ";
        }
        return s;
    }
}