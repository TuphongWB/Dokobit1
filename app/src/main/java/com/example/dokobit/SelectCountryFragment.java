package com.example.dokobit;

import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Adapter.CountryAdapter;
import com.example.dokobit.Model.Country;
import com.example.dokobit.Myinterface.IClickCountry;

import java.util.ArrayList;
import java.util.List;

public class SelectCountryFragment extends DialogFragment {
    private final List<Country> mListCountry;
    private final IClickCountry iClickCountry;
//    RecyclerView rcvCountry;
//    CountryAdapter countryAdapter;

    public SelectCountryFragment(List<Country> mListCountry, IClickCountry iClickCountry) {
        this.mListCountry = mListCountry;
        this.iClickCountry = iClickCountry;
//        this.rcvCountry = rcvCountry;
//        this.countryAdapter = countryAdapter;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        Dialog selectCountryFragment = super.onCreateDialog(savedInstanceState);

        View view = LayoutInflater.from(getContext()).inflate(R.layout.layout_dialog_select, null);
        selectCountryFragment.setContentView(view);
        RecyclerView rcvCountry = view.findViewById(R.id.rcv_country);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        rcvCountry.setLayoutManager(linearLayoutManager);

        CountryAdapter countryAdapter = new CountryAdapter(getContext(), mListCountry, new IClickCountry() {
            @Override
            public void clickItem(Country country) {
                iClickCountry.clickItem(country);
                dismiss();
            }
        });
        rcvCountry.setAdapter(countryAdapter);
        SearchView searchView = view.findViewById(R.id.srView);
        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                countryAdapter.setFilteredList(filterList(newText));
                return false;
            }
        });
        selectCountryFragment.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return selectCountryFragment;
    }

    private List<Country> filterList(String newText) {
        List<Country> filteredList = new ArrayList<>();
        CountryAdapter countryAdapter = new CountryAdapter(getContext(), mListCountry, new IClickCountry() {
            @Override
            public void clickItem(Country country) {
                iClickCountry.clickItem(country);
            }
        });
        for (Country country : mListCountry){
            if(country.getCountry().toLowerCase().contains(newText.toLowerCase())){
                filteredList.add(country);
            }
        }
        if (filteredList.isEmpty()){
            Toast.makeText(getContext(), "No data found", Toast.LENGTH_SHORT).show();

        }else {
            countryAdapter.setFilteredList(filteredList);

        }
        return filteredList;
    }


//
//    private List<Country> getListCountry() {
//        List<Country> list = new ArrayList<>();
//        list.add(new Country(R.drawable.list, "All supported countries eIDs"));
//        list.add(new Country(R.drawable.lithuania, "Lithuania"));
//        list.add(new Country(R.drawable.latvia, "Latvia"));
//        list.add(new Country(R.drawable.estonia, "Estonia"));
//        list.add(new Country(R.drawable.finland, "Finland"));
//        list.add(new Country(R.drawable.norway, "Norway"));
//        list.add(new Country(R.drawable.sweden, "Sweden"));
//        list.add(new Country(R.drawable.belgium, "Belgium"));
//        list.add(new Country(R.drawable.iceland, "Iceland"));
//        list.add(new Country(R.drawable.denmark, "Denmark"));
//
//        return list;
//    }

}
