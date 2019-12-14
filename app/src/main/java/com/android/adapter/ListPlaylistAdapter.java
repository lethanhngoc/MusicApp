package com.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.ListSongsActivity;
import com.android.mainapp.R;
import com.android.model.Playlist;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListPlaylistAdapter extends  RecyclerView.Adapter<ListPlaylistAdapter.ViewHolder>{

    Context context;
    ArrayList<Playlist> arrplaylist;

    public ListPlaylistAdapter(Context context, ArrayList<Playlist> arrplaylist) {
        this.context = context;
        this.arrplaylist = arrplaylist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_danh_sach_cac_playlist,parent,false);
        return  new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Playlist playlist = arrplaylist.get(position);
        Picasso.with(context).load(playlist.getHinhPlaylist()).into(holder.imghinhnen);
        holder.txttenplaylist.setText(playlist.getTen());
    }

    @Override
    public int getItemCount() {
        return arrplaylist.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imghinhnen;
        TextView txttenplaylist;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imghinhnen      = itemView.findViewById(R.id.imageviewdanhsachcacplaylist);
            txttenplaylist  = itemView.findViewById(R.id.textviewtendanhsachcacplaylist);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ListSongsActivity.class);
                    intent.putExtra("itemplaylist",arrplaylist.get(getPosition()));
                    context.startActivity(intent);
                }
            });
        }

    }
}
