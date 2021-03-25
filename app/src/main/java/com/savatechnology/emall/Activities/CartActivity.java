package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;


import com.savatechnology.emall.Adapters.AdapterCart;
import com.savatechnology.emall.Models.CartList;
import com.savatechnology.emall.Models.ProductList;

import com.savatechnology.emall.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<CartList> lists;
    AdapterCart adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initData();
        initRecycleView();

    }

    private void initRecycleView() {

        recyclerView=findViewById(R.id.favRecyclerView);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        adapter=new AdapterCart(lists,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        lists=new ArrayList<>();







        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","Women's Jacket",500,200));
    }
}
