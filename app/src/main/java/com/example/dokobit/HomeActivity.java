package com.example.dokobit;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.dokobit.Fragment.DashboardFragment;
import com.example.dokobit.Fragment.DocumentsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.Objects;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {



    private static final int FRAGMENT_DASHBOARD = 0;
    private static final int FRAGMENT_DOCUMENTS = 1;
    private static final int FRAGMENT_UPLOAD = 2;
    private static final int FRAGMENT_VALIDATE = 3;

    private int mCurrentFragment = FRAGMENT_DASHBOARD;

    private NavigationView mNavigationView;
    private BottomNavigationView mBottomNavigationView;
    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
                startActivity(intent);
            }
        });

    //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayShowTitleEnabled(false);
    //Set DrawerLayout
        mDrawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar,
                R.string.nav_drawer_open, R.string.nav_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    //Navigation
        mNavigationView = findViewById(R.id.navigation_view);
        mNavigationView.setNavigationItemSelectedListener(this);

        //BottomNavigation
        mBottomNavigationView = findViewById(R.id.bottomNavigationView);
        mBottomNavigationView.setOnItemSelectedListener((BottomNavigationView.OnNavigationItemSelectedListener) item -> {
            switch (item.getItemId()) {
                case R.id.bot_dasboard:
                    openDashboardFragment();
                    mNavigationView.setCheckedItem(R.id.nav_dasboard);
                    break;
                case R.id.bot_document:
                    openDocumentFragment();
                    mNavigationView.setCheckedItem(R.id.nav_document);
                    break;
            }
            return false;
            });
    // Set Dash board
        replaceFragment(new DashboardFragment());
        mNavigationView.setCheckedItem(R.id.nav_dasboard);
        mBottomNavigationView.getMenu().findItem(R.id.bot_dasboard).setChecked(true);
//        setTitleToolbar();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_dasboard){
            openDashboardFragment();
            mBottomNavigationView.getMenu().findItem(R.id.bot_dasboard).setChecked(true);
        } else if (id == R.id.nav_document){
            openDocumentFragment();
            mBottomNavigationView.getMenu().findItem(R.id.bot_document).setChecked(true);
        }else if (id == R.id.nav_upload){
            openUploadFragment();
        }else if (id == R.id.nav_validate){
            openValidateFragment();
        }else if (id == R.id.nav_setting) {
            openSettingActivity();
        }
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void openDashboardFragment() {
        if (mCurrentFragment != FRAGMENT_DASHBOARD) {
            replaceFragment(new DashboardFragment());
            mCurrentFragment = FRAGMENT_DASHBOARD;
        }
    }
    private void openDocumentFragment() {
        if (mCurrentFragment != FRAGMENT_DOCUMENTS) {
            replaceFragment(new DocumentsFragment());
            mCurrentFragment = FRAGMENT_DOCUMENTS;

        }
    }
    private void openUploadFragment() {
        Intent intent = new Intent(HomeActivity.this, UploadActivity.class);
        startActivity(intent);
    }
    private void openValidateFragment() {
        Intent intent = new Intent(HomeActivity.this, ValidateActivity.class);
        startActivity(intent);
    }
    private void openSettingActivity(){
        Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        }else if (mCurrentFragment != FRAGMENT_DASHBOARD) {
            openDashboardFragment();
            mNavigationView.setCheckedItem(R.id.nav_dasboard);
            mBottomNavigationView.getMenu().findItem(R.id.bot_dasboard).setChecked(true);
        }else {
            super.onBackPressed();
        }
    }
    private void replaceFragment(Fragment fragment){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.content_frame, fragment);
        transaction.commit();
    }

}