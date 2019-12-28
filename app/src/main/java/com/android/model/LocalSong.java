package com.android.model;

import android.os.Parcel;
import android.os.Parcelable;

public class LocalSong  implements Parcelable {
    private  String idBaihat;
    private  String tenBaihat;
    private String Uri;

    public String getUri() {
        return Uri;
    }

    public void setUri(String uri) {
        Uri = uri;
    }

    public LocalSong(String idBaihat, String tenBaihat, String Uri) {
        this.idBaihat   = idBaihat;
        this.tenBaihat  = tenBaihat;
        this.Uri        = Uri;
    }

    protected LocalSong(Parcel in) {
        idBaihat = in.readString();
        tenBaihat = in.readString();
    }

    public static final Creator<LocalSong> CREATOR = new Creator<LocalSong>() {
        @Override
        public LocalSong createFromParcel(Parcel in) {
            return new LocalSong(in);
        }

        @Override
        public LocalSong[] newArray(int size) {
            return new LocalSong[size];
        }
    };

    public String getIdBaihat() {
        return idBaihat;
    }

    public void setIdBaihat(String idBaihat) {
        this.idBaihat = idBaihat;
    }

    public String getTenBaihat() {
        return tenBaihat;
    }

    public void setTenBaihat(String tenBaihat) {
        this.tenBaihat = tenBaihat;
    }

    @Override
    public String toString() {
        return "LocalSong{" +
                "idBaihat='" + idBaihat + '\'' +
                ", tenBaihat='" + tenBaihat + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(idBaihat);
        dest.writeString(tenBaihat);
    }
}
