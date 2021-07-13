package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class ForgetPasswordVerificationActivity extends AppCompatActivity {
    Button btSubmit;
    EditText VerificationCode;
    TextView Resend;
    //String email1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password_verification);

        btSubmit=findViewById(R.id.btSubmit);
        VerificationCode=findViewById(R.id.etVerificationCode);
        Resend=findViewById(R.id.tvResend);

        //getting data of email address which is passed through intent from PasswordResetEmailVerificationActivity
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null) {
//            email1 = bundle.getString("email");
//        }
      // Log.v("abc",Email);

//        Resend.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                forgotPasswordEmailVerification(email);
//            }
//        });

        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sh.edit();
        String email = sh.getString("emailForForgetPassword", "");

        btSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // startActivity(new Intent(ForgetPasswordVerificationActivity.this,ResetPasswordActivity.class));
                forgetPasswordVerifyToken(email);
            }
        });
    }

    private void forgotPasswordEmailVerification(String email1) {
        ApiService apiService = ApiUtil.getApiService();
        apiService.forgotPassword(email1).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    try {
                        String val= response.body().string();
                        JSONObject obj= new JSONObject(val);
                        String message = obj.getString("message");

                        Toast.makeText(ForgetPasswordVerificationActivity.this, "" + message, Toast.LENGTH_SHORT).show();




                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email is valid ", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ForgetPasswordVerificationActivity.this, ForgetPasswordVerificationActivity.class));
                }
                else{
                    Toast.makeText(ForgetPasswordVerificationActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ForgetPasswordVerificationActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();            }
        });
    }

    private void forgetPasswordVerifyToken(String email) {
        ApiService apiService = ApiUtil.getApiService();
        apiService.forgetPasswordTokenVerify(VerificationCode.getText().toString().trim(),email).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful())
                {
                    try {
                        String val= response.body().string();
                        JSONObject obj= new JSONObject(val);
                        String message = obj.getString("message");

                        Toast.makeText(ForgetPasswordVerificationActivity.this, "" + message, Toast.LENGTH_SHORT).show();


                        //passing email address for forget password verification
                         //Log.v("abc",email);
//                        Intent intent = new Intent(ForgetPasswordVerificationActivity.this, ResetPasswordActivity.class);
//                        intent.putExtra("email", email1);
//                        startActivity(intent);

                    } catch (IOException | JSONException e) {
                        e.printStackTrace();
                    }

                    //Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email is valid ", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(ForgetPasswordVerificationActivity.this,ResetPasswordActivity.class));
                }
                else{
                    Toast.makeText(ForgetPasswordVerificationActivity.this, "failed ", Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(ForgetPasswordVerificationActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}