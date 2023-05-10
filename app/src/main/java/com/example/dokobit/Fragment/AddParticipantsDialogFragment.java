package com.example.dokobit.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dokobit.HomeActivity;
import com.example.dokobit.R;
import com.example.dokobit.UploadActivity;

public class AddParticipantsDialogFragment extends DialogFragment {


    public static AddParticipantsDialogFragment newInstance() {
        return new AddParticipantsDialogFragment();
}

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_addparticipants, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity(), R.style.FullscreenDialogTheme);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(true);
        Button btnSave = view.findViewById(R.id.save);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Xử lý khi click vào nút Save
                // Quay lại HomeActivity
                Intent intent = new Intent(getActivity(), HomeActivity.class);
                startActivity(intent);

                // Đóng dialog
                dismiss();
            }
        });
        ImageView imageClose = view.findViewById(R.id.close_icon);
        imageClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return dialog;
    }
}
