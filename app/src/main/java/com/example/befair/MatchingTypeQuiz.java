package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchingTypeQuiz {

    @SerializedName("LessonName")
    @Expose
    String LessonName;
    @SerializedName("Quiz")
    @Expose
    ArrayList<MatchingTypeQuizContent> quizes;
}
