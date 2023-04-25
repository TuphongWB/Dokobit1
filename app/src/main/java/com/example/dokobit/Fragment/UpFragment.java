package com.example.dokobit.Fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.dokobit.R;
import com.example.dokobit.databinding.FragmentUpBinding;

public class UpFragment extends Fragment {

    private FragmentUpBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentUpBinding.inflate(inflater);

        binding.addusing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAlertDialog();
            }
        });
        binding.participants.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddParticipantsDialogFragment dialog = new AddParticipantsDialogFragment();
                dialog.show(getParentFragmentManager(), "add_participants_dialog");
            }
        });
        return binding.getRoot();
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_addusing, null);
        builder.setView(view);



        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dialog.cancel();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
    private void showDialogFragment() {
        AddParticipantsDialogFragment dialogFragment = new AddParticipantsDialogFragment();
        dialogFragment.show(getChildFragmentManager(), "AddParticipantsDialogFragment");
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}