package com.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.mainapp.R;
import com.android.model.Song;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;

public class LocalMusicAdapter extends  RecyclerView.Adapter<LocalMusicAdapter.ViewHolder> {

    Context context;
    ArrayList<File> songFiles;

    public LocalMusicAdapter(Context context, ArrayList<File> songFiles) {
        this.context = context;
        this.songFiles = songFiles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from((context));
        View view = inflater.inflate(R.layout.dong_list_songs ,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        File song = songFiles.get(position);
        System.out.println(song.getName());
        holder.txtnamesinger.setText("Unknown");
        holder.txtnamesong.setText(song.getName());
//        holder.thumb.setImageBitmap(getData(song));
        holder.thumb.setImageURI(getData(song));
        holder.txtindex.setText(position+ 1 + "");
    }

    @Override
    public int getItemCount() {
        return songFiles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView txtindex,txtnamesong,txtnamesinger;
        ImageView thumb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewlistindex);
            txtnamesong = itemView.findViewById(R.id.textviewnamesong);
            txtnamesinger = itemView.findViewById(R.id.textviewnamesinger);
            thumb = itemView.findViewById(R.id.imageviewlikelistsongs);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaySongActivity.class);
                    String songName=songFiles.get(getPosition()).getName();
                    String singerName="Bui Anh Tuan";
                    String songUri=songFiles.get(getPosition()).getPath();
                    String id=String.valueOf(getPosition());
                    Uri thumbUri=getData(songFiles.get(getPosition()));
                    Song localSong=new Song(id,songName,singerName,songUri,thumbUri);
                    intent.putExtra("cakhuc",localSong);
                    context.startActivity(intent);
                }
            });
        }
    }

    public Uri getData(File songFile){
        Bitmap bm=null;
        ImageView coverart;
        MediaMetadataRetriever mmr=new MediaMetadataRetriever();
        mmr.setDataSource(songFile.getPath());
        byte [] data = mmr.getEmbeddedPicture();

        // convert the byte array to a bitmap
//        if(data != null)
//        {
//            Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
//            if(coverart!=null){
//                coverart.setImageBitmap(bitmap); //associated cover art in bitmap
//            }
//        }
//        else
//        {
//            coverart.setImageResource(R.drawable.iconloved);//any default cover resourse folder
//        }
//
//        coverart.setAdjustViewBounds(true);
//        coverart.setLayoutParams(new RelativeLayout.LayoutParams(500, 500));
//        return coverart;

        if(data != null) {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            bm = BitmapFactory.decodeByteArray(data, 0, data.length);
            bm.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
            String path = MediaStore.Images.Media.insertImage(context.getContentResolver(), bm, "Title", null);
            return Uri.parse(path);
        }
        return null;
    }
}
