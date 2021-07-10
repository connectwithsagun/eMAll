package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity extends AppCompatActivity {

    Button register;
    TextView login;
    EditText Email,Password,Cpassword;

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

        register=findViewById(R.id.btRegister);
        login = findViewById(R.id.tvLogin);
        Email=findViewById(R.id.etEmail);
        Password=findViewById(R.id.etPassword);
        Cpassword=findViewById(R.id.etConfirmPassword);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!validatePassword() | !validateEmail() | !validatePasswordCheck() ) {
                    return;
                }
                else{
                    userRegister();
                }

//                if (TextUtils.isEmpty(Email.getText().toString()))
//                {
//                    Toast.makeText(SignUpActivity.this, "Email is Required", Toast.LENGTH_SHORT).show();
//                }
//                else
//                {
//                    if (isValidEmailId(Email.getText().toString().trim())) {
//                        //Toast.makeText(getActivity(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(SignUpActivity.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//
//
//                if (TextUtils.isEmpty(Password.getText().toString()) && TextUtils.isEmpty(Cpassword.getText().toString()))
//                {
//                    Toast.makeText(SignUpActivity.this, "Password are Required", Toast.LENGTH_SHORT).show();
//                }
//                if (TextUtils.isEmpty(Password.getText().toString()) || TextUtils.isEmpty(Cpassword.getText().toString()))
//                {
//                    Toast.makeText(SignUpActivity.this, "Password are Required", Toast.LENGTH_SHORT).show();
//                }
//                if((!Cpassword.getText().toString().equals(Password.getText().toString()) )){
//                    Toast.makeText(SignUpActivity.this, "Password didnot matched", Toast.LENGTH_SHORT).show();
//                }
//
//                if(Cpassword.getText().toString().equals(Password.getText().toString()) && isValidEmailId(Email.getText().toString().trim()))
//                {
//                    userRegister();
//                }

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this,LoginActivity.class));
            }
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
//        else if (!val.matches(passwordVal)) {
//            Password.setError("Password is too weak");
//            return false;
//        }
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

    private void userRegister() {
        ApiService apiService = ApiUtil.getApiService();
        apiService.registerUser(Email.getText().toString(), Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //Toast.makeText(SignUpActivity.this, "" + response.code(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful()) {
                    Toast.makeText(SignUpActivity.this, "User Created Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(SignUpActivity.this, LoginActivity.class));


                }
                else
                {
                    Toast.makeText(SignUpActivity.this ,"register failed",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(SignUpActivity.this, "On Failure", Toast.LENGTH_SHORT).show();

            }
        });

        //}
    }

    private boolean  isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
    }
