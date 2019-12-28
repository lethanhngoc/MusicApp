package com.android.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.adapter.MainViewPagerAdapter;
import com.android.fragment.FragmentHome;

import com.android.fragment.FragmentLibrary;
import com.android.fragment.FragmentSearch;
import com.android.mainapp.R;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ArrayList<String> arrayList;
    ListView listView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] requiredPermissions = {
                Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.WRITE_EXTERNAL_STORAGE
        };
        ActivityCompat.requestPermissions(this, requiredPermissions, 0);

        setContentView(R.layout.activity_main);
        mapping();
        init();
    }

    public void getMusic(){
        ContentResolver contentResolver=getContentResolver();
        Uri songUri= MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor songCursor=contentResolver.query(songUri,null,null,null,null);
        if(songCursor!=null&&songCursor.moveToFirst()){
            int songTitle=songCursor.getColumnIndex(MediaStore.Audio.Media.TITLE);
            int songArtist=songCursor.getColumnIndex(MediaStore.Audio.Media.ARTIST);
            int songLocation=songCursor.getColumnIndex(MediaStore.Audio.Media.DATA);
            do {
               String currentTitle=songCursor.getString(songTitle);
               String currentArtist=songCursor.getString(songArtist);
                String currentLocation=songCursor.getString(songLocation );
               arrayList.add("Title: "+ currentTitle+"\n"
                       + "Artist: "+currentArtist+"\n"
                       + "Location: "+currentLocation);
            }while(songCursor.moveToNext());
        }
    }

    public void init(){
        MainViewPagerAdapter mainViewPagerAdapter=new MainViewPagerAdapter(getSupportFragmentManager());
        mainViewPagerAdapter.addFragment(new FragmentHome(),"Home");
        mainViewPagerAdapter.addFragment(new FragmentLibrary(),"Your Library");
        mainViewPagerAdapter.addFragment(new FragmentSearch(),"Search");
        viewPager.setAdapter(mainViewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.trangchu);
        tabLayout.getTabAt(1).setIcon(R.drawable.local);
        tabLayout.getTabAt(2).setIcon(R.drawable.search);
    }

    public void mapping(){
        tabLayout=findViewById(R.id.myTabLayout);
        viewPager =findViewById(R.id.myViewPager);
    }


}
