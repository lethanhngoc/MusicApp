package com.android.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.Color;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.adapter.ViewPagerPlayListSongs;
import com.android.fragment.FragmentMusicDisc;
import com.android.fragment.FragmentPlayListSongs;
import com.android.mainapp.R;
import com.android.model.Song;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;

public class PlaySongActivity extends AppCompatActivity {

    Toolbar toolbarplaynhac;
    TextView txtTimesong,txtTotaltimesong;
    SeekBar sktime;
    ImageButton imgplay,imgnext,imgprev,imgrepeat,imgrandom,imgstop;
    ViewPager viewpagerplaynhac;
    public static ArrayList<Song> arrSongs = new ArrayList<>();
    public static ViewPagerPlayListSongs adapternhac;
    FragmentMusicDisc fragmentMusicDisc;
    FragmentPlayListSongs fragmentPlayListSongs;
    MediaPlayer mediaPlayer;
    int pos = 0;
    boolean repeat = false;
    boolean checkrandom = false;
    boolean next = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_song);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        GetDataFromIntent();
        Init();
        evenClick();

    }

    private void evenClick() {
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(adapternhac.getItem(1) != null){
                    if(arrSongs.size() >0){
                        fragmentMusicDisc.Playnhac(arrSongs.get(0).getHinhbaihat());
                        handler.removeCallbacks(this);
                    }else{
                        handler.postDelayed(this,300);
                    }
                }
            }
        },500);
        imgplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    imgplay.setImageResource(R.drawable.iconplay);
                }else{
                    mediaPlayer.start();
                    imgplay.setImageResource(R.drawable.iconpause);
                }
            }
        });
        imgstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
//                mediaPlayer.release();
                getSupportActionBar().setTitle(arrSongs.get(pos).getTenbaihat());
                new PlayMp3().execute(arrSongs.get(pos).getLinkbaihat());
