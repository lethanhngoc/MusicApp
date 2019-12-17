package com.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.adapter.PlaySongsAdapter;
import com.android.mainapp.R;

public class FragmentPlayListSongs extends Fragment {

    View view;
    RecyclerView recyclerViewplaynhac;
    PlaySongsAdapter playSongsAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view  = inflater.inflate(R.layout.fragment_play_list_songs,container,false);
        recyclerViewplaynhac = view.findViewById(R.id.recyclerviewplaybaihat);
        if(PlaySongActivity.arrSongs.size() > 0){
            playSongsAdapter = new PlaySongsAdapter(getActivity(),PlaySongActivity.arrSongs);
            recyclerViewplaynhac.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerViewplaynhac.setAdapter(playSongsAdapter);
        }

        return view;
    }
}
