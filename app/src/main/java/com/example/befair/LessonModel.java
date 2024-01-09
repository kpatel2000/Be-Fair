package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class LessonModel {

    @SerializedName("Lessons")
    @Expose
    ArrayList<Lessons> lessons;
}
