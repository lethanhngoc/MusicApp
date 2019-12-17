package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.android.adapter.AllAlbumAdapter;
import com.android.mainapp.R;
import com.android.model.Album;
import com.android.service.APIService;
import com.android.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtatcaAlbumActivity extends AppCompatActivity {

    RecyclerView recyclerViewAllalbum;
    Toolbar toolbaralbum;
    AllAlbumAdapter allAlbumAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachtatca_album);
        init();
        GetData();
    }

    private void GetData() {
        Dataservice dataservice= APIService.getService();
        Call<List<Album>> callback=dataservice.GetAllAlbum();
        callback.enqueue(new Callback<List<Album>>() {
            @Override
            public void onResponse(Call<List<Album>> call, Response<List<Album>> response) {
                ArrayList<Album> mangalbum = (ArrayList<Album>) response.body();
                allAlbumAdapter =new AllAlbumAdapter(DanhsachtatcaAlbumActivity.this,mangalbum);
                recyclerViewAllalbum.setLayoutManager(new GridLayoutManager(DanhsachtatcaAlbumActivity.this, 2));
                recyclerViewAllalbum.setAdapter(allAlbumAdapter);
            }

            @Override
            public void onFailure(Call<List<Album>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewAllalbum=findViewById(R.id.recyclerviewAllAlbum);
        toolbaralbum=findViewById(R.id.toolbaralbum);
        setSupportActionBar(toolbaralbum);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Tất Cả Album");
        toolbaralbum.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
