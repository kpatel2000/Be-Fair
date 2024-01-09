package com.example.befair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ChoiceAdapter extends ArrayAdapter<ChoiceModel> {
    int count =0;
    Queue<Integer> deselectedIndex = new LinkedList<>();
    Context context;
    OnChoiceClicked onChoiceClicked;
    public ChoiceAdapter(@NonNull Context context, List<ChoiceModel> listOfChoices, OnChoiceClicked onChoiceClicked) {
        super(context, 0, listOfChoices);
        this.context = context;
        this.onChoiceClicked = onChoiceClicked;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null){
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_choice,parent,false);
        }
        ChoiceModel choice = getItem(position);
        TextView choiceTextView = view.findViewById(R.id.btn_choice);
        TextView selectionTextView = view.findViewById(R.id.selection);
        choiceTextView.setText(choice.choice.toLowerCase());
        choiceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!choice.isSelected){
//                    selectionTextView.setVisibility(View.VISIBLE);
//                    choiceTextView.setBackground(AppCompatResources.getDrawable(context,R.drawable.choice_selected));
                    if (deselectedIndex.isEmpty()){
                        count++;
                        selectionTextView.setText(String.valueOf(count));
                        choice.isSelected = true;
                        choice.setIndex(count);
                        onChoiceClicked.onClick(choice,false, count+1);
                    }else{
                        int index = deselectedIndex.remove();
                        selectionTextView.setText(String.valueOf(index));
                        choice.isSelected = true;
                        choice.setIndex(index);
                        if (deselectedIndex.isEmpty()) {
                            onChoiceClicked.onClick(choice, false, count+1);
                        }else{
                            onChoiceClicked.onClick(choice, false, deselectedIndex.element());
                        }
                    }
                }else {
//                    choiceTextView.setBackground(AppCompatResources.getDrawable(context,R.drawable.choice_unselected));
                    int index = Integer.parseInt(selectionTextView.getText().toString());
                    deselectedIndex.add(index);
                    selectionTextView.setVisibility(View.GONE);
                    choice.isSelected = false;
                    choice.setIndex(-1);
                    onChoiceClicked.onClick(choice,true, deselectedIndex.element());
                }
            }
        });
        return view;
    }
    interface OnChoiceClicked{
        void onClick(ChoiceModel choiceModel, boolean isRemoved, int nextSelection);
    }
}