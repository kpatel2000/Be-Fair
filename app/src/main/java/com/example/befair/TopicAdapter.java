package com.example.befair;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.befair.databinding.ItemTopicBinding;

import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.topicViewHolder>{
    List<Contents> topics;
    Context context;
    private  onTopicItemClickListener topicItemClickListener;

    public TopicAdapter(Context context, List<Contents> topics, onTopicItemClickListener topicItemClickListener) {
        this.topics = topics;
        this.context = context;
        this.topicItemClickListener = topicItemClickListener;
    }

    @NonNull
    @Override
    public topicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ItemTopicBinding binding = ItemTopicBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new TopicAdapter.topicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull topicViewHolder holder, int position) {
        holder.binding.itemTopicName.setText(topics.get(position).TopicName);
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }


    class topicViewHolder extends RecyclerView.ViewHolder{
        ItemTopicBinding binding;
        public topicViewHolder(ItemTopicBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            binding.getRoot().setOnClickListener(view -> {
                topicItemClickListener.onTopicItemClick(getAdapterPosition());
            });
        }
    }
}
