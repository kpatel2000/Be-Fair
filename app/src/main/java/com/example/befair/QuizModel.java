package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class QuizModel {

    @SerializedName("Quizes")
    @Expose
    ArrayList<Quiz> quizes;
}
