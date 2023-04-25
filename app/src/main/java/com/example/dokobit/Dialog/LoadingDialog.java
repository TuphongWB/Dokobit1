package com.example.dokobit.Dialog;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.fragment.app.DialogFragment;

import com.example.dokobit.R;

public class LoadingDialog extends DialogFragment {
    private ProgressBar progressBar;
    private LoadingDialogListener listener;
    private int numLoading = 0;

    public LoadingDialog() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_bankcard, container, false);
        progressBar = view.findViewById(R.id.progressBar1);
        getDialog().getWindow().setBackgroundDrawableResource(android.R.color.white);
        getDialog().getWindow().setGravity(Gravity.TOP);
        getDialog().getWindow().setDimAmount(0.8f);
        Window window = getDialog().getWindow();
        window.setLayout((int) (getResources().getDisplayMetrics().widthPixels * 0.8), ViewGroup.LayoutParams.WRAP_CONTENT);
        getDialog().setCancelable(true);
        getDialog().setCanceledOnTouchOutside(false);
        Button cancel = view.findViewById(R.id.dialog_btn_cancel);
        ImageView closeButton = view.findViewById(R.id.close_icon);
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
    public interface LoadingDialogListener {
        void onLoadingFinished();
    }
    public void setLoadingDialogListener(LoadingDialogListener listener) {
        this.listener = listener;
    }
    public void onLoadingFinished() {
        if (listener != null) {
            listener.onLoadingFinished();
        }
    }
}
