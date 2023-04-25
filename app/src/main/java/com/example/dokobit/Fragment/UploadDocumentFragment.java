package com.example.dokobit.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.dokobit.R;

import java.util.ArrayList;
import java.util.List;

public class UploadDocumentFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_upload_file, container, false);



        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                // Xử lý khi nhấn nút back
                if (getFragmentManager() != null) {
                    getFragmentManager().popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                } else {
                    requireActivity().onBackPressed();
                }
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
    private void clickOpenSelectFile(){
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
}
