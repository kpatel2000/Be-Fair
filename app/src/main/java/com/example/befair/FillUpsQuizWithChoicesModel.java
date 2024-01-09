package com.example.befair;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class FillUpsQuizWithChoicesModel {
    @SerializedName("Lessons")
    @Expose
    ArrayList<FillUpsQuizWithChoices> fillUpsQuizWithChoices;
}
