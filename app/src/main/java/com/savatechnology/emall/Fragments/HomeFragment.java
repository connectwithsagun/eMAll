package com.savatechnology.emall.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.savatechnology.emall.Adapters.AdapterHomeFeaturedProduct;
import com.savatechnology.emall.Adapters.AdapterHomeJustForYou;
import com.savatechnology.emall.Adapters.AdapterHomeNewArrivalsProduct;
import com.savatechnology.emall.Adapters.AdapterHomeProductCollection;
import com.savatechnology.emall.Adapters.AdapterHomeSupplier;
import com.savatechnology.emall.Adapters.AdapterSlider;
import com.savatechnology.emall.Models.HomeFeaturedProductList;
import com.savatechnology.emall.Models.HomeJustForYouList;
import com.savatechnology.emall.Models.HomeNewArrivalsProductList;
import com.savatechnology.emall.Models.HomeProductCollectionList;
import com.savatechnology.emall.Models.HomeSupplierList;
import com.savatechnology.emall.Models.SliderImageList;
import com.savatechnology.emall.R;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    List<SliderImageList> data;
    Context mContext;



    //for home product collection items

    RecyclerView recyclerView;
    List<HomeProductCollectionList> lists;
    AdapterHomeProductCollection adapter;
    GridLayoutManager layoutManager;
    public View view;

    //for home featured product items

    List<HomeFeaturedProductList> homeFeaturedProductLists;
    AdapterHomeFeaturedProduct featureproductadapter;

    //for home new arrivals product

    List<HomeNewArrivalsProductList> homeNewArrivalsProductLists;
    AdapterHomeNewArrivalsProduct newarrivalproductadapter;

    //for supplier in home

    List<HomeSupplierList> homeSupplierLists;
    AdapterHomeSupplier adapterHomeSupplier;

    //for just for you

    List<HomeJustForYouList> homeJustForYouList;
    AdapterHomeJustForYou adapterHomeJustForYou;






    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        //for image slider
        view = inflater.inflate(R.layout.fragment_home, container, false);


