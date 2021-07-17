package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.savatechnology.emall.Fragments.LoginFragment;
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

import static android.view.View.GONE;

public class VerificationActivity extends AppCompatActivity {
    Button btVerify;
    EditText registerVerificationCode;
    ImageView imgVerification;
    String email;
    RelativeLayout verificationLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //getting data of email address which is passed through intent from RegisterFragment
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            email = bundle.getString("email");
        }
      //  Log.v("abc",email);
        registerVerificationCode=findViewById(R.id.etVerificationCode);
        btVerify=findViewById(R.id.btVerify);
        imgVerification=findViewById(R.id.imgLogo);
        verificationLayout = findViewById(R.id.relativeLayoutVerificationActivity);

        btVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //startActivity(new Intent(VerificationActivity.this,LoginActivity.class));

                ApiService apiService = ApiUtil.getApiService();
                apiService.userRegistrationVerify(registerVerificationCode.getText().toString().trim(),email).enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful())
                        {
                            try {
                                String val= response.body().string();
                                JSONObject obj= new JSONObject(val);
                                String message = obj.getString("message");

                                Toast.makeText(VerificationActivity.this, "" + message, Toast.LENGTH_SHORT).show();



                            } catch (IOException | JSONException e) {
                                e.printStackTrace();
                            }

                            //Toast.makeText(PasswordResetEmailVerificationActivity.this, "Email is valid ", Toast.LENGTH_SHORT).show();
                            LoginFragment frag = new LoginFragment();
                            FragmentManager manager = getSupportFragmentManager();
                            FragmentTransaction transaction = manager.beginTransaction();
                            transaction.add(R.id.verificationActivity,frag,"Test Fragment");
//                            imgVerification.setVisibility(GONE);
//                            registerVerificationCode.setVisibility(GONE);
//                            btVerify.setVisibility(GONE);
                            verificationLayout.setVisibility(GONE);
                            transaction.commit();
                            // startActivity(new Intent(VerificationActivity.this, LoginFragment.class));
                        }
                        else{
                            Toast.makeText(VerificationActivity.this, "failed to verify user for registration ", Toast.LENGTH_SHORT).show();
                        }
                    }


                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        Toast.makeText(VerificationActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });
    }
}