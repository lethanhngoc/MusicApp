package com.android.activity;

import android.os.Bundle;

import com.android.adapter.MainViewPagerAdapter;
import com.android.fragment.FragmentHome;
import com.android.fragment.FragmentSdCard;
import com.android.fragment.FragmentSearch;
import com.android.mainapp.R;

import com.google.android.material.tabs.TabLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;



public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    public void init(){
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentHome(),"Home");
        mainViewPagerAdapter.addFragment(new FragmentSearch(),"Search");
        mainViewPagerAdapter.addFragment(new FragmentSdCard(),"SdCard");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.trangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.timkiem);
        tabLayout.getTabAt(2).setIcon(R.drawable.sdcard);
    }

    public void mapping(){
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager =findViewById(R.id.myViewPager);
    }
}
