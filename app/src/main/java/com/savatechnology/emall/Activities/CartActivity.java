package com.savatechnology.emall.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.savatechnology.emall.Adapters.AdapterCart;
import com.savatechnology.emall.Adapters.AdapterHomeNewArrivalsProduct;
import com.savatechnology.emall.JSONSchemas.Cart;
import com.savatechnology.emall.JSONSchemas.NewArrivalProduct;
import com.savatechnology.emall.Models.CartList;
import com.savatechnology.emall.Models.HomeNewArrivalsProductList;
import com.savatechnology.emall.R;
import com.savatechnology.emall.Remote.ApiService;
import com.savatechnology.emall.Remote.ApiUtil;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity {
    List<Cart> cartList;
    AdapterCart adapterCart;
    Context mContext;
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

//        recyclerView = findViewById(R.id.favRecyclerView);
//        layoutManager = new LinearLayoutManager(this);
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        recyclerView.setLayoutManager((layoutManager));
//        adapter = new AdapterCart(lists, this);
//        recyclerView.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


        SharedPreferences sh = getSharedPreferences("MySharedPref",MODE_PRIVATE);
        String s1 = sh.getString("id", "");
        ApiService apiService = ApiUtil.getApiService();
        apiService.getCartList(s1).enqueue(new Callback<List<Cart>>() {
            @Override
            public void onResponse(Call<List<Cart>> call, Response<List<Cart>> response) {
                if (response.isSuccessful()) {


                    List<Cart> newArrivalProducts = response.body();
                    recyclerView = findViewById(R.id.favRecyclerView);
                    layoutManager = new GridLayoutManager(getApplicationContext(), 1);
                    //layoutManager.setOrientation(RecyclerView.VERTICAL);
                    recyclerView.setLayoutManager(layoutManager);
                    adapterCart = new AdapterCart(newArrivalProducts, getApplicationContext());
                    recyclerView.setAdapter(adapterCart);
                    adapterCart.notifyDataSetChanged();
                }
            }



            @Override
            public void onFailure(Call<List<Cart>> call, Throwable t) {
            }
        });




    }

    private void initData() {

        lists = new ArrayList<>();


//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
//        lists.add(new CartList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg", "Women's Jacket", 500, 200));
    }
}
