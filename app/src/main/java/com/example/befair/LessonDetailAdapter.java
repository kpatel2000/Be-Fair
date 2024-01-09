package com.example.befair;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.befair.databinding.ItemLessonDetailBinding;

import java.util.List;

public class LessonDetailAdapter extends RecyclerView.Adapter<LessonDetailAdapter.detailViewHolder>{

    List<Contents> lessonDetails;
    Context context;

    public LessonDetailAdapter(List<Contents> lessonDetails, Context context) {
        this.lessonDetails = lessonDetails;
        this.context = context;
    }

    @NonNull
    @Override
    public detailViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemLessonDetailBinding binding = ItemLessonDetailBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new LessonDetailAdapter.detailViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull detailViewHolder holder, int position) {
        holder.binding.detailTopicTitle.setText(lessonDetails.get(position).TopicName);
        final int resourceId = context.getResources().getIdentifier(lessonDetails.get(position).ImageName, "drawable", context.getPackageName());
        holder.binding.detailImage.setImageResource(resourceId);
    }

    @Override
    public int getItemCount() {
        return lessonDetails.size();
    }

    class detailViewHolder extends RecyclerView.ViewHolder{
        ItemLessonDetailBinding binding;
        public detailViewHolder(ItemLessonDetailBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}