package com.example.befair;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class ClueDialog extends Dialog {

    String clue;
    public ClueDialog(@NonNull Context context, String clue) {
        super(context);
        this.clue = clue;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(R.layout.layout_clue_dialog);
        TextView clueTextView = findViewById(R.id.clue);
        clueTextView.setText(clue);
    }
}
