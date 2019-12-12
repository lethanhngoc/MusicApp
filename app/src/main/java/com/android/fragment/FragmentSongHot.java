package com.android.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.adapter.SonghotAdapter;
import com.android.mainapp.R;
import com.android.model.Song;
import com.android.service.APIService;
import com.android.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentSongHot extends Fragment {

    View view;
    RecyclerView recyclerViewsonghot;
    SonghotAdapter songhotAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_song_hot,container,false);
        recyclerViewsonghot = view.findViewById(R.id.Recyclerviewsonghot);
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetSongHot();
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                ArrayList<Song> songArrayList = (ArrayList<Song>) response.body();
                songhotAdapter = new SonghotAdapter(getActivity(),songArrayList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
                linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerViewsonghot.setLayoutManager(linearLayoutManager);
                recyclerViewsonghot.setAdapter(songhotAdapter);
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }
}
