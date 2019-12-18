package com.android.service;

import com.android.model.Advertisement;
import com.android.model.Album;
import com.android.model.ChuDe;
import com.android.model.ChuDeVaTheLoaiTrongNgay;
import com.android.model.Playlist;
import com.android.model.Song;
import com.android.model.TheLoai;

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


    @GET("danhsachplaylist.php")
    Call<List<Playlist>> GetDanhSachCacPlayList();

    @FormUrlEncoded
    @POST("ListSongs.php")
    Call<List<Song>> GetDanhsachbaihattheochude(@Field("idtheloai") String idtheloai);

    @GET("tatcachude.php")
    Call<List<ChuDe>> GetAllChuDe();

    @FormUrlEncoded
    @POST("theloaitheochude.php")
    Call<List<TheLoai>> GetTheloaitheochude(@Field("idchude") String idchude);

    @GET("tatcaalbum.php")
    Call<List<Album>> GetAllAlbum();

    @FormUrlEncoded
    @POST("ListSongs.php")
    Call<List<Song>> GetDanhsachbaihattheoalbum(@Field("idalbum") String idalbum);

    @FormUrlEncoded
    @POST("updateluotthich.php")
    Call<String> UpdateLuotThich(@Field("luotthich") String luotthich, @Field("idbaihat") String idbaihat);

    @FormUrlEncoded
    @POST("searchbaihat.php")
    Call<List<Song>> GetSearchBaiHat (@Field("tukhoa") String tukhoa);
}
