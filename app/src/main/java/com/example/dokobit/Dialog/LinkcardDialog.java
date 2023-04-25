package com.example.dokobit.Dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.fragment.app.DialogFragment;

import com.example.dokobit.R;

public class LinkcardDialog extends DialogFragment {
    public LinkcardDialog() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Set up views or do other initialization here
        View view = inflater.inflate(R.layout.dialog_linkcard, container, false);

        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.white);
        getDialog().getWindow().setGravity(Gravity.TOP);
        getDialog().getWindow().setDimAmount(0.8f);

        Button cancel = view.findViewById(R.id.btn_cancel_linkcard);

        ImageView closeButton = view.findViewById(R.id.close_icon1);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
