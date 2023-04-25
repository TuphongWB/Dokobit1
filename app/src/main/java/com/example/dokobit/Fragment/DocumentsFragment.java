package com.example.dokobit.Fragment;

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
import com.example.dokobit.databinding.FragmentDocumentsBinding;

import java.util.ArrayList;
import java.util.List;

public class DocumentsFragment extends Fragment {

    private FragmentDocumentsBinding binding;
    private ViewGroup emptyLayout;

    private List<DocumentModel> mListDoc;
    private View mEmptyState;
    private DocumentAdapter mDoc;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDocumentsBinding.inflate(inflater, container, false);

        emptyLayout = binding.getRoot();
        binding.rcvPagedoc.setLayoutManager(new LinearLayoutManager(getContext()));
        int emptyStateImage = R.drawable.ic_empty;
        String emptyStateTitle = getString(R.string.empty_state_title);
        String emptyStateSubtitle = getString(R.string.empty_state_subtitle);

        List<DocumentModel> Doclist = new ArrayList<>();
//        Doclist.add(new DocumentModel("Title 1", R.drawable.document));
//        Doclist.add(new DocumentModel("Title 2", R.drawable.dashboard));
//        Doclist.add(new DocumentModel("Title 3", R.drawable.dashboard));
//        Doclist.add(new DocumentModel("Title 4", R.drawable.dashboard));
//        Doclist.add(new DocumentModel("Title 5", R.drawable.dashboard));
//        Doclist.add(new DocumentModel("Title 6", R.drawable.dashboard));


        mDoc = new DocumentAdapter(Doclist, emptyStateImage, emptyStateTitle, emptyStateSubtitle);
        binding.rcvPagedoc.setAdapter(mDoc);
        if (Doclist.isEmpty()) { // Kiểm tra nếu RecyclerView không có dữ liệu để hiển thị
            binding.rcvPagedoc.setVisibility(View.GONE); // Ẩn RecyclerView
            emptyLayout.setVisibility(View.VISIBLE);
        } else {
            binding.rcvPagedoc.setVisibility(View.VISIBLE); // Hiển thị RecyclerView
            emptyLayout.setVisibility(View.GONE);
        }

        return binding.getRoot();
    }
}

