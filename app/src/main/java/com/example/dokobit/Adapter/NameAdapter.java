package com.example.dokobit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Model.Name;
import com.example.dokobit.Myinterface.IClickItemNameListener;
import com.example.dokobit.R;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.NameViewHolder> {

    private Context mContext;
    private List<Name> mListName;

    private IClickItemNameListener iClickItemNameListener;

    public NameAdapter(List<Name> mListName, IClickItemNameListener iClickItemNameListener) {
        this.mListName = mListName;
        this.iClickItemNameListener = iClickItemNameListener;
    }


    public void setData(List<Name> list){
        this.mListName = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_logo, parent, false);
        return new NameViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NameViewHolder holder, int position) {
        Name name = mListName.get(position);
        if (name == null){
            return;
        }
        holder.imgName.setImageResource(name.getResourceId());
        holder.tvName.setText(name.getLogo());
        holder.tvName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickItemNameListener.onClickItemName(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListName != null){
            return mListName.size();
        }
        return 0;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        private ImageView imgName;
        private TextView tvName;
        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
            imgName = itemView.findViewById(R.id.jpg_logo);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
