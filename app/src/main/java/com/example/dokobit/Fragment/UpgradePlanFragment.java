package com.example.dokobit.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dokobit.R;

public class UpgradePlanFragment extends Fragment {
    private com.example.dokobit.databinding.UpgradePlanLayoutBinding binding;

    private boolean isHidden = true;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.upgrade_plan_layout, container, false);


        return binding.getRoot();
    }


}
