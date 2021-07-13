package com.savatechnology.emall.Fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;


import com.savatechnology.emall.Activities.EditProfileActivity;
import com.savatechnology.emall.Activities.MainActivity;
import com.savatechnology.emall.Activities.PasswordChangeActivity;
import com.savatechnology.emall.R;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AccountFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AccountFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    ///////
    TextView editProfile,changePassword,logout,userName,eMail;
    CardView userCard;
    AlertDialog.Builder builder;

    // CircularImageView userPicture;

    View view;
    private Context mContext;




    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AccountFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AccountFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AccountFragment newInstance(String param1, String param2) {
        AccountFragment fragment = new AccountFragment();
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
//        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
//        Boolean b = sh.getBoolean("isLoggedIn",false);
//        if(b){
//            view = inflater.inflate(R.layout.fragment_account, container, false);
//            init(view);
//            return view;
//        }
        view = inflater.inflate(R.layout.fragment_account, container, false);
        init(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    void init(View view) {

        builder = new AlertDialog.Builder(mContext);
        eMail = view.findViewById(R.id.tvEmail);
        SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("email", "");
        eMail.setText(s1);

        editProfile = view.findViewById(R.id.tvProfile);
        changePassword = view.findViewById(R.id.tvChangePassword);
        logout = view.findViewById(R.id.tvLogout);
        userCard = view.findViewById(R.id.cvUserWishProfile);

        Boolean b = sh.getBoolean("isLoggedIn",false);
        if(!b){
            userCard.setVisibility(View.GONE);
        }

       // fav = view.findViewById(R.id.tvFavourite);


        editProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, EditProfileActivity.class));
            }
        });
        changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mContext, PasswordChangeActivity.class));


            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sh = getActivity().getSharedPreferences("MySharedPref", MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sh.edit();
               // String s1 = sh.getString("email", "");
              //  String s2 = sh.getString("password", "");
                Boolean b1 = sh.getBoolean("isLoggedIn",false);
                if(!b1){
                    Toast.makeText(mContext,"You are not logged in for logout!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    builder.setMessage("Do you want to logout ?")
                            .setCancelable(false)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                    // Fetching the stored data
                                    // from the SharedPreference

                                    myEdit.clear();
                                    myEdit.apply();
                                    Toast.makeText(mContext,"Logout Successfully", Toast.LENGTH_SHORT).show();
                                  startActivity(new Intent(mContext, MainActivity.class));

                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    //  Action for 'NO' Button
                                    dialog.cancel();
                                    //Toast.makeText(mContext,"you choose no action for alertbox", Toast.LENGTH_SHORT).show();
                                }
                            });
                    //Creating dialog box
                    AlertDialog alert = builder.create();
                    //Setting the title manually
                    alert.setTitle("Logout");
                    alert.show();

                }




            }
        });



//        fav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//              // startActivity(new Intent(mContext, FavouritesFragment.class));
//
//
//                //calling favourite fragment class from this fragment class
//
//
//                FavouritesFragment favouritesfragment = new FavouritesFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.accFragment, favouritesfragment);
//                fragmentTransaction.commit();
//
//
//
//
//
//            }
//        });
    }

}