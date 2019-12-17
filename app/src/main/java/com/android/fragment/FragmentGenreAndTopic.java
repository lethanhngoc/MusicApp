package com.android.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.android.activity.DanhsachcacplaylistActivity;
import com.android.activity.DanhsachtatcachudeActivity;
import com.android.activity.DanhsachtheloaitheochudeActivity;
import com.android.activity.ListSongsActivity;
import com.android.mainapp.R;
import com.android.model.ChuDe;
import com.android.model.ChuDeVaTheLoaiTrongNgay;
import com.android.model.TheLoai;
import com.android.service.APIService;
import com.android.service.Dataservice;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentGenreAndTopic extends Fragment {

    View view;
    HorizontalScrollView horizontalScrollView;
    TextView txtseemore;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_genre_topic, container,false);
        horizontalScrollView =  view.findViewById(R.id.horizontalScrollview);
        txtseemore = view.findViewById(R.id.textviewseemore);
        txtseemore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), DanhsachtatcachudeActivity.class);
                startActivity(intent);

            }
        });
        GetData();
        return view;
    }

    private void GetData() {
        Dataservice dataservice = APIService.getService();
        Call<ChuDeVaTheLoaiTrongNgay> callback = dataservice.GetGenreAndTopic();
        callback.enqueue(new Callback<ChuDeVaTheLoaiTrongNgay>() {
            @Override
            public void onResponse(Call<ChuDeVaTheLoaiTrongNgay> call, Response<ChuDeVaTheLoaiTrongNgay> response) {
                ChuDeVaTheLoaiTrongNgay genreandtopic = response.body();

                final ArrayList<ChuDe> topicArrayList = new ArrayList<>();
                topicArrayList.addAll(genreandtopic.getChuDe());

                final ArrayList<TheLoai> genreArrayList = new ArrayList<>();
                genreArrayList.addAll(genreandtopic.getTheLoai());


                LinearLayout linearLayout = new LinearLayout(getActivity());
                linearLayout.setOrientation(LinearLayout.HORIZONTAL);

                LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(580,550);
                layout.setMargins(10,20,10,30);
                for(int i = 0 ; i < (topicArrayList.size()); i++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(topicArrayList.get(i).getTopicThumb() != null){
                        Picasso.with(getActivity()).load(topicArrayList.get(i).getTopicThumb()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);
                    final int finalI = i;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), DanhsachtheloaitheochudeActivity.class);
                            intent.putExtra("chude",topicArrayList.get(finalI));
                            startActivity(intent);
                        }
                    });
                }
                for(int j = 0 ; j < (genreArrayList.size()); j++){
                    CardView cardView = new CardView(getActivity());
                    cardView.setRadius(10);
                    ImageView imageView = new ImageView(getActivity());
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    if(genreArrayList.get(j).getGenreThumb() != null){
                        Picasso.with(getActivity()).load(genreArrayList.get(j).getGenreThumb()).into(imageView);
                    }
                    cardView.setLayoutParams(layout);
                    cardView.addView(imageView);
                    linearLayout.addView(cardView);

                    final int finalJ = j;
                    imageView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent= new Intent(getActivity(), ListSongsActivity.class);
                            intent.putExtra("idtheloai",genreArrayList.get(finalJ));
                            startActivity(intent);
                        }
                    });
                }
                horizontalScrollView.addView(linearLayout);
            }

            @Override
            public void onFailure(Call<ChuDeVaTheLoaiTrongNgay> call, Throwable t) {

            }
        });
    }
}
