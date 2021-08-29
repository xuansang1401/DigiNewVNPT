package com.gpaddy.baseandroid.quyen.diginews.fragment;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int pageNum;

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        this.pageNum = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new HomeFragment();
            case 1:
                return new VideoFragment();
            case 2:
                return new ExploreFragment();
            case 3:
                return new CategoryFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getCount() {
        return pageNum;
    }
}

