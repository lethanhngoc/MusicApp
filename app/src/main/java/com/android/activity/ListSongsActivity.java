package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.adapter.ListSongAdapter;
import com.android.mainapp.R;
import com.android.model.Advertisement;
import com.android.model.Song;
import com.android.service.APIService;
import com.android.service.Dataservice;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ListSongsActivity extends AppCompatActivity {

    Advertisement advertisement;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSongs;
    FloatingActionButton floatingActionButton;
    ImageView imgListSong;
    ImageView imgBack;
    ArrayList<Song> songArrayList;
    ListSongAdapter listSongAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        DateIntent();
        Anhxa();
        init();
        if(advertisement != null && !advertisement.getTenBaiHat().equals("")){
            setValueInView(advertisement.getTenBaiHat(),advertisement.getHinhBaiHat());
            GetDataQuangcao(advertisement.getIdQuangCao());
        }
    }

    private void GetDataQuangcao(String idquangcao) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetListSongsForAds(idquangcao);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
               songArrayList = (ArrayList<Song>) response.body();
               listSongAdapter =  new ListSongAdapter(ListSongsActivity.this,songArrayList);
                recyclerViewListSongs.setLayoutManager(new LinearLayoutManager(ListSongsActivity.this));
                recyclerViewListSongs.setAdapter(listSongAdapter);

            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void setValueInView(String ten, String hinh) {
        collapsingToolbarLayout.setTitle(ten);
        try {
            URL url = new URL(hinh);
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            BitmapDrawable bitmapDrawable = new BitmapDrawable(getResources(),bitmap);
            collapsingToolbarLayout.setBackground(bitmapDrawable);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Picasso.with(this).load(hinh).into(imgListSong);
    }

    private void init() {
        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);

    }

    private void Anhxa() {
        coordinatorLayout           = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout     = findViewById((R.id.collapsingtoolbar));
        toolbar                     = findViewById(R.id.toolbarlist);
        recyclerViewListSongs       = findViewById(R.id.RecyclerviewListSongs);
        floatingActionButton        = findViewById(R.id.floatingactionbutton);
        imgListSong                 = findViewById(R.id.imageviewlistsongs);
        imgBack                     = findViewById(R.id.imageviewback);
    }

    private void DateIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                advertisement = (Advertisement) intent.getSerializableExtra("banner");
                Toast.makeText(this,advertisement.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
        }
    }
}
