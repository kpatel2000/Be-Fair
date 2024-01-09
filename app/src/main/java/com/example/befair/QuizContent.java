package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QuizContent {
    @SerializedName("Question")
    @Expose
    String question;

    @SerializedName("CorrectAnswer")
    @Expose
    String correctAnswer;

    @SerializedName("Image")
    @Expose
    String image;

}
