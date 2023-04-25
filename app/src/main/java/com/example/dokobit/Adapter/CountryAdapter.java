package com.example.dokobit.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Model.Country;
import com.example.dokobit.Myinterface.IClickCountry;
import com.example.dokobit.R;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder>{

    private Context c;
    private List<Country> mListCountry;
    private IClickCountry iClickCountry;

    public CountryAdapter(Context c, List<Country> mListCountry, IClickCountry iClickCountry) {
        this.c = c;
        this.mListCountry = mListCountry;
        this.iClickCountry = iClickCountry;
    }
    public void setFilteredList(List<Country> filteredList){
        this.mListCountry = filteredList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_country, parent, false);
        return new CountryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryViewHolder holder, int position) {
        Country country = mListCountry.get(position);
        if (country == null) {
            return;
        }
        holder.imgCountry.setImageResource(country.getImage());
        holder.tvCountry.setText(country.getCountry());
        holder.tvCountry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iClickCountry.clickItem(country) ;
            }
        });
    }
    

    @Override
    public int getItemCount() {
        if (mListCountry != null){
            return mListCountry.size();
        }
        return 0;
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgCountry;
        private TextView tvCountry;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            imgCountry = itemView.findViewById(R.id.img_country);
            tvCountry = itemView.findViewById(R.id.tv_Country);
        }
    }

}
