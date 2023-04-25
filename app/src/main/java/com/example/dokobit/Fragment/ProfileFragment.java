package com.example.dokobit.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dokobit.Adapter.MyAdapter;
import com.example.dokobit.Dialog.AddAliasDialog;
import com.example.dokobit.R;
import com.example.dokobit.databinding.FragmentProfileBinding;

import java.util.ArrayList;
import java.util.List;


public class ProfileFragment extends Fragment {
    private FragmentProfileBinding binding;
    private AddAliasDialog mAddAliasDialog;
    private MyAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        com.example.dokobit.databinding.FragmentProfileBinding binding = FragmentProfileBinding.inflate(inflater, container, false);


        List<String> dataList = new ArrayList<>();
        dataList.add("A document needs my signature/approval");
        dataList.add("New comment is added\n");
        dataList.add("A document signing/approving deadline is\n" +
                "approaching");
        dataList.add("Someone signs/approves my document");
        dataList.add("I'm invited as a viewert");
        dataList.add("All parties signed/approved the document");

        mAddAliasDialog = new AddAliasDialog();


        binding.btnAddAlias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAddAliasDialog.show(getParentFragmentManager(), "add_alias_dialog");
            }
        });


        MyAdapter adapter = new MyAdapter(dataList);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        binding.recyclerView.setAdapter(adapter);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL);
        itemDecoration.setDrawable(getResources().getDrawable(R.drawable.shape, null));
        binding.recyclerView.addItemDecoration(itemDecoration);
        return binding.getRoot();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}