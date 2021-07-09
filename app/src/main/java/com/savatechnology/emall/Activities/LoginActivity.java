package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.savatechnology.emall.Fragments.HomeFragment;
import com.savatechnology.emall.Fragments.LoginFragment;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    Button btLogin;
    TextView tvRegister;
    TextView tvSkip;
    TextView tvForgetPassword;
    EditText Email,Password;


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



//validateUser();
        btLogin = findViewById(R.id.btLogin);
        tvRegister = findViewById(R.id.tvRegister);
        tvSkip=findViewById(R.id.tvSkip);
        tvForgetPassword=findViewById(R.id.tvForgetPassword);
        Email=findViewById(R.id.etUsername);
        Password=findViewById(R.id.etPassword);

//        btLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                startActivity(new Intent(LoginActivity.this,MainActivity.class));
//            }
//        });
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userLogin();
                //startActivity(new Intent(LoginActivity.this,MainActivity.class));


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
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
            }
        });
        tvForgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, PasswordResetEmailVerificationActivity.class));
            }
        });









    }

//    private void validateUser(){
//        btLogin = findViewById(R.id.btLogin);
//
//        btLogin.setOnClickListener(view -> {
//
//            Toast.makeText(LoginActivity.this,"Clicked",Toast.LENGTH_SHORT).show();
//
//            ApiService apiService = ApiUtil.getApiService();
//            apiService.getUserList().enqueue(new Callback<ResponseBody>() {
//                @Override
//                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                    try {
//                        Toast.makeText(LoginActivity.this,""+response.body().string(),Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                }
//                @Override
//                public void onFailure(Call<ResponseBody> call, Throwable t) {
//                    Toast.makeText(LoginActivity.this,"On Failure",Toast.LENGTH_SHORT).show();
//                }
//            });
//        });
//    }

    private void userLogin() {



        if (Email.getText().toString().trim().equals("")) {
            Toast.makeText(LoginActivity.this,"UserId Cannot be left blank", Toast.LENGTH_SHORT).show();
            //CommonMethod.showAlert("UserId Cannot be left blank", LoginFragment.this);

        } else if (Password.getText().toString().trim().equals("")) {
            //CommonMethod.showAlert("password Cannot be left blank", LoginFragment.this);
            Toast.makeText(LoginActivity.this, "password Cannot be left blank", Toast.LENGTH_SHORT).show();

        } else {
            ApiService apiService = ApiUtil.getApiService();
            apiService.loginUser(Email.getText().toString(), Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    //Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
                    if (response.isSuccessful()) {
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }
                    // startActivity(new Intent(mContext, MainActivity.class));
                    else if(response.code()==400)
                    {
                        Toast.makeText(LoginActivity.this, "Email & Password doesn't match",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this ,"Login failed",Toast.LENGTH_LONG).show();

                    }

                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(LoginActivity.this, "On Failure", Toast.LENGTH_SHORT).show();

                }
            });

        }
    }


}