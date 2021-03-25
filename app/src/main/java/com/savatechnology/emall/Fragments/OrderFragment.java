package com.savatechnology.emall.Fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.savatechnology.emall.Adapters.AdapterOrderList;
import com.savatechnology.emall.Models.OrderList;
import com.savatechnology.emall.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link OrderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OrderFragment extends Fragment {




    RecyclerView recyclerView;
    List<OrderList> lists;
    AdapterOrderList adapter;
    Context mContext;
    LinearLayoutManager layoutManager;
    private View view;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public OrderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OrderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static OrderFragment newInstance(String param1, String param2) {
        OrderFragment fragment = new OrderFragment();
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

        view = inflater.inflate(R.layout.fragment_order, container, false);
        initRecycleViewForSlider(view);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    private void initRecycleViewForSlider(View view) {



        lists = new ArrayList<>();


        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));
        lists.add(new OrderList(R.drawable.pepsicola,"Cold Drinks","Lungeli Traders","Accepted"));



        recyclerView = view.findViewById(R.id.favRecyclerView);
        layoutManager=new LinearLayoutManager(mContext);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterOrderList(lists);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }


}


