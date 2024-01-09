package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FillUpsQuizWithChoicesContent {
    @SerializedName("Question")
    @Expose
    String question;

    @SerializedName("CorrectAnswer")
    @Expose
    String correctAnswer;

    @SerializedName("Choices")
    @Expose
    List<String> choices;
}
