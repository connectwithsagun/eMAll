package com.savatechnology.emall.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.savatechnology.emall.R;

public class PasswordResetEmailVerificationActivity extends AppCompatActivity {

Button btSendCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_email_verification);

        btSendCode=findViewById(R.id.btSendCode);

        btSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordResetEmailVerificationActivity.this,ForgetPasswordVerificationActivity.class));
            }
        });


    }
}