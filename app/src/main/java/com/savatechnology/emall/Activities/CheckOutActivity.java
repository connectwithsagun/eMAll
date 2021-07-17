package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.savatechnology.emall.R;

public class CheckOutActivity extends AppCompatActivity {
TextView username,address,phone,email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);

        username = findViewById(R.id.tvUserName);
        address = findViewById(R.id.tvUserAddress);
        phone = findViewById(R.id.tvUserPhone);
        email = findViewById(R.id.tvUserGmail);

        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("username", "");
        String s2 = sh.getString("address", "");
        String s3 = sh.getString("phone", "");
        String s4 = sh.getString("email", "");

        username.setText(s1);
        address.setText(s2);
        phone.setText(s3);
        email.setText(s4);
    }



}