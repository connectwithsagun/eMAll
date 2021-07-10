package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
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
import java.util.regex.Pattern;

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
        Email=findViewById(R.id.etEmail);
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

        ApiService apiService = ApiUtil.getApiService();
        apiService.loginUser(Email.getText().toString(), Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Welcome "+Email.getText(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));


//                    SettingsFragment settingsFragment = new SettingsFragment();
//                    FragmentManager fragmentManager = getFragmentManager();
//                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                    fragmentTransaction.replace(R.id.container, settingsFragment);
//                    fragmentTransaction.addToBackStack(null);
//                    fragmentTransaction.commit();



                }

                else
                {
                    if (TextUtils.isEmpty(Email.getText().toString()) && TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Email and Password are Required", Toast.LENGTH_SHORT).show();
                    }
                    else  if (TextUtils.isEmpty(Email.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Email is Required", Toast.LENGTH_SHORT).show();
                    }
                    else if (TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(LoginActivity.this, "Password is Required", Toast.LENGTH_SHORT).show();
                    }
                    else if(response.code()==400)
                    {
                        if(isValidEmailId(Email.getText().toString().trim())){
                            //Toast.makeText(getActivity(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(LoginActivity.this, "InValid Email Address.", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(LoginActivity.this ,"Email & Password doesn't match",Toast.LENGTH_LONG).show();
                    }

                    else
                    {
                        Toast.makeText(LoginActivity.this ,"Login failed",Toast.LENGTH_LONG).show();
                    }
                }




            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "On Failure", Toast.LENGTH_SHORT).show();

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