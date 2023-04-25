package com.example.dokobit.Fragment;

import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.dokobit.Dialog.LinkcardDialog;
import com.example.dokobit.Dialog.LoadingDialog;
import com.example.dokobit.R;
import com.example.dokobit.databinding.FragmentBillingBinding;
import com.example.dokobit.databinding.UpgradePlanLayoutBinding;


public class BillingFragment extends Fragment implements LoadingDialog.LoadingDialogListener{

    private FragmentBillingBinding binding;
    private UpgradePlanLayoutBinding upgradePlanBinding;
    private LoadingDialog loadingDialog;

    private int currentLayout = 1;

    private boolean isHidden = true;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_billing, container, false);


        if (currentLayout == 1) {
            return binding.getRoot();
        } else {
            return upgradePlanBinding.getRoot();
        }
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set click listener for button in layout 1
        binding.upgrade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentLayout == 1) {
                    ViewGroup parent = (ViewGroup) binding.getRoot().getParent();
                    parent.removeView(binding.getRoot());
                    upgradePlanBinding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.upgrade_plan_layout, parent, false);

                    String text = "Dokobit works with Qualified Electronic Signatures and Advanced Electronic Signatures defined in the elDAS regulation. The price of each signature includes qualified timestamps. See signature prices in different countries.";

                    int startIndex = text.indexOf("See signature prices in different countries.");
                    int endIndex = startIndex + "See signature prices in different countries.".length();

                    SpannableString spannableString = new SpannableString(text);
                    spannableString.setSpan(new ForegroundColorSpan(ContextCompat.getColor(getContext(), R.color.colorPrimary)), startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

                    upgradePlanBinding.textView.setText(spannableString);



                    if (isHidden) {
                        upgradePlanBinding.expandableLayout.setVisibility(View.GONE);
                    } else {
                        upgradePlanBinding.expandableLayout.setVisibility(View.VISIBLE);
                    }// inflate layout UpgradePlan
                    upgradePlanBinding.showAll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            showExpandableLayout(v);
                        }
                    });
                    upgradePlanBinding.showAll1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isHidden) {
                                // Nếu layout bị ẩn thì hiển thị nó lên
                                upgradePlanBinding.expandableLayout1.setVisibility(View.VISIBLE);
                                upgradePlanBinding.showAll1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0);
                                upgradePlanBinding.showAll1.setText("Hide all features");
                                isHidden = false;

                            } else {
                                // Nếu layout đang hiển thị thì ẩn nó đi
                                upgradePlanBinding.expandableLayout1.setVisibility(View.GONE);
                                upgradePlanBinding.showAll1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
                                upgradePlanBinding.showAll1.setText("Show all features");
                                isHidden = true;

                            }
                        }
                    });
                    parent.addView(upgradePlanBinding.getRoot()); // thêm layout UpgradePlan vào parent
                    currentLayout = 2;
                }
            }
        });
        binding.linkcard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showLoadingDialog();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.onLoadingFinished();
                    }
                }, 5000);
            }
        });
    }
    public void showExpandableLayout(View view) {
        if (isHidden) {
            // Nếu layout bị ẩn thì hiển thị nó lên
            upgradePlanBinding.expandableLayout.setVisibility(View.VISIBLE);
            upgradePlanBinding.showAll.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_up, 0);
            upgradePlanBinding.showAll.setText("Hide all features");
            isHidden = false;

        } else {
            // Nếu layout đang hiển thị thì ẩn nó đi
            upgradePlanBinding.expandableLayout.setVisibility(View.GONE);
            upgradePlanBinding.showAll.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_down, 0);
            upgradePlanBinding.showAll.setText("Show all features");
            isHidden = true;

        }
    }
    private void showLoadingDialog() {
        if (loadingDialog == null) {
            loadingDialog = new LoadingDialog();

        }
        loadingDialog.setLoadingDialogListener(this);

        // Hiển thị Dialog loading
        loadingDialog.show(getParentFragmentManager(), "LoadingDialog");
    }

    private void hideLoadingDialog() {
        if (loadingDialog != null) {
            loadingDialog.dismiss();
            loadingDialog = null;
        }
    }


    @Override
    public void onLoadingFinished() {

        hideLoadingDialog();

        LinkcardDialog linkcardDialog = new LinkcardDialog();
        linkcardDialog.show(getParentFragmentManager(), "LinkcardDialog");
    }
}
