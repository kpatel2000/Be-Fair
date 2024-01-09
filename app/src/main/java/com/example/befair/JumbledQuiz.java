package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JumbledQuiz {

    @SerializedName("LessonName")
    @Expose
    String LessonName;
    @SerializedName("Quizes")
    @Expose
    ArrayList<JumbledQuizContent> quizes;
}
