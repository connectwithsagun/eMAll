package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.savatechnology.emall.Adapters.AdapterProduct;

import com.savatechnology.emall.Models.ProductList;

import com.savatechnology.emall.R;

import java.util.ArrayList;
import java.util.List;

public class ProductSearchActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    List<ProductList> lists;
    AdapterProduct adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_search);

        initData();
        initRecycleView();

    }

    private void initRecycleView() {

        recyclerView=findViewById(R.id.favRecyclerView);
        layoutManager=new GridLayoutManager(this,2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        adapter=new AdapterProduct(lists,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        lists=new ArrayList<>();

        lists.add(new ProductList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","cold drinks",50,2.5,"Pangeni Traders"));
        lists.add(new ProductList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","cold drinks",50,2.5,"Pangeni Traders"));
        lists.add(new ProductList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","cold drinks",50,2.5,"Pangeni Traders"));
        lists.add(new ProductList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","cold drinks",50,2.5,"Pangeni Traders"));
        lists.add(new ProductList("https://cdnc.lystit.com/photos/freepeople/margo-denim-midi-skirt-Kaylee%20Wash-7c0aa1d3-.jpeg","cold drinks",50,2.5,"Pangeni Traders"));

    }
}
