package com.example.dokobit.Fragment;

import android.app.Dialog;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.dokobit.Myinterface.OnFileNameSelectedListener;
import com.example.dokobit.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment{
    private ActivityResultLauncher<String> mGetContent;

    private OnFileNameSelectedListener mListener;

    public void setOnFileNameSelectedListener(OnFileNameSelectedListener listener) {
        this.mListener = listener;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFileNameSelectedListener) {
            mListener = (OnFileNameSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFileNameSelectedListener");
        }
    }
    private void onFileNameSelected(String fileName) {
        if (mListener != null) {
            mListener.onFileNameSelected(fileName);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog Bottomsheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);

        Bottomsheet.setContentView(view);
        mListener = (OnFileNameSelectedListener) getActivity();
        setOnFileNameSelectedListener(mListener);

        LinearLayout selectfile = view.findViewById(R.id.upload_file);
        TextView file = view.findViewById(R.id.filename);
        TextView mTextFile = requireActivity().findViewById(R.id.text_file_name);
        mListener = (OnFileNameSelectedListener) getActivity();
        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mGetContent.launch("*/*");
            }
        });
        mGetContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                String fileName = getFileNameFromUri(result);
                if (mListener != null) {
                    mListener.onFileNameSelected(fileName);
                    mTextFile.setText(fileName);

                }
                dismiss();
            }
        });
        return Bottomsheet;
    }
    private String getFileNameFromUri(Uri uri) {
        String fileName = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = requireActivity().getContentResolver()
                    .query(uri, null, null, null, null);
            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME);
                if (columnIndex != -1) {
                    fileName = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        } else if (uri.getScheme().equals("file")) {
            fileName = uri.getLastPathSegment();
        }
        return fileName;
    }


}
