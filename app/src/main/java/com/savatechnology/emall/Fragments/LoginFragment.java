package com.savatechnology.emall.Fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;
import com.savatechnology.emall.Activities.EditProfileActivity;
import com.savatechnology.emall.Activities.ForgetPasswordVerificationActivity;
import com.savatechnology.emall.Activities.HomeActivity;
import com.savatechnology.emall.Activities.LoginActivity;
import com.savatechnology.emall.Activities.MainActivity;
import com.savatechnology.emall.Activities.PasswordChangeActivity;
import com.savatechnology.emall.Activities.PasswordResetEmailVerificationActivity;
import com.savatechnology.emall.Activities.SignUpActivity;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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
    TextView Register,Skip,ForgetPsw, Title, DontAccount;
    EditText Email,Password,Id;
    ImageView img;
    RelativeLayout relativeLayoutLogin;
    String _id,username;

    BottomNavigationView btn_nav;


    View view;
    private Context mContext;
    private Inflater inflater;


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
        view= inflater.inflate(R.layout.fragment_login, container, false);




         btn_nav=(BottomNavigationView) getActivity().findViewById(R.id.bottom_nav_view);
        btn_nav.setVisibility(view.GONE);



        init(view);

        return view;
    }




    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    void init(View view) {



        Login = view.findViewById(R.id.btLogin);
        Register = view.findViewById(R.id.tvRegister);
        Skip=view.findViewById(R.id.tvSkip);
        ForgetPsw=view.findViewById(R.id.tvForgetPassword);
        Email=view.findViewById(R.id.etEmail);
        Password=view.findViewById(R.id.etPassword);
        img=view.findViewById(R.id.imgLogo);
        Title=view.findViewById(R.id.tvTitle);
        DontAccount=view.findViewById(R.id.tvDidnothaveanaccount);
        relativeLayoutLogin = view.findViewById(R.id.relativeLayoutLogin);



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validatePassword() | !validateEmail() ) {
                    return;
                }
                else{
                    userLogin();
                }



            }
        });

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //startActivity(new Intent(mContext, SignUpActivity.class));

               // startActivity(new Intent(mContext, RegisterFragment.class));
                RegisterFragment fragment=new RegisterFragment();
                FragmentManager manager=getParentFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.replace(R.id.loginFragment,fragment);
                transaction.addToBackStack(null);
                relativeLayoutLogin.setVisibility(GONE);

                transaction.commit();


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
                    try{
                        String val= response.body().string();
                        JSONObject obj= new JSONObject(val);
                        String result = obj.getString("result");
                        JSONObject obj1= new JSONObject(result);
                        String user = obj1.getString("user");
                        JSONObject obj2= new JSONObject(user);
                        _id = obj2.getString("_id");





                    } catch (Exception e) {
                        e.printStackTrace();
                    }





                    SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
                    SharedPreferences.Editor loginPreferences = sharedPreferences.edit();


                    loginPreferences.putString("email", Email.getText().toString());
                    loginPreferences.putString("password", Password.getText().toString());
                    loginPreferences.putBoolean("isLoggedIn", true);
                    loginPreferences.putString("id", _id);
                    loginPreferences.putString("username", username);
                    loginPreferences.apply();


                    Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();

                    String name= Email.getText().toString();
                  String  name1= name.substring(0, name.indexOf("@"));

                    Toast.makeText(getActivity(), "Welcome "+name1, Toast.LENGTH_LONG).show();


//                    getUserDetails();


                    Intent i = new Intent(mContext, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);

                }

                else
                    if (TextUtils.isEmpty(Email.getText().toString()) && TextUtils.isEmpty(Password.getText().toString())) {
                        Toast.makeText(getActivity(), "Email and Password are Required", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        try {
                            String val = response.errorBody().string();
                            JSONObject obj= new JSONObject(val);
                            String message = obj.getString("message");

                            Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();


                        } catch (IOException | JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    {


                     }




            }


            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(mContext, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    //}
}

//    private void getUserDetails() {
//        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
//        String UserId = sh.getString("id", "");
//        ApiService apiService = ApiUtil.getApiService();
//        apiService.userDetailsGet(UserId).enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//
//                //Toast.makeText(getActivity(), "" + response.code(), Toast.LENGTH_SHORT).show();
//
//                if (response.isSuccessful())
//                {
//                    try{
//                        String val= response.body().string();
//                        JSONObject obj= new JSONObject(val);
//                        String username = obj.getString("username");
//                        String phone = obj.getString("phone");
//                        String address = obj.getString("addres");
//                        String gender = obj.getString("gender");
//                        String image = obj.getString("image");
//
//                        //Log.v("abc",address);
//
//                      //  Toast.makeText(getActivity(), "" + username, Toast.LENGTH_LONG).show();
//
//                        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
//                        SharedPreferences.Editor loginPreferences = sharedPreferences.edit();
//                        loginPreferences.putString("username", username);
//                        loginPreferences.putString("phone", phone);
//                        loginPreferences.putString("address", address);
//                        loginPreferences.putString("gender", gender);
//                        loginPreferences.putString("image", image);
//                        loginPreferences.apply();
//
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//
//
//
//
//
//
//                    //Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_SHORT).show();
//
//
//                    Intent i = new Intent(mContext, MainActivity.class);
//                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(i);
//
//                }
//
//
//                else{
//                    try {
//                        String val = response.errorBody().string();
//                        JSONObject obj= new JSONObject(val);
//                        String message = obj.getString("message");
//
//                        Toast.makeText(getActivity(), "" + message, Toast.LENGTH_SHORT).show();
//
//
//                    } catch (IOException | JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//                {
//
//
//                }
//
//
//
//
//            }
//
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(mContext, "Error" +t.getMessage(), Toast.LENGTH_SHORT).show();
//
//            }
//        });
//
//
//    }

    private boolean  isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
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

        else {
            Password.setError(null);
            // Password.setErrorEnabled(false);
            return true;
        }
    }


}