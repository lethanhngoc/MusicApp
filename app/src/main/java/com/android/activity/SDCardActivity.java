package com.android.activity;

import android.Manifest;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.android.mainapp.R;
import com.android.model.Song;

import java.io.File;
import java.util.ArrayList;

public class SDCardActivity extends AppCompatActivity {

    private ListView listView;
//    private Song songs[];
    private String songNames[];
    TextView textView1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_sdcard);

        listView = findViewById(R.id.listviewsdcard);
        textView1 = findViewById(R.id.textTitleSDCard);

        String[] requiredPermissions = { Manifest.permission.READ_EXTERNAL_STORAGE };
        ActivityCompat.requestPermissions(this, requiredPermissions, 0);

        System.out.println("env: "+Environment.getExternalStoragePublicDirectory("Downloads").listFiles());
        ArrayList<File> songs = readSongs(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS));
        songNames = new String[songs.size()];
//        String text ;
//        toast("hello");
        for(int i = 0 ; i< songs.size() ; i++){
            songNames[i] = songs.get(i).getName().replace(".mp3","");
            Log.d("Annguyen",songNames[i]);
//            toast(songs.get(i).getName());
        }



//        text = songNames[0].toString();
//        textView1.setText("ANNGUYEN");
//        textView1.setText(text);
//        Log.d("Nguyentest","ANNGUYEN");
//        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.dong_sdcard,R.id.textsdcard,songNames);
//
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//            }
//        });
    }

    public void toast(String text){
        Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT).show();
    }
    private ArrayList<File> readSongs(File root){
        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = root.listFiles();
        for(File file : files){
            if(file.isDirectory() && !file.isHidden()){
                arrayList.addAll(readSongs(file));
            }else{
                if(file.getName().endsWith(".mp3")|| file.getName().endsWith(".mav")){
                    arrayList.add(file);
                }
            }
        }
        return arrayList;
    }
}
