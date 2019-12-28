package com.android.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.android.activity.ListSongsActivity;
import com.android.activity.PlaySongActivity;
import com.android.adapter.LocalMusicAdapter;
import com.android.adapter.PlaySongsAdapter;
import com.android.mainapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.File;
import java.util.ArrayList;

public class FragmentLocalMusic extends Fragment {

    View view;
    ListView listViewLocalMusic;
    ImageView coverart;
    private String songNames[];
    RecyclerView recyclerViewListLocalSongs;
    RecyclerView recyclerViewplaynhac;
    PlaySongsAdapter playSongsAdapter;
    AppCompatActivity appCompatActivity;
    Button button;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Override
    public void onAttach(Activity activity) {
        appCompatActivity= (AppCompatActivity) activity;
        super.onAttach(activity);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view= inflater.inflate(R.layout.fragment_local_music, container, false);
        button=view.findViewById(R.id.buttonPlayLocal);

        recyclerViewListLocalSongs=view.findViewById(R.id.recyclerviewLocalSong);
        ArrayList<File> songs=readSongs(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        songNames=new String[songs.size()];
        for (int i=0;i<songs.size();i++){
            songNames[i]= songs.get(i).getName().replace(".mp3","");
        }

        LocalMusicAdapter localMusicAdapter=new LocalMusicAdapter(this.getContext(),songs,button);
        recyclerViewListLocalSongs.setLayoutManager(new LinearLayoutManager(appCompatActivity.getApplicationContext()));
        recyclerViewListLocalSongs.setAdapter(localMusicAdapter);
        return view;
    }

    private ArrayList<File> readSongs(File root){


        ArrayList<File> arrayList=new ArrayList<>();
        File files[] =root.listFiles();
        for(File file:files){
            if(file.isDirectory()){
                arrayList.addAll(readSongs(file));
            }else{
                if(file.getName().endsWith(".mp3")){
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
