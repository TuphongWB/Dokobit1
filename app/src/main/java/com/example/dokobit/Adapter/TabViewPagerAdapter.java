package com.example.dokobit.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dokobit.Fragment.BillingFragment;
import com.example.dokobit.Fragment.ProfileFragment;

public class TabViewPagerAdapter extends FragmentStateAdapter {
    private static final int NUM_PAGES = 2;

    public TabViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ProfileFragment();
            case 1:
                return new BillingFragment();
            default:
                return new ProfileFragment();
        }
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }
}

