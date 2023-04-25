package com.example.dokobit.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private List<String> dataList;

    public MyAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String data = dataList.get(position);
        holder.textView.setText(data);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;
        public SwitchCompat switch1;
        public SwitchCompat switch2;

        public MyViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.tv1);
            switch1 = itemView.findViewById(R.id.switch1);
            switch2 = itemView.findViewById(R.id.switch2);
        }
    }
}