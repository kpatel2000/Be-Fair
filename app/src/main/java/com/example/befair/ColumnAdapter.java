package com.example.befair;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ColumnAdapter extends RecyclerView.Adapter<ColumnAdapter.ColumnViewHolder> {

    Context context;
    List<ColumnModel> columnModelList;
    public ColumnAdapter(Context context, List<ColumnModel> columnModelList) {
        this.context = context;
        this.columnModelList = columnModelList;
    }


    @NonNull
    @Override
    public ColumnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_column, parent, false);
        return new ColumnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ColumnViewHolder holder, int position) {
        holder.columnA.setText(columnModelList.get(position).columnA);
        holder.columnB.setText(columnModelList.get(position).columnB);
        if (!columnModelList.get(position).answer.equals("")){
            holder.answer.setText(columnModelList.get(position).answer);
        }
        holder.answer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                columnModelList.get(position).setAnswer(holder.answer.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }


    public void update(List<ColumnModel> columnModelList){
        this.columnModelList = columnModelList;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return columnModelList.size();
    }

    static class ColumnViewHolder extends RecyclerView.ViewHolder{
        EditText answer;
        TextView columnA, columnB;

        public ColumnViewHolder(@NonNull View itemView) {
            super(itemView);
            answer = itemView.findViewById(R.id.quiz_matching_answer);
            columnA = itemView.findViewById(R.id.quiz_matching_columnA);
            columnB = itemView.findViewById(R.id.quiz_matching_columnB);
        }
    }
}
