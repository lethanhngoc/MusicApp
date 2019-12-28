package com.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.SyncStateContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.mainapp.R;
import com.android.model.Song;

import java.util.ArrayList;

public class PlaySongsAdapter extends RecyclerView.Adapter<PlaySongsAdapter.ViewHolder>{
    Bundle savedInstanceState;
    Context context;
    ArrayList<Song> arrSong;
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_play_bai_hat,parent,false);

        return new ViewHolder(view);
    }

    public PlaySongsAdapter(Context context, ArrayList<Song> arrSong) {
        this.context = context;
        this.arrSong = arrSong;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = arrSong.get(position);
        holder.txtcasi.setText(song.getCasi());
        holder.txtindex.setText(position + 1 +"");
        holder.txttenbaihat.setText(song.getTenbaihat());
    }

    @Override
    public int getItemCount() {
        return arrSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat,txtcasi;
        public ViewHolder(View itemView){
            super(itemView);
            txtcasi         = itemView.findViewById(R.id.textviewplaynhactencasi);
            txtindex        = itemView.findViewById(R.id.textviewplaynhacindex);
            txttenbaihat    = itemView.findViewById(R.id.textviewplaynhactenbaihat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    PlaySongActivity playSongActivity=new PlaySongActivity();
                    System.out.println(getPosition()+"---"+arrSong.size());
                    playSongActivity.playMedia(arrSong,getPosition());
                }
            });
        }
    }
}
