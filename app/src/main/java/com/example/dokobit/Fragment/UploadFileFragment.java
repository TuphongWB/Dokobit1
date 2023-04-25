package com.example.dokobit.Fragment;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Adapter.FileApdater;
import com.example.dokobit.Model.File;
import com.example.dokobit.Myinterface.IClickFile;
import com.example.dokobit.R;

import java.util.List;

public class UploadFileFragment extends DialogFragment {
    private final List<File> mListFile;

    private final IClickFile iClickFile;

    public UploadFileFragment(List<File> mListFile, IClickFile iClickFile) {
        this.mListFile = mListFile;
        this.iClickFile = iClickFile;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog selectfile = super.onCreateDialog(savedInstanceState);

        View view = getLayoutInflater().inflate(R.layout.dialog_file, null);
        selectfile.setContentView(view);
        RecyclerView rcvFile = view.findViewById(R.id.rcv_file);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvFile.setLayoutManager(linearLayoutManager);

        FileApdater fileApdater = new FileApdater(getContext(), mListFile, new IClickFile() {
            @Override
            public void clickItem(File file) {
                iClickFile.clickItem(file);
                dismiss();
            }
        });
        rcvFile.setAdapter(fileApdater);
        Button btnCancel = view.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        selectfile.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return selectfile;
    }
}
