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

import com.example.dokobit.Adapter.ViewPager2Adapter;
import com.example.dokobit.Myinterface.OnFileNameSelectedListener;
import com.example.dokobit.R;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

public class BottomSheetDialogFragment extends com.google.android.material.bottomsheet.BottomSheetDialogFragment{


    private OnFileNameSelectedListener mListener;



    private ActivityResultLauncher<String> getContent;
    public void setOnFileNameSelectedListener(OnFileNameSelectedListener listener) {
        this.mListener = listener;
    }



    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        BottomSheetDialog Bottomsheet = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.layout_bottom_sheet, null);

        Bottomsheet.setContentView(view);


        LinearLayout selectfile = view.findViewById(R.id.upload_file);

        selectfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFilePicker();

            }
        });
        return Bottomsheet;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFilePickerActivityResult();


    }
    private void setupFilePickerActivityResult() {
        getContent = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                String fileName = getFileNameFromUri(result);
                if (mListener != null) {
                    mListener.onFileNameSelected(fileName);


                }
                dismiss();
            }
        });
    }
    private void openFilePicker() {
        getContent.launch("*/*");
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
