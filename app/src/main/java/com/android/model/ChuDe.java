package com.android.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ChuDe implements Serializable {

@SerializedName("Topic_Id")
@Expose
private String topicId;
@SerializedName("Topic_Name")
@Expose
private String topicName;
@SerializedName("Topic_Thumb")
@Expose
private String topicThumb;

public String getTopicId() {
return topicId;
}

public void setTopicId(String topicId) {
this.topicId = topicId;
}

public String getTopicName() {
return topicName;
}

public void setTopicName(String topicName) {
this.topicName = topicName;
}

public String getTopicThumb() {
return topicThumb;
}

public void setTopicThumb(String topicThumb) {
this.topicThumb = topicThumb;
}

}