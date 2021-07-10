package com.savatechnology.emall.Activities;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordResetEmailVerificationActivity extends AppCompatActivity {

Button btSendCode;
EditText Email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_reset_email_verification);

        btSendCode=findViewById(R.id.btSendCode);
        Email= findViewById(R.id.etEmail);

        btSendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(PasswordResetEmailVerificationActivity.this,ForgetPasswordVerificationActivity.class));
                if (!validateEmail() ) {
                    return;
                }
                else{
                    forgotPasswordEmailVerification();
                }
            }
        });


    }

    private void forgotPasswordEmailVerification() {

        ApiService apiService = ApiUtil.getApiService();
        apiService.forgotPassword(Email.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    try {
                        String val= response.body().string();
                        JSONObject obj= new JSONObject(val);
                        String message = obj.getString("message");

                        Toast.makeText(PasswordResetEmailVerificationActivity.this, "" + message, Toast.LENGTH_SHORT).show();


                        //passing email address for forget password verification

                        Intent intent = new Intent(getApplicationContext(), ForgetPasswordVerificationActivity.class);
                        intent.putExtra("email", Email.getText().toString().trim());
                        startActivity(intent);
                        finish();


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email is valid ", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(PasswordResetEmailVerificationActivity.this, ForgetPasswordVerificationActivity.class));
                }
                else{
                    Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email address is not valid ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(PasswordResetEmailVerificationActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();            }
        });
    }

    private Boolean validateEmail() {
        String val = Email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            Email.setError("Email field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            Email.setError("Invalid email address");
            return false;
        } else {
            Email.setError(null);
            //Email.setErrorEnabled(false);
            return true;
        }
    }
}