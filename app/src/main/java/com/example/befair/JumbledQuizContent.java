package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class JumbledQuizContent {
    @SerializedName("Clue")
    @Expose
    String clue;

    @SerializedName("CorrectAnswer")
    @Expose
    String correctAnswer;

    @SerializedName("JumbledLetter")
    @Expose
    String jumbledLetter;
}
