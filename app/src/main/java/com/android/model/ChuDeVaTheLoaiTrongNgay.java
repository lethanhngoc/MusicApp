package com.android.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChuDeVaTheLoaiTrongNgay {

@SerializedName("TheLoai")
@Expose
private List<TheLoai> theLoai = null;
@SerializedName("ChuDe")
@Expose
private List<ChuDe> chuDe = null;

public List<TheLoai> getTheLoai() {
return theLoai;
}

public void setTheLoai(List<TheLoai> theLoai) {
this.theLoai = theLoai;
}

public List<ChuDe> getChuDe() {
return chuDe;
}

public void setChuDe(List<ChuDe> chuDe) {
this.chuDe = chuDe;
}

}