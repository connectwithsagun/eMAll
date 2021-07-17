package com.savatechnology.emall.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;
import com.savatechnology.emall.Adapters.PageAdapter;
import com.savatechnology.emall.R;

public class SuppliersDetailActivity extends AppCompatActivity {

    TabLayout tabLayout;
    TabItem tabItem1,tabItem2;
    ViewPager viewPager;
    PageAdapter pageAdapter;
    ImageView imageSupplier;
    TextView supplierName,supplierLocation,supplierPhone;
    String sName,sLocation,sPhone,sImage,sId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suppliers_detail);

        supplierName =findViewById(R.id.tvSupplierName);
        supplierLocation =findViewById(R.id.tvSupplierLocation);
        supplierPhone =findViewById(R.id.tvSupplierPhone);
        imageSupplier = (ImageView)findViewById(R.id.imageSupplier);

        sName = getIntent().getStringExtra("supplierName");
        sLocation = getIntent().getStringExtra("supplierLocation");
        sPhone = getIntent().getStringExtra("supplierPhone");
        sImage = getIntent().getStringExtra("supplierImage");
        sId = getIntent().getStringExtra("supplierId");

        supplierName.setText(sName);
        supplierLocation.setText(sLocation);
        supplierPhone.setText(sPhone);

        // loading supplier image
        Glide.with(this)
                .asBitmap()
                .load(sImage)
                .into(imageSupplier);
//Log.v("abc",sPhone);

        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        SharedPreferences.Editor loginPreferences = sharedPreferences.edit();
        loginPreferences.putString("sId", sId);

        tabLayout=(TabLayout)findViewById(R.id.tab_layout1);
        tabItem1=(TabItem)findViewById(R.id.tab1);
        tabItem2=(TabItem)findViewById(R.id.tab2);
        viewPager=(ViewPager)findViewById(R.id.vPager);

        pageAdapter=new PageAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(pageAdapter);


        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());


                if(tab.getPosition()==0 || tab.getPosition()==1 )
                    pageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));


        //listen for scroll or page change

    }
}