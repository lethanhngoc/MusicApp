package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import androidx.appcompat.widget.Toolbar;

import com.android.adapter.DanhsachtheloaitheochudeAdapter;
import com.android.mainapp.R;
import com.android.model.ChuDe;
import com.android.model.TheLoai;
import com.android.service.APIService;
import com.android.service.Dataservice;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DanhsachtheloaitheochudeActivity extends AppCompatActivity {

    ChuDe chuDe;
    RecyclerView recyclerViewtheloaitheochude;
    Toolbar toolbartheloaitheochude;
    DanhsachtheloaitheochudeAdapter danhsachtheloaitheochudeAdapter;
    private Dataservice dataservice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theloaitheochude);
        GetIntent();
        init();
        GetData();
    }

    public void GetData(){
        Dataservice dataservice= APIService.getService();
        Call<List<TheLoai>> callback=dataservice.GetTheloaitheochude(chuDe.getTopicId());
        callback.enqueue(new Callback<List<TheLoai>>() {
            @Override
            public void onResponse(Call<List<TheLoai>> call, Response<List<TheLoai>> response) {
                ArrayList<TheLoai> mangtheloai = (ArrayList<TheLoai>) response.body();
                danhsachtheloaitheochudeAdapter =new DanhsachtheloaitheochudeAdapter(DanhsachtheloaitheochudeActivity.this,mangtheloai);
                recyclerViewtheloaitheochude.setLayoutManager(new GridLayoutManager(DanhsachtheloaitheochudeActivity.this, 2));
                recyclerViewtheloaitheochude.setAdapter(danhsachtheloaitheochudeAdapter);
            }

            @Override
            public void onFailure(Call<List<TheLoai>> call, Throwable t) {

            }
        });
    }

    private void init() {
        recyclerViewtheloaitheochude= findViewById(R.id.recyclerviewtheloaitheochude);
        toolbartheloaitheochude=findViewById(R.id.toolbartheloaitheochude);
        setSupportActionBar(toolbartheloaitheochude);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(chuDe.getTopicName());
        toolbartheloaitheochude.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void GetIntent() {
        Intent intent=getIntent();
        if(intent.hasExtra("chude")){
            chuDe= (ChuDe) intent.getSerializableExtra("chude");

        }
    }
}
