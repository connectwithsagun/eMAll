package com.savatechnology.emall.Fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.savatechnology.emall.Activities.ForgetPasswordVerificationActivity;
import com.savatechnology.emall.Activities.LoginActivity;
import com.savatechnology.emall.Activities.MainActivity;
import com.savatechnology.emall.Activities.PasswordResetEmailVerificationActivity;
import com.savatechnology.emall.Activities.SignUpActivity;
import com.savatechnology.emall.Activities.VerificationActivity;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.io.IOException;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.PATCH;

import static android.view.View.GONE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RegisterFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RegisterFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    Button register;
    TextView login,registerTitle,allreadyHaveAccount;
    EditText Email, Password, Cpassword;
    ImageView imgLogo;
    RelativeLayout relativeLayoutRegister;


    View view;
    private Context mContext;
    private Inflater inflater;

    public RegisterFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RegisterFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RegisterFragment newInstance(String param1, String param2) {
        RegisterFragment fragment = new RegisterFragment();
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
        view = inflater.inflate(R.layout.fragment_register, container, false);
        init(view);
        return view;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

     void init (View view) {


        register = view.findViewById(R.id.btRegister);
        login = view.findViewById(R.id.tvLogin);
        Email = view.findViewById(R.id.etEmail);
        Password = view.findViewById(R.id.etPassword);
        Cpassword = view.findViewById(R.id.etConfirmPassword);
        imgLogo = view.findViewById(R.id.imgLogo);
        registerTitle = view.findViewById(R.id.tvTitle);
        allreadyHaveAccount = view.findViewById(R.id.tvAlreadyHaveAnAccount);
         relativeLayoutRegister = view.findViewById(R.id.relativeLayoutRegister);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateEmail() | !validatePasswordCheck() ) {
                    return;
                }
                else{
                    userRegister();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //setDisplayShowTitleEnabled(false);
                //startActivity(new Intent(mContext, LoginActivity.class));
                LoginFragment fragment1=new LoginFragment();
                FragmentManager manager=getParentFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.registerFragment,fragment1);
                transaction.addToBackStack(null);
                relativeLayoutRegister.setVisibility(GONE);
                transaction.commit();


            }
        });


    }


    private void userRegister() {


            ApiService apiService = ApiUtil.getApiService();
            apiService.registerUser(Email.getText().toString(), Password.getText().toString()).enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                       // Toast.makeText(getActivity(), "" + response.body().string(), Toast.LENGTH_SHORT).show();


                    if (response.isSuccessful()) {
                        Toast.makeText(getActivity(), "User Created Successfully", Toast.LENGTH_SHORT).show();

                        //passing email address for user registration verification

                        Intent intent = new Intent(mContext, VerificationActivity.class);
                        intent.putExtra("email", Email.getText().toString().trim());
                        startActivity(intent);
//                        finish();
//

                       // startActivity(new Intent(mContext, VerificationActivity.class));

                    }
                    else
                    {
                        Toast.makeText(getActivity() ,"register failed",Toast.LENGTH_LONG).show();
                    }


                }


                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    Toast.makeText(getActivity(), "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
                }



            });
        }



    private Boolean validateEmail() {
        String val = Email.getText().toString();
       // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
        String emailPattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
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

