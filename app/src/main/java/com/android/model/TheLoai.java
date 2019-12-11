package com.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TheLoai {

@SerializedName("Genre_Id")
@Expose
private String genreId;
@SerializedName("Genre_TopicId")
@Expose
private String genreTopicId;
@SerializedName("Genre_Name")
@Expose
private String genreName;
@SerializedName("Genre_Thumb")
@Expose
private String genreThumb;

public String getGenreId() {
return genreId;
}

public void setGenreId(String genreId) {
this.genreId = genreId;
}

public String getGenreTopicId() {
return genreTopicId;
}

public void setGenreTopicId(String genreTopicId) {
this.genreTopicId = genreTopicId;
}

public String getGenreName() {
return genreName;
}

public void setGenreName(String genreName) {
this.genreName = genreName;
}

public String getGenreThumb() {
return genreThumb;
}

public void setGenreThumb(String genreThumb) {
this.genreThumb = genreThumb;
}

}