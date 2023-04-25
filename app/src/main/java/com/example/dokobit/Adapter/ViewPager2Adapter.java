package com.example.dokobit.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dokobit.Fragment.DocumentOptionFragment;
import com.example.dokobit.Fragment.UpFragment;
import com.example.dokobit.Fragment.UploadDocumentFragment;

public class ViewPager2Adapter extends FragmentStateAdapter {

    private static final int NUM_PAGES = 3;

    public ViewPager2Adapter(FragmentManager fragmentManager, Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @Override
    public int getItemCount() {
        return NUM_PAGES;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new UploadDocumentFragment();
            case 1:
                return new DocumentOptionFragment();
            case 2:
                return new UpFragment();
            default:
                return null;
        }
    }
}