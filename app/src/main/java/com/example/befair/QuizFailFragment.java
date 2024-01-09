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
import com.example.befair.databinding.FragmentQuizBinding;
import com.example.befair.databinding.FragmentQuizFailBinding;

public class QuizFailFragment extends Fragment {
    public QuizFailFragment() {
    }

    FragmentQuizFailBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizFailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String lessonName = getArguments().getString("LessonName");

        binding.quizFailRetakeQuiz.setOnClickListener(view1 -> {
            Bundle args = new Bundle();
            args.putString("LessonName",lessonName);
            Navigation.findNavController(view1).navigate(R.id.action_quizFailFragment_to_quizRuleFragment,args);
        });

        binding.quizFailHome.setOnClickListener(view2 -> {
            Navigation.findNavController(view2).navigate(R.id.action_quizFailFragment_to_homeFragment);
        });
    }
}