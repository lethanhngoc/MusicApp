package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;


import com.android.mainapp.R;
import com.android.model.Advertisement;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ListSongsActivity extends AppCompatActivity {

    Advertisement advertisement;
    CoordinatorLayout coordinatorLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    RecyclerView recyclerViewListSongs;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_songs);

        DateIntent();
        Anhxa();
    }

    private void Anhxa() {
        coordinatorLayout           = findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout     = findViewById((R.id.collapsingtoolbar));
        toolbar                     = findViewById(R.id.toolbarlist);
        recyclerViewListSongs       = findViewById(R.id.RecyclerviewListSongs);
        floatingActionButton        = findViewById(R.id.floatingactionbutton);
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
