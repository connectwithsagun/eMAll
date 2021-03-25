package com.savatechnology.emall.Activities;


import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.savatechnology.emall.Adapters.AdapterSlider;
import com.savatechnology.emall.Models.SliderImageList;
import com.savatechnology.emall.R;
import com.smarteist.autoimageslider.IndicatorView.draw.controller.DrawController;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {


    List<SliderImageList> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        data = new ArrayList<>();
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/3769747/pexels-photo-3769747.jpeg?cs=srgb&dl=pexels-andrea-piacquadio-3769747.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/34577/pexels-photo.jpg?cs=srgb&dl=pexels-negative-space-34577.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/5632364/pexels-photo-5632364.jpeg?cs=srgb&dl=pexels-karolina-grabowska-5632364.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/6461519/pexels-photo-6461519.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6461519.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/2697786/pexels-photo-2697786.jpeg?cs=srgb&dl=pexels-%F0%9D%90%95%F0%9D%90%9E%F0%9D%90%A7%F0%9D%90%AE%F0%9D%90%AC-%F0%9D%90%87%F0%9D%90%83-%F0%9D%90%8C%F0%9D%90%9A%F0%9D%90%A4%F0%9D%90%9E-%F0%9D%90%AE%F0%9D%90%A9-%26-%F0%9D%90%8F%F0%9D%90%9E%F0%9D%90%AB%F0%9D%90%9F%F0%9D%90%AE%F0%9D%90%A6%F0%9D%90%9E-2697786.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/6461511/pexels-photo-6461511.jpeg?cs=srgb&dl=pexels-pavel-danilyuk-6461511.jpg&fm=jpg"));
        data.add(new SliderImageList("Cold Drinks", "https://images.pexels.com/photos/2697786/pexels-photo-2697786.jpeg?cs=srgb&dl=pexels-%F0%9D%90%95%F0%9D%90%9E%F0%9D%90%A7%F0%9D%90%AE%F0%9D%90%AC-%F0%9D%90%87%F0%9D%90%83-%F0%9D%90%8C%F0%9D%90%9A%F0%9D%90%A4%F0%9D%90%9E-%F0%9D%90%AE%F0%9D%90%A9-%26-%F0%9D%90%8F%F0%9D%90%9E%F0%9D%90%AB%F0%9D%90%9F%F0%9D%90%AE%F0%9D%90%A6%F0%9D%90%9E-2697786.jpg&fm=jpg"));
        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new AdapterSlider(this, data));

        sliderView.setOnIndicatorClickListener(new DrawController.ClickListener() {
            @Override
            public void onIndicatorClicked(int position) {
                Log.i("GGG", "onIndicatorClicked: " + sliderView.getCurrentPagePosition());
            }
        });

    }
}

