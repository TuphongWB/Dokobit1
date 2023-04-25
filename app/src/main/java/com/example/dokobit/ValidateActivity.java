package com.example.dokobit;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.dokobit.Fragment.ValidFragment;

public class ValidateActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_validate);
        RadioGroup radioGroup = findViewById(R.id.radio_group);
        RadioButton radioButton1 = findViewById(R.id.radio_button1);
        RadioButton radioButton2 = findViewById(R.id.radio_button2);

        ImageView close_icon1 = findViewById(R.id.close_icon1);
        close_icon1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ValidateActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });


        Button validate = findViewById(R.id.validate);

        validate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValidFragment dialogFragment = new ValidFragment();
                dialogFragment.show(getSupportFragmentManager(), "MyDialogFragment");
            }
        });



        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi click vào radioButton1
                radioButton2.setChecked(false);
            }
        });

        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Thực hiện hành động khi click vào radioButton2
                radioButton1.setChecked(false);
            }
        });
    }
}