//for home product items
        initRecycleViewForHomeProduct(view);
        initRecycleViewForHomeFeaturedProduct(view);
        initRecycleViewForHomeNewArrivalsProduct(view);
        initRecycleViewForHomeSupplier(view);
        initRecycleViewForHomeJustForYou(view);

        //for image slider

        data = new ArrayList<>();
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/3769747/pexels-photo-3769747.jpeg?cs=srgb&dl=pexels-andrea-piacquadio-3769747.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/34577/pexels-photo.jpg?cs=srgb&dl=pexels-negative-space-34577.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/5632364/pexels-photo-5632364.jpeg?cs=srgb&dl=pexels-karolina-grabowska-5632364.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/6461519/pexels-photo-6461519.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6461519.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/2697786/pexels-photo-2697786.jpeg?cs=srgb&dl=pexels-%F0%9D%90%95%F0%9D%90%9E%F0%9D%90%A7%F0%9D%90%AE%F0%9D%90%AC-%F0%9D%90%87%F0%9D%90%83-%F0%9D%90%8C%F0%9D%90%9A%F0%9D%90%A4%F0%9D%90%9E-%F0%9D%90%AE%F0%9D%90%A9-%26-%F0%9D%90%8F%F0%9D%90%9E%F0%9D%90%AB%F0%9D%90%9F%F0%9D%90%AE%F0%9D%90%A6%F0%9D%90%9E-2697786.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/6461511/pexels-photo-6461511.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6461511.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/2697786/pexels-photo-2697786.jpeg?cs=srgb&dl=pexels-%F0%9D%90%95%F0%9D%90%9E%F0%9D%90%A7%F0%9D%90%AE%F0%9D%90%AC-%F0%9D%90%87%F0%9D%90%83-%F0%9D%90%8C%F0%9D%90%9A%F0%9D%90%A4%F0%9D%90%9E-%F0%9D%90%AE%F0%9D%90%A9-%26-%F0%9D%90%8F%F0%9D%90%9E%F0%9D%90%AB%F0%9D%90%9F%F0%9D%90%AE%F0%9D%90%A6%F0%9D%90%9E-2697786.jpg&fm=jpg"));
        SliderView sliderView = view.findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new AdapterSlider(mContext, data));

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });
        return view;
    }

    private void initRecycleViewForHomeJustForYou(View view) {

        homeJustForYouList = new ArrayList<>();



        homeJustForYouList.add(new HomeJustForYouList("https://newsladder.net/wp-content/uploads/2016/05/luxury-watches-mens.jpg", "Watch",5000));
        homeJustForYouList.add(new HomeJustForYouList("https://1000logos.net/wp-content/uploads/2017/05/Logo-Red-Bull.png", "Cold drink",600));



        recyclerView = view.findViewById(R.id.rcvJustForYou);
        layoutManager = new GridLayoutManager(mContext, 2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapterHomeJustForYou = new AdapterHomeJustForYou(homeJustForYouList,mContext);
        recyclerView.setAdapter(adapterHomeJustForYou);
        adapterHomeJustForYou.notifyDataSetChanged();



    }

    private void initRecycleViewForHomeSupplier(View view) {

        homeSupplierLists = new ArrayList<>();



        homeSupplierLists.add(new HomeSupplierList("https://1000logos.net/wp-content/uploads/2017/05/Logo-Red-Bull.png", "Lungeli Traders"));
        homeSupplierLists.add(new HomeSupplierList("https://1000logos.net/wp-content/uploads/2017/05/Logo-Red-Bull.png", "Lungeli Traders"));



        recyclerView = view.findViewById(R.id.rcvSupplier);
        layoutManager = new GridLayoutManager(mContext, 2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapterHomeSupplier = new AdapterHomeSupplier(homeSupplierLists,mContext);
        recyclerView.setAdapter(adapterHomeSupplier);
        adapterHomeSupplier.notifyDataSetChanged();


    }

    private void initRecycleViewForHomeNewArrivalsProduct(View view) {


        homeNewArrivalsProductLists = new ArrayList<>();



        homeNewArrivalsProductLists.add(new HomeNewArrivalsProductList("https://weneedfun.com/wp-content/uploads/2016/07/Muscle-Cars-16.jpg", "Car",500000));
        homeNewArrivalsProductLists.add(new HomeNewArrivalsProductList("https://weneedfun.com/wp-content/uploads/2016/07/Muscle-Cars-16.jpg", "Car",500000));



        recyclerView = view.findViewById(R.id.rcvNewArrivalproduct);
        layoutManager = new GridLayoutManager(mContext, 2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        newarrivalproductadapter = new AdapterHomeNewArrivalsProduct(homeNewArrivalsProductLists,mContext);
        recyclerView.setAdapter(newarrivalproductadapter);
        newarrivalproductadapter.notifyDataSetChanged();


    }

    private void initRecycleViewForHomeFeaturedProduct(View view) {



        homeFeaturedProductLists = new ArrayList<>();



        homeFeaturedProductLists.add(new HomeFeaturedProductList("https://www.sheffield-systems.co.uk/wp-content/uploads/2017/06/mobile-phones.jpg", "Mobiles",10000));
        homeFeaturedProductLists.add(new HomeFeaturedProductList("https://www.sheffield-systems.co.uk/wp-content/uploads/2017/06/mobile-phones.jpg", "Mobiles",10000));




        recyclerView = view.findViewById(R.id.rcvHomeFeaturedProduct);
        layoutManager = new GridLayoutManager(mContext, 2);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        featureproductadapter = new AdapterHomeFeaturedProduct(homeFeaturedProductLists,mContext);
        recyclerView.setAdapter(featureproductadapter);
        featureproductadapter.notifyDataSetChanged();






    }

    private void initRecycleViewForHomeProduct(View view) {


        lists = new ArrayList<>();




        lists.add(new HomeProductCollectionList("https://getwallpapers.com/wallpaper/full/b/a/7/1404433-pepsi-logo-wallpaper-2560x1600-mobile.jpg", "Cold Drinks"));
        lists.add(new HomeProductCollectionList("https://getwallpapers.com/wallpaper/full/b/a/7/1404433-pepsi-logo-wallpaper-2560x1600-mobile.jpg", "Cold Drinks"));
        lists.add(new HomeProductCollectionList("https://getwallpapers.com/wallpaper/full/b/a/7/1404433-pepsi-logo-wallpaper-2560x1600-mobile.jpg", "Cold Drinks"));



        recyclerView = view.findViewById(R.id.rcvHomeProduct);
        layoutManager = new GridLayoutManager(mContext, 3);
        //layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new AdapterHomeProductCollection(lists,mContext);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    }


}