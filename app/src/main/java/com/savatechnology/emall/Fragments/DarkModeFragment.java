package com.savatechnology.emall.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ToggleButton;

import com.savatechnology.emall.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DarkModeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DarkModeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ToggleButton darkMode;

    View view;
    private Context mContext;

    public DarkModeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DarkModeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DarkModeFragment newInstance(String param1, String param2) {
        DarkModeFragment fragment = new DarkModeFragment();
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
//        AppCompatDelegate
//                .setDefaultNightMode(
//                        AppCompatDelegate
//                                .MODE_NIGHT_YES);






        View view= inflater.inflate(R.layout.fragment_dark_mode, container, false);
        init(view);
        return view;

    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }
    void init(View view) {
        darkMode = view.findViewById(R.id.tbDarkMode);

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppCompatDelegate
                        .setDefaultNightMode(
                                AppCompatDelegate
                                        .MODE_NIGHT_YES);



//
//                if (isDarkModeOn) {
//
//                    // if dark mode is on it
//                    // will turn it off
//                    AppCompatDelegate
//                            .setDefaultNightMode(
//                                    AppCompatDelegate
//                                            .MODE_NIGHT_NO);
//                    // it will set isDarkModeOn
//                    // boolean to false
//                    editor.putBoolean(
//                            "isDarkModeOn", false);
//                    editor.apply();
//
//                    // change text of Button
//                    btnToggleDark.setText(
//                            "Enable Dark Mode");
//                }
//                else {
//
//                    // if dark mode is off
//                    // it will turn it on
//                    AppCompatDelegate
//                            .setDefaultNightMode(
//                                    AppCompatDelegate
//                                            .MODE_NIGHT_YES);
//
//                    // it will set isDarkModeOn
//                    // boolean to true
//                    editor.putBoolean(
//                            "isDarkModeOn", true);
//                    editor.apply();
//
//                    // change text of Button
//                    btnToggleDark.setText(
//                            "Disable Dark Mode");
//                }


            }
        });

    }

    }
