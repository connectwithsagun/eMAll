package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.savatechnology.emall.Fragments.HomeFragment;
import com.savatechnology.emall.R;

public class LoginActivity extends AppCompatActivity {
    Button btLogin;
    TextView tvRegister;
    TextView tvSkip;
    TextView tvForgetPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Take instance of Action Bar
        // using getSupportActionBar and
        // if it is not Null
        // then call hide function
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }




        btLogin = findViewById(R.id.btLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvSkip=findViewById(R.id.tvSkip);
        tvForgetPassword=findViewById(R.id.tvForgetPassword);

        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,HomeFragment.class));
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {





                startActivity(new Intent(LoginActivity.this,SignUpActivity.class));
            }
        });

        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, HomeFragment.class));
            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetEmailVerificationActivity.class));
            }
        });

    }

}