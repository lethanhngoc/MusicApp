package com.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.mainapp.R;
import com.android.model.Song;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SonghotAdapter extends RecyclerView.Adapter<SonghotAdapter.ViewHolder>{
    Context context;
    ArrayList<Song> songArrayList;

    public SonghotAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_song_hot,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Song song = songArrayList.get(position);
        holder.txtcasi.setText(song.getCasi());
        holder.txtten.setText(song.getTenbaihat());
        Picasso.with(context).load(song.getHinhbaihat()).into(holder.imghinh);
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtten, txtcasi;
        ImageView imghinh,imgluotthich;
        public ViewHolder(View itemView){
            super(itemView);
            txtten = itemView.findViewById(R.id.textviewnamesonghot);
            txtcasi = itemView.findViewById(R.id.textviewsingersonghot);
            imghinh = itemView.findViewById(R.id.imageviewsonghot);
            imgluotthich = itemView.findViewById(R.id.imageviewluotthich);
        }
    }
}
