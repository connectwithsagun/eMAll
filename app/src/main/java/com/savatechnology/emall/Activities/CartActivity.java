package com.savatechnology.emall.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.savatechnology.emall.Adapters.AdapterCart;
import com.savatechnology.emall.Models.CartList;
import com.savatechnology.emall.R;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<CartList> lists;
    AdapterCart adapter;
    Button checkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        initData();
        initRecycleView();

        //calling checkout activity

        checkout = findViewById(R.id.btnCheckOut);
        checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, CheckOutActivity.class));
            }
        });


    }

    private void initRecycleView() {

        recyclerView = findViewById(R.id.favRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        adapter = new AdapterCart(lists, this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        lists = new ArrayList<>();


        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
    }
}
