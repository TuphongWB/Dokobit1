package com.example.dokobit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Model.File;
import com.example.dokobit.Myinterface.IClickFile;
import com.example.dokobit.R;

import java.util.List;

public class FileApdater extends RecyclerView.Adapter<FileApdater.FileViewHolder> {
    private List<File> mListFile;
    private IClickFile iClickFile;

    public FileApdater(Context context, List<File> mListFile, IClickFile iClickFile) {
        this.mListFile = mListFile;
        this.iClickFile = iClickFile;
    }

    @NonNull
    @Override
    public FileApdater.FileViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_file, parent, false);
        return new FileViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileApdater.FileViewHolder holder, int position) {
        File file = mListFile.get(position);
        if(file == null) {
            return;
        }
        holder.imgFile.setImageResource(file.getImage());
        holder.tvFile.setText(file.getName());
        holder.tvFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickFile.clickItem(file);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListFile != null){
            return mListFile.size();
        }
        return 0;
    }

    public class FileViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgFile;
        private TextView tvFile;
        public FileViewHolder(@NonNull View itemview) {
            super(itemview);
            imgFile = itemview.findViewById(R.id.img_file);
            tvFile = itemview.findViewById(R.id.tv_file);

        }
    }
}
