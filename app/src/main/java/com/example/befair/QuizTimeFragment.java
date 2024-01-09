package com.example.befair;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.FragmentQuizRuleBinding;
import com.example.befair.databinding.FragmentQuizTimeBinding;

public class QuizTimeFragment extends Fragment {
    public QuizTimeFragment() {
    }

    FragmentQuizTimeBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizTimeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String lessonName = getArguments().getString("LessonName");
        binding.quizTimeBack.setOnClickListener(view1 ->{
            Bundle args = new Bundle();
            args.putString("LessonName",lessonName);
            Navigation.findNavController(view).navigate(R.id.action_quizTimeFragment_to_detailFragment,args);
        });

        binding.quizTimeHome.setOnClickListener(view1 ->{
            Navigation.findNavController(view).navigate(R.id.action_quizTimeFragment_to_homeFragment);
        });

        binding.quizTimeNext.setOnClickListener(view1 ->{
            Bundle args = new Bundle();
            args.putString("LessonName",lessonName);
            Navigation.findNavController(view).navigate(R.id.action_quizTimeFragment_to_quizRuleFragment,args);
        });

        Glide.with(this)
                .asGif()
                .load(R.drawable.quiz_time)
                .into(binding.quizTimeImage);

        Glide.with(this)
                .asGif()
                .load(R.drawable.guess)
                .into(binding.quizTimeGuess);
    }
}