package com.example.dokobit.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.example.dokobit.R;

public class AnnotationDialog extends Dialog {
    private AnnotationDialogListener dialogListener;

    public AnnotationDialog(Context context, AnnotationDialogListener listener) {
        super(context);
        this.dialogListener = listener;
    }
    public interface AnnotationDialogListener {
        void onOptionSelected(String selectedOption);
    }
    private void onSaveClicked() {
        // Lấy option được chọn
        RadioGroup radioGroup = findViewById(R.id.radioGroup);
        int checkedRadioButtonId = radioGroup.getCheckedRadioButtonId();
        String selectedOption = "";

        switch (checkedRadioButtonId) {
            case R.id.radioButton1:
                selectedOption = "Frist page - top";
                break;
            case R.id.radioButton2:
                selectedOption = "Frist page - bottom";
                break;
            case R.id.radioButton3:
                selectedOption = "Last page - top";
                break;
            case R.id.radioButton4:
                selectedOption = "Last page - bottom";
                break;
            case R.id.radioButton5:
                selectedOption = "Do not use annotations";
                break;
        }

        // Gọi callback và truyền option được chọn về Fragment
        if (dialogListener != null) {
            dialogListener.onOptionSelected(selectedOption);
        }

        dismiss();
    }
    public AnnotationDialog(Context context) {
        super(context);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_annotation);


        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        layoutParams.copyFrom(getWindow().getAttributes());
        int width = (int) (getContext().getResources().getDisplayMetrics().widthPixels * 0.9); // Đặt chiều rộng là 90% chiều rộng màn hình
        layoutParams.width = width;
        getWindow().setAttributes(layoutParams);
        Button btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSaveClicked();
            }
        });
    }
}
