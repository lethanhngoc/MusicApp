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
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.adapter.ListSongAdapter;
import com.android.mainapp.R;
import com.android.model.Advertisement;
import com.android.model.Album;
import com.android.model.Playlist;
import com.android.model.Song;
import com.android.model.TheLoai;
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
    FloatingActionButton floatingActionButton, btnShufflePlay;
    ImageView imgListSong;
    ArrayList<Song> songArrayList;
    ListSongAdapter listSongAdapter;
    Playlist playlist;
    TheLoai theLoai;
    Album album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        DataIntent();
        Anhxa();
        init();
        if(advertisement != null && !advertisement.getTenBaiHat().equals("")){
            setValueInView(advertisement.getTenBaiHat(),advertisement.getHinhBaiHat());
            GetDataQuangcao(advertisement.getIdQuangCao());
        }
        if(playlist != null && !playlist.getTen().equals("")){
            setValueInView(playlist.getTen(),playlist.getHinhPlaylist());
            GetDataPlayList(playlist.getIdPlaylist());
        }
        if(theLoai!=null && !theLoai.getGenreName().equals("")){
            setValueInView(theLoai.getGenreName(),theLoai.getGenreThumb());
            GetDataTheLoai(theLoai.getGenreId());
        }
        if(album!=null && !album.getTenAlbum().equals("")){
            setValueInView(album.getTenAlbum(),album.getHinhanhAlbum());
            GetDataAlbum(album.getIdAlbum());
        }
    }

    private void GetDataAlbum(String idAlbum) {
        Dataservice dataservice=APIService.getService();
        Call<List<Song>> callback =dataservice.GetDanhsachbaihattheoalbum(idAlbum);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                Log.d("idtheloaibaihat",songArrayList.size()+"");
                songArrayList.clear();
                listSongAdapter = new ListSongAdapter(ListSongsActivity.this,songArrayList);
                recyclerViewListSongs.setLayoutManager(new LinearLayoutManager(ListSongsActivity.this));
                recyclerViewListSongs.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataTheLoai(String idtheloai){
        Dataservice dataservice=APIService.getService();
        Call<List<Song>> callback =dataservice.GetDanhsachbaihattheochude(idtheloai);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                Log.d("idtheloaibaihat",songArrayList.size()+"");
                listSongAdapter = new ListSongAdapter(ListSongsActivity.this,songArrayList);
                recyclerViewListSongs.setLayoutManager(new LinearLayoutManager(ListSongsActivity.this));
                recyclerViewListSongs.setAdapter(listSongAdapter);

                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
    }

    private void GetDataPlayList(String idplaylist) {
        Dataservice dataservice = APIService.getService();
        Call<List<Song>> callback = dataservice.GetListSongsForPlayList(idplaylist);
        callback.enqueue(new Callback<List<Song>>() {
            @Override
            public void onResponse(Call<List<Song>> call, Response<List<Song>> response) {
                songArrayList = (ArrayList<Song>) response.body();
                listSongAdapter = new ListSongAdapter(ListSongsActivity.this,songArrayList);
                recyclerViewListSongs.setLayoutManager(new LinearLayoutManager(ListSongsActivity.this));
                recyclerViewListSongs.setAdapter(listSongAdapter);
                eventClick();
            }

            @Override
            public void onFailure(Call<List<Song>> call, Throwable t) {

            }
        });
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
                eventClick();
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
        collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
        collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
        floatingActionButton.setEnabled(false);

        toolbar= findViewById(R.id.toolbardanhsach);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void Anhxa() {
        coordinatorLayout           = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout     = findViewById((R.id.collapsingtoolbar));
        toolbar                     = findViewById(R.id.toolbardanhsach);
        recyclerViewListSongs       = findViewById(R.id.RecyclerviewListSongs);
        floatingActionButton        = findViewById(R.id.floatingactionbutton);
        imgListSong                 = findViewById(R.id.imageviewlistsongs);
    }

    private void DataIntent() {
        Intent intent = getIntent();
        if(intent != null){
            if(intent.hasExtra("banner")){
                advertisement = (Advertisement) intent.getSerializableExtra("banner");
                Toast.makeText(this,advertisement.getTenBaiHat(), Toast.LENGTH_LONG).show();

            }
            if(intent.hasExtra("itemplaylist")){
                playlist = (Playlist) intent.getSerializableExtra("itemplaylist");
//                Toast.makeText(this,playlist.getTenBaiHat(), Toast.LENGTH_LONG).show();
            }
            if(intent.hasExtra("idtheloai")){
//                Log.d("idtheloai",intent.toString());
                theLoai= (TheLoai) intent.getSerializableExtra("idtheloai");
            }
            if(intent.hasExtra("album")){
                album= (Album) intent.getSerializableExtra("album");
            }
        }
    }

    private void eventClick(){
        floatingActionButton.setEnabled(true);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListSongsActivity.this,PlaySongActivity.class);
                intent.putExtra("cacbaihat",songArrayList);
                startActivity(intent);
            }
        });

//        btnShufflePlay.setEnabled(true);
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                System.out.println("HELLLOOOOOOOOO");
//            }
//        });
    }
}
