package com.example.dokobit.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.dokobit.R;

public class AddAliasDialog extends DialogFragment {
    private EditText mAliasEditText;
    private Button mCancelButton;
    private Button mAddButton;

    public AddAliasDialog() {
        // Required empty public constructor
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_alias, null);
        mAliasEditText = view.findViewById(R.id.text_input_edit_text);
        mCancelButton = view.findViewById(R.id.dialog_btn_cancel);
        mAddButton = view.findViewById(R.id.dialog_btn_add);
        ImageView closeButton = view.findViewById(R.id.close_icon);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        builder.setView(view);
        return builder.create();
    }
}
