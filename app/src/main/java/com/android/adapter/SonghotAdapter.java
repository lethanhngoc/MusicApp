package com.android.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.activity.PlaySongActivity;
import com.android.mainapp.R;
import com.android.model.Song;
import com.android.service.APIService;
import com.android.service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

            //Bai53
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, PlaySongActivity.class);
                    intent.putExtra("cakhuc",songArrayList.get(getPosition()));
                    context.startActivity(intent);
            imgluotthich.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    imgluotthich.setImageResource(R.drawable.iconloved);
                    Dataservice dataservice= APIService.getService();
                    Call<String> callback=dataservice.UpdateLuotThich("1","2");
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String ketqua=response.body();
                            System.err.println("error******: "+ketqua);
                            if(ketqua.equals("1")){
                                Toast.makeText(context,"Da thich", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(context,"Loi!!", Toast.LENGTH_SHORT).show();
                            }

                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                        }
                    });
                    imgluotthich.setEnabled(false);
                }
            });
        }
    }
}
