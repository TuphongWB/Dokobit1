package com.example.dokobit.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.dokobit.Fragment.DocumentOptionFragment;
import com.example.dokobit.Fragment.UpFragment;
import com.example.dokobit.Fragment.UploadDocumentFragment;
import com.example.dokobit.Myinterface.OnFileNameSelectedListener;

import java.util.ArrayList;
import java.util.List;

public class ViewPager2Adapter extends FragmentStateAdapter {
    private UploadDocumentFragment uploadFragment;
    private DocumentOptionFragment documentOptionFragment;
    private UpFragment upFragment;
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
                if (uploadFragment == null) {
                    uploadFragment = new UploadDocumentFragment();
                }
                return  uploadFragment;
            case 1:
                if (documentOptionFragment == null) {
                    documentOptionFragment = new DocumentOptionFragment();
                }
                return documentOptionFragment;
            case 2:
                if (upFragment == null) {
                    upFragment = new UpFragment();
                }
                return upFragment;
            default:
                return null;
        }
    }

}