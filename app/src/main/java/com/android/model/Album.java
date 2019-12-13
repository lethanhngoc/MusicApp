package com.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("IdAlbum")
    @Expose
    private String idAlbum;
    @SerializedName("TenAlbum")
    @Expose
    private String tenAlbum;
    @SerializedName("TencasiAlbum")
    @Expose
    private String tencasiAlbum;
    @SerializedName("HinhanhAlbum")
    @Expose
    private String hinhanhAlbum;

    public String getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(String idAlbum) {
        this.idAlbum = idAlbum;
    }

    public String getTenAlbum() {
        return tenAlbum;
    }

    public void setTenAlbum(String tenAlbum) {
        this.tenAlbum = tenAlbum;
    }

    public String getTencasiAlbum() {
        return tencasiAlbum;
    }

    public void setTencasiAlbum(String tencasiAlbum) {
        this.tencasiAlbum = tencasiAlbum;
    }

    public String getHinhanhAlbum() {
        return hinhanhAlbum;
    }

    public void setHinhanhAlbum(String hinhanhAlbum) {
        this.hinhanhAlbum = hinhanhAlbum;
    }

}