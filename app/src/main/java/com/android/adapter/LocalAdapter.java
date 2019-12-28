package com.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.mainapp.R;
import com.android.model.LocalSong;

import java.util.ArrayList;

public class LocalAdapter extends RecyclerView.Adapter<LocalAdapter.ViewHolder> {


    Context context;
    ArrayList<LocalSong> arrLocalSong = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.dong_local_song,parent,false);
        return new ViewHolder(view);
    }
    public LocalAdapter(Context context, ArrayList<LocalSong> arrLocalSong) {
        this.context = context;
        this.arrLocalSong = arrLocalSong;
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LocalSong localSong = arrLocalSong.get(position);
        holder.txtindex.setText(position + 1 +"");
        holder.txttenbaihat.setText(localSong.getTenBaihat());
    }

    @Override
    public int getItemCount() {
        return arrLocalSong.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtindex,txttenbaihat;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtindex = itemView.findViewById(R.id.textviewlocalindex);
            txttenbaihat = itemView.findViewById(R.id.textviewlocaltenbaihat);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(context, PlaySongActivity.class);
//                    intent.putExtra("localsong",arrLocalSong);
//                    context.startActivity(intent);
//                }
//            });
        }
    }

}
