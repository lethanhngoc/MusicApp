package com.android.service;

import com.android.model.Advertisement;
import com.android.model.ChuDeVaTheLoaiTrongNgay;
import com.android.model.Playlist;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Advertisement>> GetDataBanner();

    @GET("playlistforcurrent.php")
    Call<List<Playlist>>  GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeVaTheLoaiTrongNgay> GetGenreAndTopic();
}
