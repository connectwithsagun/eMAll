package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognitionService;
import android.util.Log;
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

public class ResetPasswordActivity extends AppCompatActivity {
    Button btDone;
    EditText Password, Cpassword;
  //  String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_password);

        btDone=findViewById(R.id.btDone);
        Password=findViewById(R.id.etPassword);
        Cpassword=findViewById(R.id.etConfirmPassword);

        //getting data of email address which is passed through intent from PasswordResetEmailVerificationActivity to ForgetPasswordVerificationActivity


//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            email = bundle.getString("email");
//        }
        //Log.v("abc",Email);


        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        String email = sh.getString("emailForForgetPassword", "");

        btDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(ResetPasswordActivity.this,LoginActivity.class));

                if (!validatePassword() | !validatePasswordCheck() ) {
                    return;
                }
                else{
                    passwordChange(email);
                }

            }
        });
       // Log.v("abc",email);

    }
    private void passwordChange(String email) {


        ApiService apiService = ApiUtil.getApiService();
        apiService.passwordChangeByForgetPassword(email,Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Toast.makeText(ResetPasswordActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful())
                {
                    try {
                        String val= response.body().string();
                        JSONObject obj= new JSONObject(val);
                        String message = obj.getString("message");

                        Toast.makeText(ResetPasswordActivity.this, "" + message, Toast.LENGTH_SHORT).show();


                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email is valid ", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ResetPasswordActivity.this, LoginActivity.class));
                }
                else{
                    Toast.makeText(ResetPasswordActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ResetPasswordActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    private Boolean validatePassword() {
        String val = Password.getText().toString();
        String passwordVal = "^" +
                //"(?=.*[0-9])" +         //at least 1 digit
                //"(?=.*[a-z])" +         //at least 1 lower case letter
                //"(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            Password.setError("Password field cannot be empty");
            return false;
        }

        else {
            Password.setError(null);
            // Password.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePasswordCheck() {
        String val = Cpassword.getText().toString();
        String val1 = Password.getText().toString();

        if(!val.equals(val1))
        {
            Cpassword.setError("password didnot matched!!please re-type password");
            return false;
        }
        else {
            Cpassword.setError(null);
            return true;
        }

    }


}
