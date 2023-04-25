package com.example.dokobit.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.dokobit.R;

public class AddParticipantsDialogFragment extends DialogFragment {


    public static AddParticipantsDialogFragment newInstance() {
        return new AddParticipantsDialogFragment();
}
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_addparticipants, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity())
                .setView(view);

        return builder.create();
    }
}
