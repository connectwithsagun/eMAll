package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.savatechnology.emall.R;

public class ForgetPasswordVerificationActivity extends AppCompatActivity {
    Button btSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_verification);

        btSubmit=findViewById(R.id.btSubmit);

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ForgetPasswordVerificationActivity.this,ResetPasswordActivity.class));
            }
        });
    }
}