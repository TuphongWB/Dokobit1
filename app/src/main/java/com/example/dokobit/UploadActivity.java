package com.example.dokobit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dokobit.Adapter.ViewPager2Adapter;
import com.example.dokobit.databinding.ActivityUploadBinding;

import me.relex.circleindicator.CircleIndicator3;


public class UploadActivity extends AppCompatActivity {
    private ActivityUploadBinding binding;
    private ViewPager2Adapter viewPager2Adapter;
    private CircleIndicator3 indicator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUploadBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());



        //Back stack
        Toolbar toolbar = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //ViewPager2
        viewPager2Adapter = new ViewPager2Adapter(getSupportFragmentManager(), getLifecycle());

        binding.fragmentContainer.setAdapter(viewPager2Adapter);
        //CircleIndicator
        indicator = findViewById(R.id.circle);
        indicator.setViewPager(binding.fragmentContainer);

        binding.fragmentContainer.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position == 2){
                    binding.layoutNext.setVisibility(View.GONE);
                }else {
                    binding.layoutNext.setVisibility(View.VISIBLE);
                }
            }
        });

        binding.layoutNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.fragmentContainer.getCurrentItem() < 2){
                    binding.fragmentContainer.setCurrentItem(binding.fragmentContainer.getCurrentItem() + 1);
                }
            }
        });
        binding.layoutBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (binding.fragmentContainer.getCurrentItem() > 0){
                    binding.fragmentContainer.setCurrentItem(binding.fragmentContainer.getCurrentItem() - 1);
                }
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // Quay lại HomeActivity
            Intent intent = new Intent(this, HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
