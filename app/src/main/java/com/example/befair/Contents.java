package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contents {

    @SerializedName("TopicName")
    @Expose
    String TopicName;

    @SerializedName("ImageName")
    @Expose
    String ImageName;
}
