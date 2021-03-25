package com.savatechnology.emall.Adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.savatechnology.emall.Fragments.SuppliersAllPageTabFragment;
import com.savatechnology.emall.Fragments.SuppliersHomePageTabFragment;

public class PageAdapter extends FragmentPagerAdapter {

    int tabcount;
    public PageAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0 : return new SuppliersHomePageTabFragment();
            case 1 : return new SuppliersAllPageTabFragment();
            default:return null;
        }

    }

    @Override
    public int getCount() {
        return tabcount;
    }
}
