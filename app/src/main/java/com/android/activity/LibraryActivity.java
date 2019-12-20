package com.android.activity;

import android.os.Bundle;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.mainapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class LibraryActivity extends AppCompatActivity {


//    Toolbar toolbar = findViewById(R.id.toolbarLibrary);
//    TabLayout tabLayout = findViewById(R.id.tablayout);
//    ViewPager viewPager = findViewById(R.id.viewpagerLibrary);
//    PagerAdapter pageAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_your_playlist);
    }
}
