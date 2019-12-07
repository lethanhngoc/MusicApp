package com.android.service;

import com.android.model.Advertisement;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Dataservice {

    @GET("songbanner.php")
    Call<List<Advertisement>> GetDataBanner();

}
