package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.savatechnology.emall.Fragments.AccountFragment;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.IOException;

import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileActivity extends AppCompatActivity {
    Button etSubmit;
    EditText phone,username,address;
    RadioGroup gender;
    RadioButton radioGenderButton,radioMale,radioFemale,radioOther;
    String Id;
    static String abc;
    int selectedID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        etSubmit=findViewById(R.id.btSubmit);
        username = findViewById(R.id.etUserName);
        phone   = findViewById(R.id.etPhone);
        address = findViewById(R.id.etAddress);
//        gender = (RadioGroup) findViewById(R.id.radioSex);
      //  selectedID = gender.getCheckedRadioButtonId();
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        radioOther = findViewById(R.id.radioOther);




        // radioGenderButton = (RadioButton) findViewById(R.id.selectedID);

        SharedPreferences sh = EditProfileActivity.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String name = sh.getString("username", "");
        String ph = sh.getString("phone", "");
        String add = sh.getString("address", "");
        String gender = sh.getString("gender", "");

    //    Log.v("fefv",gender);
        //Toast.makeText(EditProfileActivity.this, ""+add, Toast.LENGTH_SHORT).show();


            if (!name.isEmpty()) {
                username.setText(name);
                phone.setText(ph);
                address.setText(add);
                if(gender.equals("Male"))
                    radioMale.setChecked(true);
                else if(gender.equals("Female"))
                    radioFemale.setChecked(true);
                else
                    radioOther.setChecked(true);
            }

        RadioGroup rg = (RadioGroup) findViewById(R.id.radioSex);
        //final String[] abc = new String[1];
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                switch (checkedId) {
                    case R.id.radioMale:
                        //radioMale.setChecked(true);
                        abc ="Male";
                        break;
                    case R.id.radioFemale:
                        //radioFemale.setChecked(true);
                        abc ="Female";
                        break;
                    case R.id.radioOther:
                        //radioOther.setChecked(true);
                        abc ="Others";
                        break;
                }
            }
        });


      // Log.v("abc",Id);



        etSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (TextUtils.isEmpty(username.getText().toString()) && TextUtils.isEmpty(phone.getText().toString())){
//                    username.setError("Username must be filled");
//                    phone.setError("Phone number must be filled");
//                }
//                else if(TextUtils.isEmpty(username.getText().toString())){
//                    username.setError("Username must be filled");
//                }
//                else if(TextUtils.isEmpty(phone.getText().toString())){
//                    phone.setError("Phone number must be filled");
//                }
                SharedPreferences sh = EditProfileActivity.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
                String name = sh.getString("username", "");
                {
                    if (name.isEmpty()) {

                        addUserDetails(abc);
                    } else {

                        editUser();

                    }
                }
            }
        });
    }

    private void addUserDetails(String abc) {

        SharedPreferences sh = EditProfileActivity.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String Id = sh.getString("id", "");
       // Log.v("abc",abc);
        ApiService apiService = ApiUtil.getApiService();
        apiService.userDetailAdd(Id, username.getText().toString(),phone.getText().toString(),address.getText().toString(),abc).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                try {
                    Log.v("sab",response.errorBody().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }

                // Toast.makeText(getActivity(), "" + response.body().string(), Toast.LENGTH_SHORT).show();


                if (response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "User details added Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditProfileActivity.this, MainActivity.class));

                }

                else
                {

                    Toast.makeText(EditProfileActivity.this ,"update failed",Toast.LENGTH_LONG).show();
                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }



        });
    }




    private void editUser() {
        SharedPreferences sh = EditProfileActivity.this.getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String Id = sh.getString("id", "");

        ApiService apiService = ApiUtil.getApiService();
        apiService.editUserInfo(Id, username.getText().toString(),phone.getText().toString(),address.getText().toString(),abc).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                // Toast.makeText(getActivity(), "" + response.body().string(), Toast.LENGTH_SHORT).show();


                if (response.isSuccessful()) {
                    Toast.makeText(EditProfileActivity.this, "User details updated Successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(EditProfileActivity.this, MainActivity.class));

                }
                else
                {
                    Toast.makeText(EditProfileActivity.this ,"update failed",Toast.LENGTH_LONG).show();
                }


            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(EditProfileActivity.this, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
            }



        });
    }
}