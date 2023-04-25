package com.example.dokobit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;
import androidx.viewpager2.widget.ViewPager2;

import com.example.dokobit.Adapter.TabViewPagerAdapter;
import com.example.dokobit.databinding.ActivitySettingBinding;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        com.example.dokobit.databinding.ActivitySettingBinding binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        TabViewPagerAdapter tabViewPagerAdapter = new TabViewPagerAdapter(this);
        //Toolbar
        initToolbar();
        //Color
        setTabLayAnimation();
        //ViewPager
        ViewPager2 viewPager = binding.viewPager;
        viewPager.setAdapter(tabViewPagerAdapter);
        int tabIndex = getIntent().getIntExtra("tabIndex", 0);
        viewPager.setCurrentItem(tabIndex);
        //TabLayout
        TabLayout tabs = binding.tabMode;
        new TabLayoutMediator(tabs, viewPager, (tab, position) -> {
            switch (position){
                case 0:
                    tab.setText("Profile");
                    break;

                case 1:
                    tab.setText("Billing");
                    break;
            }
        }).attach();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void initToolbar(){
        Toolbar toolbar = findViewById(R.id.toolbartab);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    private void setTabLayAnimation(){
        final CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.CollapsingToolbarLayout);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.header);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(@Nullable Palette palette) {
                int myColor = palette.getVibrantColor(getResources().getColor(R.color.colorAccent));
                int myDarkColor = palette.getVibrantColor(getResources().getColor(R.color.transparent));
                collapsingToolbarLayout.setContentScrimColor(myColor);
                collapsingToolbarLayout.setStatusBarScrimColor(myDarkColor);
            }
        });
    }
}