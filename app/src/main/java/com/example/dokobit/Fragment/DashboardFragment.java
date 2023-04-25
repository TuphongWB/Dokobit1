package com.example.dokobit.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dokobit.Adapter.DocumentAdapter;
import com.example.dokobit.Model.DocumentModel;
import com.example.dokobit.R;
import com.example.dokobit.SettingActivity;
import com.example.dokobit.UploadActivity;
import com.example.dokobit.ValidateActivity;
import com.example.dokobit.databinding.FragmentDashboardBinding;

import java.util.ArrayList;
import java.util.List;

public class DashboardFragment extends Fragment {
    private ViewGroup mEmptyState;
    private FragmentDashboardBinding binding;
    private List<DocumentModel> mListDoc;
    private DocumentAdapter mDoc;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);


        binding.rcvDoc.setLayoutManager(new LinearLayoutManager(getContext()));
        int emptyStateImage = R.drawable.ic_empty;
        String emptyStateTitle = getString(R.string.empty_state_title);
        String emptyStateSubtitle = getString(R.string.empty_state_subtitle);

        binding.cardUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UploadActivity.class);
                startActivity(intent);
            }
        });
        binding.cardValidate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ValidateActivity.class);
                startActivity(intent);
            }
        });

        List<DocumentModel> Doclist = new ArrayList<>();
        Doclist.add(new DocumentModel("2023-04-14", R.drawable.document));
        Doclist.add(new DocumentModel("2023-04-16", R.drawable.user));
        Doclist.add(new DocumentModel("2023-03-15", R.drawable.group));
        Doclist.add(new DocumentModel("2023-01-04", R.drawable.user));
        Doclist.add(new DocumentModel("2023-05-02", R.drawable.portfolio));
        Doclist.add(new DocumentModel("2023-03-11", R.drawable.dashboard));

        //Upgrade plan
        binding.upgrade2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SettingActivity.class);
                intent.putExtra("tabIndex", 1);
                startActivity(intent);


            }
        });


        mDoc = new DocumentAdapter(Doclist, emptyStateImage, emptyStateTitle, emptyStateSubtitle);
        binding.rcvDoc.setAdapter(mDoc);
        if (Doclist.isEmpty()) { // Kiểm tra nếu RecyclerView không có dữ liệu để hiển thị
            binding.rcvDoc.setVisibility(View.GONE); // Ẩn RecyclerView
            if (mEmptyState == null) { // Nếu empty layout chưa được khởi tạo
                mEmptyState = (ViewGroup) LayoutInflater.from(getContext()).inflate(R.layout.empty_layout, container, false);
            }
            ((ViewGroup) binding.rcvDoc.getParent()).addView(mEmptyState); // Thêm empty layout vào ViewGroup của RecyclerView
        } else {
            binding.rcvDoc.setVisibility(View.VISIBLE); // Hiển thị RecyclerView
            if (mEmptyState != null) { // Nếu empty layout đã được hiển thị trước đó
                ((ViewGroup) binding.rcvDoc.getParent()).removeView(mEmptyState); // Xóa empty layout khỏi ViewGroup của RecyclerView
            }
        }

        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

//    public void addData(DocumentModel data) {
//        if (data != null) {
//            mListDoc.add(data);
//        } else {
//            throw new IllegalArgumentException("Data must be an instance of DocumentModel");
//        }
//    }
}