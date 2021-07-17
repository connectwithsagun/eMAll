package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.savatechnology.emall.Fragments.AccountFragment;
import com.savatechnology.emall.Fragments.HomeFragment;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class
PasswordChangeActivity extends AppCompatActivity {

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
               // startActivity(new Intent(PasswordChangeActivity.this, AccountFragment.class));
                if (!validatePassword()  | !validatePasswordCheck() ) {
                    return;
                }
                else{
                    userPasswordChange();
                }



            }
        });

    }

    private void userPasswordChange() {
        SharedPreferences sh = PasswordChangeActivity.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String Email = sh.getString("email", "");
        ApiService apiService = ApiUtil.getApiService();
        apiService.passwordChange(Email,oldPassword.getText().toString(), cPassword.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {





                if (response.isSuccessful()) {
                    Toast.makeText(PasswordChangeActivity.this, "User password changed Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(PasswordChangeActivity.this, AccountFragment.class));

                }
                else
                {
                    Toast.makeText(PasswordChangeActivity.this ,"update failed",Toast.LENGTH_LONG).show();
                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PasswordChangeActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }



        });

    }

    private Boolean validatePassword() {
        String val = newPassword.getText().toString();
        String val1 = oldPassword.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val1.isEmpty()) {
            oldPassword.setError("Old password must be filled");
        }
        else{
            cPassword.setError(null);
            // Password.setErrorEnabled(false);
            return true;
        }
        if (val.isEmpty()) {
            newPassword.setError("Password field cannot be empty");
            return false;
        }
        else {
            newPassword.setError(null);
            // Password.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePasswordCheck() {
        String val = newPassword.getText().toString();
        String val1 = cPassword.getText().toString();

        if(!val.equals(val1))
        {
            cPassword.setError("password didnot matched!!please re-type password");
            return false;
        }
        else {
            cPassword.setError(null);
            return true;
        }

    }

}