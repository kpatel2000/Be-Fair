package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class JumbledQuizModel {

    @SerializedName("JumbledQuizes")
    @Expose
    ArrayList<JumbledQuiz> jumbledQuizes;
}
