package com.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.android.activity.LibraryActivity;
import com.android.adapter.AlbumAdapter;
import com.android.adapter.MainViewPagerAdapter;
import com.android.mainapp.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class FragmentLibrary extends Fragment {
    View view;
    ViewPager viewPager;
    private FragmentActivity myContext;
    Context context;
    Button button;
    @Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;

        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        context=container.getContext();
        view =inflater.inflate(R.layout.fragment_library,container,false);
        viewPager=view.findViewById(R.id.viewpagerLibrary);
        button=view.findViewById(R.id.buttonPlayLocal);

        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(myContext.getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentLocalMusic(),"Local");
        mainViewPagerAdapter.addFragment(new FragmentYourPlaylist(),"Your Playlist");

        viewPager.setAdapter(mainViewPagerAdapter);
        TabLayout tabLayout = view.findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition()==0){
//                    Intent intent=new Intent(context, LibraryActivity.class);
//                    context.startActivity(intent);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return view;
    }

}
