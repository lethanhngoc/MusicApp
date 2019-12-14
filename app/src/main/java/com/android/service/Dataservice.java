package com.android.service;

import com.android.model.Advertisement;
import com.android.model.Album;
import com.android.model.ChuDeVaTheLoaiTrongNgay;
import com.android.model.Playlist;
import com.android.model.Song;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Advertisement>> GetDataBanner();

    @GET("playlistforcurrent.php")
    Call<List<Playlist>>  GetPlaylistCurrentDay();

    @GET("chudevatheloaitrongngay.php")
    Call<ChuDeVaTheLoaiTrongNgay> GetGenreAndTopic();

    @GET("baihatduocthich.php")
    Call<List<Song>>  GetSongHot();

    @FormUrlEncoded
    @POST("ListSongs.php")
    Call<List<Song>> GetListSongsForAds(@Field("idquangcao") String idquangcao);

    @FormUrlEncoded
    @POST("ListSongs.php")
    Call<List<Song>> GetListSongsForPlayList(@Field("idplaylist") String idplaylist);
  
    @GET("albumhot.php")
    Call<List<Album>> GetAlbumHot();

    @FormUrlEncoded
    @POST("ListSongs.php")
    Call<List<Song>> GetDanhsachbaihattheochude(@Field("idtheloai") String idtheloai);
  
}
