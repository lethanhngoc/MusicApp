package com.android.fragment;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.adapter.LocalAdapter;
import com.android.adapter.PlaySongsAdapter;
import com.android.mainapp.R;
import com.android.model.LocalSong;
import com.android.model.Song;

import java.io.File;
import java.util.ArrayList;

public class FragmentSdCard extends Fragment {

    View view;
    ArrayList<File> songs;
    ArrayList<LocalSong> localSongs = new ArrayList<>();
    LocalAdapter localAdapter;
    private String songNames[];
    RecyclerView recyclerView;

    ImageButton button;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sdcard,container,false);
        recyclerView = view.findViewById(R.id.recyclerviewlocalsong);
        button = view.findViewById(R.id.ButtonSDCard);

        localSongs.clear();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String[] requiredPermissions = { Manifest.permission.READ_EXTERNAL_STORAGE };
        ActivityCompat.requestPermissions(getActivity(), requiredPermissions, 0);

//        System.out.println("env: "+ Environment.getExternalStoragePublicDirectory("Download").listFiles());

        songs = readSongs(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));

        songNames = new String[songs.size()];

        for(int i = 0 ; i< songs.size() ; i++){

            songNames[i] = songs.get(i).getName().replace(".mp3","");
            LocalSong a = new LocalSong(i+"",songNames[i],songs.get(i).getPath());
            localSongs.add(a);
        }

        localAdapter = new LocalAdapter(getActivity(),localSongs);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(localAdapter);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PlaySongActivity.class);
                intent.putExtra("cacbaihatlocal",localSongs);
                startActivity(intent);
            }
        });
    }
    private ArrayList<File> readSongs(File root){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = root.listFiles();
        for(File file : files){
            if(file.isDirectory() && !file.isHidden()){
                arrayList.addAll(readSongs(file));
            }else{
                if(file.getName().endsWith(".mp3")|| file.getName().endsWith(".mav")){
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
