package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.savatechnology.emall.Adapters.AdapterSuppliers;
import com.savatechnology.emall.Models.SuppliersList;
import com.savatechnology.emall.R;

import java.util.ArrayList;
import java.util.List;

public class SuppliersSearchActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    GridLayoutManager layoutManager;
    List<SuppliersList> lists;
    AdapterSuppliers adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_search);

        initData();
        initRecycleView();

    }

    private void initRecycleView() {

        recyclerView=findViewById(R.id.favRecyclerView);
        layoutManager=new GridLayoutManager(this,2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
       // adapter=new AdapterSuppliers(lists,this);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        lists=new ArrayList<>();

        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));
        lists.add(new SuppliersList(R.drawable.pepsicola,"Lungeli Traders","Arkhola"));

    }
}
