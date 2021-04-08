package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.view.View;
import android.widget.Button;

import com.savatechnology.emall.R;

public class ResetPasswordActivity extends AppCompatActivity {
    Button btDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btDone=findViewById(R.id.btDone);


        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));
            }
        });
    }
}