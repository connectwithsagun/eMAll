package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.savatechnology.emall.R;

public class SignUpActivity extends AppCompatActivity {
    TextView tvLogin;
    Button btRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Take instance of Action Bar
        // using getSupportActionBar and
        // if it is not Null
        // then call hide function
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        btRegister=findViewById(R.id.btRegister);
        tvLogin = findViewById(R.id.tvLogin);

        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,VerificationActivity.class));
            }
        });
        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
        });
    }
}