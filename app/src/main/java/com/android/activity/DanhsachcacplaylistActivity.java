package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.android.mainapp.R;

public class DanhsachcacplaylistActivity extends AppCompatActivity {


    Toolbar toolbar;
    RecyclerView recyclerViewdanhsachcacplaylist;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhsachcacplaylist);
        anhxa();
        init();
    }

    private void init() {

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
//        setSupportActionBar(toolbar);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//        getSupportActionBar().setTitle("Play Lists");
//        toolbar.setTitleTextColor(getResources().getColor(R.color.colorPurple));
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                finish();
//            }
//        });
    }

    private void anhxa() {
        toolbar                             = findViewById(R.id.toolbardanhsachcacplaylist);
        recyclerViewdanhsachcacplaylist     = findViewById(R.id.Recyclerviewdanhsachcacplaylist);
        imageView                           = findViewById(R.id.imageviewbackseemoreplaylist);
    }
}
