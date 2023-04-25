package com.example.dokobit.Fragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.fragment.app.Fragment;

import com.example.dokobit.R;
import com.example.dokobit.databinding.FragmentDocumentOptionBinding;

import java.util.Calendar;
import java.util.Locale;


public class DocumentOptionFragment extends Fragment {
    private FragmentDocumentOptionBinding binding;
    private Calendar calendar = Calendar.getInstance();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentDocumentOptionBinding.inflate(inflater);

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
}