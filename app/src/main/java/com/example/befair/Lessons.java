package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Lessons {

    @SerializedName("LessonName")
    @Expose
    String LessonName;
    @SerializedName("LessonImage")
    @Expose
    String LessonImage;
    @SerializedName("Content")
    @Expose
    ArrayList<Contents> content;
    @SerializedName("IsLocked")
    @Expose
    boolean IsLocked;
}