//                mediaPlayer.pause();
                imgplay.setImageResource(R.drawable.iconpause);
            }
        });
        imgrepeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(repeat == false){
                    if(checkrandom == true){
                        checkrandom = false;
                        imgrepeat.setImageResource(R.drawable.iconsyned);
                        imgrandom.setImageResource(R.drawable.iconsuffle);
                    }
                    imgrepeat.setImageResource(R.drawable.iconsyned);
                    repeat = true;
                }else{
                    imgrepeat.setImageResource(R.drawable.iconrepeat);
                    repeat = false;
                }
            }
        });
        imgrandom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkrandom == false){
                    if(repeat == true){
                        repeat = false;
                        imgrandom.setImageResource(R.drawable.iconshuffled);
                        imgrepeat.setImageResource(R.drawable.iconrepeat);

                    }
                    imgrandom.setImageResource(R.drawable.iconshuffled);
                    checkrandom = true;
                }else{
                    imgrandom.setImageResource(R.drawable.iconsuffle);
                    checkrandom = false;
                }
            }
        });
        sktime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });

        imgnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrSongs.size() >0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(pos < (arrSongs.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        pos ++;
                        if(repeat == true){
                            if(pos == 0){
                                pos = arrSongs.size();
                            }
                            pos -= 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(arrSongs.size());
                            if(index == pos){
                                pos = index - 1;
                            }
                            pos = index;
                        }
                        if(pos >(arrSongs.size()-1)){
                            pos = 0;
                        }
                        new PlayMp3().execute(arrSongs.get(pos).getLinkbaihat());
                        fragmentMusicDisc.Playnhac(arrSongs.get(pos).getHinhbaihat());
                        getSupportActionBar().setTitle(arrSongs.get(pos).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },2000);
            }
        });
        imgprev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrSongs.size() >0){
                    if(mediaPlayer.isPlaying() || mediaPlayer != null){
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        mediaPlayer = null;
                    }
                    if(pos < (arrSongs.size())){
                        imgplay.setImageResource(R.drawable.iconpause);
                        pos --;
                        if(repeat == true){
                            pos += 1;
                        }
                        if(checkrandom == true){
                            Random random = new Random();
                            int index = random.nextInt(arrSongs.size());
                            if(index == pos){
                                pos = index - 1;
                            }
                            pos = index;
                        }
                        if(pos < 0){
                            pos = arrSongs.size() - 1;
                        }
                        new PlayMp3().execute(arrSongs.get(pos).getLinkbaihat());
                        fragmentMusicDisc.Playnhac(arrSongs.get(pos).getHinhbaihat());
                        getSupportActionBar().setTitle(arrSongs.get(pos).getTenbaihat());
                        UpdateTime();
                    }
                }
                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler1 = new Handler();
                handler1.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },2000);
            }
        });
    }

    private void GetDataFromIntent() {
        Intent intent = getIntent();
        arrSongs.clear();

        if(intent != null)
        {
            if(intent.hasExtra("cakhuc")){
                Song song = intent.getParcelableExtra("cakhuc");
                arrSongs.add(song);
//                Toast.makeText(this,song.getTenbaihat(),Toast.LENGTH_SHORT).show();
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
        imgstop                 = findViewById(R.id.imageviewbuttonstop);
        viewpagerplaynhac       = findViewById(R.id.viewpagerplaynhac);

        setSupportActionBar(toolbarplaynhac);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbarplaynhac.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                mediaPlayer.stop();
                arrSongs.clear();
            }
        });
        toolbarplaynhac.setTitleTextColor(Color.WHITE);

        fragmentMusicDisc = new FragmentMusicDisc();
        fragmentPlayListSongs =  new FragmentPlayListSongs();
         adapternhac = new ViewPagerPlayListSongs(getSupportFragmentManager());
         adapternhac.AddFragment(fragmentPlayListSongs);
         adapternhac.AddFragment(fragmentMusicDisc);
         viewpagerplaynhac.setAdapter(adapternhac);

            //Xu ly su kien phat nhac init
        fragmentMusicDisc = (FragmentMusicDisc) adapternhac.getItem(1);
        if(arrSongs.size()>0){
            getSupportActionBar().setTitle(arrSongs.get(0).getTenbaihat());
            new PlayMp3().execute(arrSongs.get(0).getLinkbaihat());
            imgplay.setImageResource(R.drawable.iconpause);
        }

    }
    class PlayMp3 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            return strings[0];
        }

        @Override
        protected void onPostExecute(String song) {
            super.onPostExecute(song);
            try{
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                }
            });
                mediaPlayer.setDataSource(song);
                mediaPlayer.prepare();
            } catch (IOException e) {
                e.printStackTrace();
            }
            mediaPlayer.start();
            TimeSong();
            UpdateTime();
        }
    }

    private void TimeSong() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
        txtTotaltimesong.setText(simpleDateFormat.format(mediaPlayer.getDuration()));
        sktime.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(mediaPlayer != null){
                    sktime.setProgress(mediaPlayer.getCurrentPosition());
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                    txtTimesong.setText(simpleDateFormat.format(mediaPlayer.getCurrentPosition()));
                    handler.postDelayed(this,300);
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            next = true;
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        },300);
        final Handler handler1 = new Handler();
        handler1.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(next == true){
                    if(pos < (arrSongs.size())) {
                        imgplay.setImageResource(R.drawable.iconpause);
                        pos++;
                        if (repeat == true) {
                            if (pos == 0) {
                                pos = arrSongs.size();
                            }
                            pos -= 1;
                        }
                        if (checkrandom == true) {
                            Random random = new Random();
                            int index = random.nextInt(arrSongs.size());
                            if (index == pos) {
                                pos = index - 1;
                            }
                            pos = index;
                        }
                        if (pos > (arrSongs.size() - 1)) {
                            pos = 0;
                        }
                        new PlayMp3().execute(arrSongs.get(pos).getLinkbaihat());
                        fragmentMusicDisc.Playnhac(arrSongs.get(pos).getHinhbaihat());
                        getSupportActionBar().setTitle(arrSongs.get(pos).getTenbaihat());
                    }
                imgprev.setClickable(false);
                imgnext.setClickable(false);
                Handler handler2 = new Handler();
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        imgprev.setClickable(true);
                        imgnext.setClickable(true);
                    }
                },5000);
                next = false;
                handler2.removeCallbacks(this);
                }else{
                    handler1.postDelayed(this,1000);
                }
            }
        },1000);
    }
}
