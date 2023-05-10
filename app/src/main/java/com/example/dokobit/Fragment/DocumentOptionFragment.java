package com.example.dokobit.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.dokobit.Dialog.AnnotationDialog;
import com.example.dokobit.Model.FileNameData;
import com.example.dokobit.Myinterface.OnFileNameSelectedListener;
import com.example.dokobit.R;
import com.example.dokobit.databinding.FragmentDocumentOptionBinding;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Calendar;
import java.util.Locale;


public class DocumentOptionFragment extends Fragment implements AnnotationDialog.AnnotationDialogListener {
    private FragmentDocumentOptionBinding binding;
    private TextView tvSelectedOption;

    private TextInputEditText mFileName;
    private FileNameData fileNameData;

    private Calendar calendar = Calendar.getInstance();
    private OnFileNameSelectedListener mListener ;







    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDocumentOptionBinding.inflate(inflater);
        mFileName = binding.getRoot().findViewById(R.id.edit_filename);
        fileNameData = FileNameData.getInstance();
        String fileName = fileNameData.getFileName();
        tvSelectedOption = binding.getRoot().findViewById(R.id.option);
        mFileName.setText(fileName);

        binding.getRoot().findViewById(R.id.annotation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogFragment();
            }
        });



        binding.getRoot().findViewById(R.id.tv_date).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });
        binding.getRoot().findViewById(R.id.tv_time).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog();
            }
        });


        return binding.getRoot();
    }
    private void showTimePickerDialog() {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(requireActivity(), new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                // Lưu giờ và phút được chọn
                calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendar.set(Calendar.MINUTE, minute);

                // Hiển thị giờ và phút đã chọn trong TextView
                binding.tvTime.setText(String.format(Locale.getDefault(), "%02d:%02d", hourOfDay, minute));
            }
        }, hour, minute, true);

        timePickerDialog.show();
    }
    private void showDatePickerDialog() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                // Lưu ngày, tháng và năm được chọn
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                // Hiển thị ngày, tháng và năm đã chọn trong TextView
                ((TextView)binding.getRoot().findViewById(R.id.tv_date)).setText(String.format(Locale.getDefault(), "%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year));
            }
        }, year, month, dayOfMonth);

        datePickerDialog.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        // Lấy instance của FileNameData
        FileNameData fileNameData = FileNameData.getInstance();
        // Lấy tên file hiện tại
        String currentFileName = fileNameData.getFileName();
        // Kiểm tra xem tên file có thay đổi hay không
        if (currentFileName != null && !currentFileName.isEmpty() && !currentFileName.equals(mFileName.getText().toString())) {
            // Cập nhật giao diện với tên file mới
            mFileName.setText(currentFileName);
        }
    }

    private void showDialogFragment() {
        AnnotationDialog dialog = new AnnotationDialog(requireContext(), this);
        dialog.show();

    }

    @Override
    public void onOptionSelected(String selectedOption) {
        tvSelectedOption.setText(selectedOption);
    }
}