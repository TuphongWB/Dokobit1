package com.example.dokobit.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Model.DocumentModel;
import com.example.dokobit.R;

import java.util.List;
public class DocumentAdapter extends RecyclerView.Adapter<DocumentAdapter.DocumentViewHolder> {

    private List<DocumentModel> mDocList;
    private int mEmptyStateImage;
    private String mEmptyStateTitle;
    private String mEmptyStateSubtitle;

    public DocumentAdapter(List<DocumentModel> docList, int emptyStateImage, String emptyStateTitle, String emptyStateSubtitle) {
        mDocList = docList;
        mEmptyStateImage = emptyStateImage;
        mEmptyStateTitle = emptyStateTitle;
        mEmptyStateSubtitle = emptyStateSubtitle;
    }

    @NonNull
    @Override
    public DocumentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false);
        return new DocumentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DocumentViewHolder holder, int position) {
        if (mDocList.isEmpty()) {
            holder.mEmptyStateImage.setImageResource(mEmptyStateImage);
            holder.mEmptyStateTitle.setText(mEmptyStateTitle);
            holder.mEmptyStateSubtitle.setText(mEmptyStateSubtitle);
        } else {
            DocumentModel currentDoc = mDocList.get(position);
            holder.mDocTitle.setText(currentDoc.getDocumentName());
            holder.mDocImage.setImageResource(currentDoc.getImgDoc());
        }
    }

    @Override
    public int getItemCount() {
        if (mDocList.isEmpty()) {
            return 1;
        } else {
            return mDocList.size();
        }
    }

    public static class DocumentViewHolder extends RecyclerView.ViewHolder {

        public TextView mDocTitle;
        public ImageView mDocImage;
        public ImageView mEmptyStateImage;
        public TextView mEmptyStateTitle;
        public TextView mEmptyStateSubtitle;

        public DocumentViewHolder(@NonNull View itemView) {
            super(itemView);

            mDocTitle = itemView.findViewById(R.id.tv_doc);
            mDocImage = itemView.findViewById(R.id.imgdoc);
            mEmptyStateImage = itemView.findViewById(R.id.empty_state_image);
            mEmptyStateTitle = itemView.findViewById(R.id.empty_state_title);
            mEmptyStateSubtitle = itemView.findViewById(R.id.empty_state_description);
        }
    }
}



