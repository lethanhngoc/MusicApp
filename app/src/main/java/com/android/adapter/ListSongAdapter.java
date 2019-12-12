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

import java.util.ArrayList;

public class ListSongAdapter extends  RecyclerView.Adapter<ListSongAdapter.ViewHolder> {

    Context context;
    ArrayList<Song> songArrayList;

    public ListSongAdapter(Context context, ArrayList<Song> songArrayList) {
        this.context = context;
        this.songArrayList = songArrayList;
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
        Song song = songArrayList.get(position);
        holder.txtnamesinger.setText(song.getCasi());
        holder.txtnamesong.setText(song.getTenbaihat());
        holder.txtindex.setText(position+ 1 + "");
    }

    @Override
    public int getItemCount() {
        return songArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txtnamesong,txtnamesinger;
        ImageView imglike;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex        = itemView.findViewById(R.id.textviewlistindex);
            txtnamesong     = itemView.findViewById(R.id.textviewnamesong);
            txtnamesinger   = itemView.findViewById(R.id.textviewnamesinger);
            imglike         = itemView.findViewById(R.id.imageviewlikelistsongs);
        }

    }
}
