package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.savatechnology.emall.Fragments.AccountFragment;
import com.savatechnology.emall.Fragments.HomeFragment;
import com.savatechnology.emall.R;

public class PasswordChangeActivity extends AppCompatActivity {

    Button btChangePassword;
    EditText oldPassword,newPassword,cPassword;
    private View parent_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        btChangePassword=findViewById(R.id.btChangePassword);
        oldPassword = findViewById(R.id.etCurrentPassword);
        newPassword = findViewById(R.id.etNewPassword);
        cPassword = findViewById(R.id.etConfirmPassword);


        btChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(PasswordChangeActivity.this, AccountFragment.class));




            }
        });

    }

}