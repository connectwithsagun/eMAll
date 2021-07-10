package com.savatechnology.emall.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.savatechnology.emall.Activities.EditProfileActivity;
import com.savatechnology.emall.Activities.ForgetPasswordVerificationActivity;
import com.savatechnology.emall.Activities.HomeActivity;
import com.savatechnology.emall.Activities.LoginActivity;
import com.savatechnology.emall.Activities.MainActivity;
import com.savatechnology.emall.Activities.PasswordResetEmailVerificationActivity;
import com.savatechnology.emall.Activities.SignUpActivity;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    Button Login;
    TextView Register,Skip,ForgetPsw;
    EditText Email,Password;
    //String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    View view;
    private Context mContext;


    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.activity_login, container, false);




        init(view);
       //validateUser();

        //userLogin(email,password);
        return view;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    void init(View view) {

//        BottomNavigationView btn_nav=view.findViewById(R.id.bottom_nav_view);
//        btn_nav.setVisibility(View.GONE);

        Login = view.findViewById(R.id.btLogin);
        Register = view.findViewById(R.id.tvRegister);
        Skip=view.findViewById(R.id.tvSkip);
        ForgetPsw=view.findViewById(R.id.tvForgetPassword);
        Email=view.findViewById(R.id.etEmail);
        Password=view.findViewById(R.id.etPassword);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                userLogin();


            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, SignUpActivity.class));

            }
        });

        Skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, MainActivity.class));


            }
        });


        ForgetPsw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, PasswordResetEmailVerificationActivity.class));



            }
        });


    }




    private void userLogin() {

        ApiService apiService = ApiUtil.getApiService();
        apiService.loginUser(Email.getText().toString(), Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {


                //Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();

                if (response.isSuccessful())
                {
                    Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
                    Toast.makeText(getActivity(), "Welcome "+Email.getText(), Toast.LENGTH_LONG).show();
                    startActivity(new Intent(mContext, MainActivity.class));






                }

                else
                    {
                        if (TextUtils.isEmpty(Email.getText().toString()) && TextUtils.isEmpty(Password.getText().toString())) {
                            Toast.makeText(getActivity(), "Email and Password are Required", Toast.LENGTH_SHORT).show();
                        }
                        else  if (TextUtils.isEmpty(Email.getText().toString())) {
                            Toast.makeText(getActivity(), "Email is Required", Toast.LENGTH_SHORT).show();
                        }
                        else if (TextUtils.isEmpty(Password.getText().toString())) {
                            Toast.makeText(getActivity(), "Password is Required", Toast.LENGTH_SHORT).show();
                        }
                        else if(response.code()==400)
                        {
                            if(isValidEmailId(Email.getText().toString().trim())){
                                //Toast.makeText(getActivity(), "Valid Email Address.", Toast.LENGTH_SHORT).show();
                            }else{
                                Toast.makeText(getActivity(), "InValid Email Address.", Toast.LENGTH_SHORT).show();
                            }
                            Toast.makeText(getActivity() ,"Email & Password doesn't match",Toast.LENGTH_LONG).show();
                        }

                        else
                        {
                            Toast.makeText(getActivity() ,"Login failed",Toast.LENGTH_LONG).show();
                        }
                     }




            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(getActivity(), "On Failure", Toast.LENGTH_SHORT).show();

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