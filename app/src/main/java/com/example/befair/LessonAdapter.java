package com.example.befair;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.ItemLessonEvenBinding;
import com.example.befair.databinding.ItemLessonOddBinding;

import java.util.List;

public class LessonAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Lessons> lessons;
    Context context;
    private static SharedPreferences pref;
    private static onLessonItemClickListener lessonItemClickListener;

    private static final int LAYOUT_EVEN= 0;
    private static final int LAYOUT_ODD= 1;
    RecyclerView.ViewHolder viewHolder = null;

    public LessonAdapter(Context context, List<Lessons> lessons, onLessonItemClickListener lessonItemClickListener) {
        this.lessons = lessons;
        this.context = context;
        LessonAdapter.lessonItemClickListener = lessonItemClickListener;
        pref = context.getSharedPreferences("MyPref",0);
    }

    @Override
    public int getItemViewType(int position) {
        if(position % 2 == 0)
            return LAYOUT_EVEN;
        else
            return LAYOUT_ODD;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        if(viewType==LAYOUT_EVEN){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_even, parent, false);
            viewHolder = new LessonEvenViewHolder(view);
        }else{
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lesson_odd, parent, false);
            viewHolder = new LessonOddViewHolder(view);
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder.getItemViewType() == LAYOUT_EVEN){
            LessonEvenViewHolder evenViewHolder = (LessonEvenViewHolder) holder;
            final int resourceId = context.getResources().getIdentifier(lessons.get(position).LessonImage, "drawable", context.getPackageName());
            Glide.with(context)
                    .load(resourceId)
                    .into(evenViewHolder.lesson);

            int count = pref.getInt("LessonCount",-1);
            if(position>=count){
                evenViewHolder.lock.setVisibility(View.VISIBLE);
                if (position != lessons.size()-1) {
                    Glide.with(context)
                            .load(R.drawable.even_locked)
                            .into(evenViewHolder.arrow);
                }else{
                    evenViewHolder.arrow.setVisibility(View.GONE);
                }
                evenViewHolder.lesson.setAlpha(0.3f);

            }else{
                if (position != lessons.size()-1) {
                    Glide.with(context)
                            .load(R.drawable.even_unlocked)
                            .into(evenViewHolder.arrow);
                }else{
                    evenViewHolder.arrow.setVisibility(View.GONE);
                }
            }
        }else{
            LessonOddViewHolder oddViewHolder = (LessonOddViewHolder) holder;
            final int resourceId = context.getResources().getIdentifier(lessons.get(position).LessonImage, "drawable", context.getPackageName());
            Glide.with(context)
                    .load(resourceId)
                    .into(oddViewHolder.lesson);
            int count = pref.getInt("LessonCount",-1);
            if(position>=count){

                oddViewHolder.lock.setVisibility(View.VISIBLE);

                if (position != lessons.size()-1) {
                    Glide.with(context)
                            .load(R.drawable.odd_locked)
                            .into(oddViewHolder.arrow);
                }else{
                    oddViewHolder.arrow.setVisibility(View.GONE);
                }

                oddViewHolder.lesson.setAlpha(0.3f);

            }else{
                if (position != lessons.size()-1) {
                    Glide.with(context)
                            .load(R.drawable.odd_unlocked)
                            .into(oddViewHolder.arrow);
                }else{
                    oddViewHolder.arrow.setVisibility(View.GONE);
                }
            }

        }
    }

    @Override
    public int getItemCount() {
        return lessons.size();
    }

    static class LessonEvenViewHolder extends RecyclerView.ViewHolder{
        ImageView lesson;
        ImageView arrow;
        ImageView lock;
        public LessonEvenViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson = itemView.findViewById(R.id.even_lesson_image);
            arrow = itemView.findViewById(R.id.even_arrow);
            lock = itemView.findViewById(R.id.even_lesson_lock);
            lesson.setOnClickListener(view -> {
                int count = pref.getInt("LessonCount",-1);
                if (getAdapterPosition()<count) {
                    lessonItemClickListener.onLessonItemClick(getAdapterPosition());
                }
            });
        }
    }

    static class LessonOddViewHolder extends RecyclerView.ViewHolder{

        ImageView lesson;
        ImageView arrow;
        ImageView lock;
        public LessonOddViewHolder(@NonNull View itemView) {
            super(itemView);
            lesson = itemView.findViewById(R.id.odd_lesson_image);
            arrow = itemView.findViewById(R.id.odd_arrow);
            lock = itemView.findViewById(R.id.odd_lesson_lock);
            lesson.setOnClickListener(view -> {
                int count = pref.getInt("LessonCount",-1);
                if (getAdapterPosition()<count) {
                    lessonItemClickListener.onLessonItemClick(getAdapterPosition());
                }
            });
        }
    }
}
