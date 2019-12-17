package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mainapp.R;
import com.android.model.Song;

import java.util.ArrayList;
import java.util.List;

public class PlaySongActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong,txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay,imgnext,imgprev,imgrepeat,imgrandom;
    ViewPager viewpagerplaynhac;
    ImageView imgbackplaynhac;
    public static ArrayList<Song> arrSongs = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);

        Init();
        GetDataFromIntent();

    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrSongs.clear();

        if(intent != null)
        {
            if(intent.hasExtra("cakhuc")){
                Song song = intent.getParcelableExtra("cakhuc");
                arrSongs.add(song);
                Toast.makeText(this,song.getTenbaihat(),Toast.LENGTH_SHORT).show();
            }
            if(intent.hasExtra("cacbaihat")){
                ArrayList<Song>  SongArrayList =intent.getParcelableArrayListExtra("cacbaihat");
                arrSongs = SongArrayList;
            }
        }
    }

    private void Init() {
        toolbarplaynhac         = findViewById(R.id.toolbarplaynhac);
        txtTimesong             = findViewById(R.id.textviewtimesong);
        txtTotaltimesong        = findViewById(R.id.textviewtotaltimesong);
        sktime                  = findViewById(R.id.seekbarsong);
        imgplay                 = findViewById(R.id.imageviewbuttonplay);
        imgnext                 = findViewById(R.id.imageviewbuttonnext);
        imgprev                 = findViewById(R.id.imageviewbuttonprev);
        imgrepeat               = findViewById(R.id.imageviewbuttonrepeat);
        imgrandom               = findViewById(R.id.imageviewbuttonsuffle);
        viewpagerplaynhac       = findViewById(R.id.viewpagerplaynhac);
        imgbackplaynhac         = findViewById(R.id.imageviewbackplaynhac);

        imgbackplaynhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
