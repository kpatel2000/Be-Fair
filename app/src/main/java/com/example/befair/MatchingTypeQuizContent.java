package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class MatchingTypeQuizContent {

    @SerializedName("ColumnA")
    @Expose
    ArrayList<String> columnAList;

    @SerializedName("ColumnB")
    @Expose
    ArrayList<String> columnBList;

    @SerializedName("CorrectAnswer")
    @Expose
    ArrayList<String> correctAnswerList;
}
