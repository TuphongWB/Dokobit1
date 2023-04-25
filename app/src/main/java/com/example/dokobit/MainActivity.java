package com.example.dokobit;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dokobit.Adapter.NameAdapter;
import com.example.dokobit.Model.Country;
import com.example.dokobit.Model.Name;
import com.example.dokobit.Myinterface.IClickCountry;
import com.example.dokobit.Myinterface.IClickItemNameListener;
import com.example.dokobit.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private List<Name> mListname;
    private IClickItemNameListener iClickItemNameListener;


    private com.example.dokobit.Adapter.NameAdapter nameAdapter;
    private List<Country> listCountry;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
//        nameAdapter = new com.example.dokobit.Adapter.NameAdapter(mListname, iClickItemNameListener);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        binding.rcvName.setLayoutManager(linearLayoutManager);
        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        ((DividerItemDecoration) itemDecoration).setDrawable(getResources().getDrawable(R.drawable.shape, null));
        binding.rcvName.addItemDecoration(itemDecoration);

        nameAdapter = new NameAdapter(getListName(), new IClickItemNameListener() {
            @Override
            public void onClickItemName(Name Name) {
                onClickGotoLogin(Name);
            }
        });
        nameAdapter.setData(getListName());
        binding.rcvName.setAdapter(nameAdapter);

        //Fragment

        //Button
        CardView cardView = findViewById(R.id.cardView2);
        listCountry = new ArrayList<>();
        cardView.setOnClickListener(v -> clickOpenSelectCountryFragment());
        Button signup = (Button) findViewById(R.id.btn_signup);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });
    }
    private boolean isDialogShowing = false;
    private void clickOpenSelectCountryFragment() {
        List<Country> list = new ArrayList<>();
        list.add(new Country(R.drawable.list, "All supported countries eIDs"));
        list.add(new Country(R.drawable.lithuania, "Lithuania"));
        list.add(new Country(R.drawable.latvia, "Latvia"));
        list.add(new Country(R.drawable.estonia, "Estonia"));
        list.add(new Country(R.drawable.finland, "Finland"));
        list.add(new Country(R.drawable.norway, "Norway"));
        list.add(new Country(R.drawable.sweden, "Sweden"));
        list.add(new Country(R.drawable.belgium, "Belgium"));
        list.add(new Country(R.drawable.iceland, "Iceland"));
        list.add(new Country(R.drawable.denmark, "Denmark"));

        SelectCountryFragment selectCountryFragment = new SelectCountryFragment(list, new IClickCountry() {
            @Override
            public void clickItem(Country country) {
                TextView textView = findViewById(R.id.tv_Country);
                textView.setText(country.getCountry());
                ImageView img = findViewById(R.id.img_country);
                img.setImageResource(country.getImage());

                if (country.getImage() == R.drawable.list) {
                    // Quay lại danh sách ban đầu
                    nameAdapter.setData(getListName());
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.lithuania) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(0));
                    list1.add(getListName().get(3));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.latvia) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(3));
                    list1.add(getListName().get(4));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.estonia) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(1));
                    list1.add(getListName().get(3));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();

                } else if (country.getImage() == R.drawable.finland) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(10));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.norway) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(5));
                    list1.add(getListName().get(6));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();

                } else if (country.getImage() == R.drawable.sweden) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(7));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.belgium) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(9));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();
                } else if (country.getImage() == R.drawable.iceland) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(2));
                    list1.add(getListName().get(8));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();

                } else if (country.getImage() == R.drawable.denmark) {
                    List<Name> list1 = new ArrayList<>();
                    list1.add(getListName().get(11));
                    list1.add(getListName().get(12));
                    nameAdapter.setData(list1);
                    nameAdapter.notifyDataSetChanged();

                }


                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast, (ViewGroup) findViewById(R.id.custom_toast_layout));
                TextView text = (TextView) layout.findViewById(R.id.txt_message);
                ImageView imgFlag = (ImageView) layout.findViewById(R.id.img_flag);
                imgFlag.setImageResource(country.getImage());
                text.setText(country.getCountry());

                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                toast.setView(layout);
                toast.show();

                }
        });



        selectCountryFragment.show(getSupportFragmentManager(), selectCountryFragment.getTag());



    }


    private List<Name> getListName() {
        List<Name> list = new ArrayList<>();
        list.add(new Name(R.drawable.logo1, "Mobile ID"));
        list.add(new Name(R.drawable.logo2, "Mobile ID"));
        list.add(new Name(R.drawable.is, "Mobile ID"));
        list.add(new Name(R.drawable.logo3, "Smart ID"));
        list.add(new Name(R.drawable.logo4, "eParaksts mobile"));
        list.add(new Name(R.drawable.bankidd, "Norwegian BankID"));
        list.add(new Name(R.drawable.bankid, "Norwegian BankID on mobile"));
        list.add(new Name(R.drawable.bankid2, "Swedish BankID"));
        list.add(new Name(R.drawable.au, "Audkenni"));
        list.add(new Name(R.drawable.itsme, "itsme"));
        list.add(new Name(R.drawable.estonia, "Finish Trust Network (FTN)"));
        list.add(new Name(R.drawable.mitid, "MitID"));
        list.add(new Name(R.drawable.nemid, "NemID"));

        return list;
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
    }
    private void onClickGotoLogin(Name name){
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_name", name);
        intent.putExtras(bundle);
        startActivity(intent);
    }
}
