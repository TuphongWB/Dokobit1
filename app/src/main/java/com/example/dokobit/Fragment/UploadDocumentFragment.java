package com.example.dokobit.Fragment;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.dokobit.Model.File;
import com.example.dokobit.Myinterface.IClickFile;
import com.example.dokobit.Myinterface.OnFileNameSelectedListener;
import com.example.dokobit.R;

import java.util.ArrayList;
import java.util.List;

public class UploadDocumentFragment extends Fragment implements OnFileNameSelectedListener{

    private TextView mTextfilename;

    private OnFileNameSelectedListener mListener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFileNameSelectedListener) {
            mListener = (OnFileNameSelectedListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFileNameSelectedListener");
        }
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_file, container, false);

        mTextfilename = view.findViewById(R.id.text_file_name);



        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Xử lý khi nhấn nút back
                if (getParentFragmentManager() != null) {
                    getParentFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    requireActivity().onBackPressed();
                }
            }
        });

        Button btn_upload = view.findViewById(R.id.button_upload);
        btn_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    clickOpenBottomSheet();
                }
        });
        CardView cardView = view.findViewById(R.id.cardFile);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOpenSelectFile();
            }
        });

        return view;

    }

    private void clickOpenBottomSheet() {
        BottomSheetDialogFragment bottomSheetDialogFragment = new BottomSheetDialogFragment();
        bottomSheetDialogFragment.setOnFileNameSelectedListener(this);
        bottomSheetDialogFragment.show(getChildFragmentManager(), bottomSheetDialogFragment.getTag());


    }

    private void clickOpenSelectFile() {
        List<File> list = new ArrayList<>();
        list.add(new File(R.drawable.asic, "ASiC"));
        list.add(new File(R.drawable.bdoc, "BDoc"));
        list.add(new File(R.drawable.pdf, "PDF"));
        list.add(new File(R.drawable.edoc, "EDoc"));
        list.add(new File(R.drawable.adoc, "ADoc (CeDoc)"));

        UploadFileFragment selectFile = new UploadFileFragment(list, clickFileListener);
        selectFile.show(requireActivity().getSupportFragmentManager(), selectFile.getTag());
    }

    private final IClickFile clickFileListener = new IClickFile() {
        @Override
        public void clickItem(File file) {
            View view = getView();
            if (view != null) {
                TextView textView = view.findViewById(R.id.tv_file);
                textView.setText(file.getName());
                ImageView img = view.findViewById(R.id.img_file);
                img.setImageResource(file.getImage());
            }
        }
    };
    public void setFileName(String fileName) {
        if (mTextfilename != null) {
            mTextfilename.setText(fileName);
        }
    }

    @Override
    public void onFileNameSelected(String fileName) {
        mTextfilename.setText(fileName);
    }
}
