package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import com.savatechnology.emall.R;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.savatechnology.emall.Adapters.AdapterFavouriteList;
import com.savatechnology.emall.Models.FavouriteList;

import java.util.ArrayList;
import java.util.List;

public class FavouriteActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    List<FavouriteList> lists;
    AdapterFavouriteList adapter;

    public FavouriteActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favourite);
       initData();
        initRecycleView();

    }

    private void initRecycleView() {

        recyclerView=findViewById(R.id.favRecyclerView);
        layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager((layoutManager));
        adapter=new AdapterFavouriteList(lists);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void initData() {

        lists=new ArrayList<>();
        lists.add(new FavouriteList(R.drawable.laptop," Hello","11:18","How are you?","--------"));
        lists.add(new FavouriteList(R.drawable.laptop,"Hi!!!","11:18","I'm fine","--------"));
        lists.add(new FavouriteList(R.drawable.laptop," Hello","11:18","How are you?","--------"));
        lists.add(new FavouriteList(R.drawable.laptop,"Hi!!!","11:18","I'm fine","--------"));
        lists.add(new FavouriteList(R.drawable.laptop," Hello","11:18","How are you?","--------"));
        lists.add(new FavouriteList(R.drawable.laptop,"Hi!!!","11:18","I'm fine","--------"));
        lists.add(new FavouriteList(R.drawable.laptop," Hello","11:18","How are you?","--------"));
        lists.add(new FavouriteList(R.drawable.laptop,"Hi!!!","11:18","I'm fine","--------"));
        lists.add(new FavouriteList(R.drawable.laptop," Hello","11:18","How are you?","--------"));
        lists.add(new FavouriteList(R.drawable.laptop,"Hi!!!","11:18","I'm fine","--------"));


    }
}