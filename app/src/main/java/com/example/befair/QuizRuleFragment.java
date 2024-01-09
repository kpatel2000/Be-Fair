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
import com.example.befair.databinding.FragmentTopicBinding;

public class QuizRuleFragment extends Fragment {
    public QuizRuleFragment() {
    }

    FragmentQuizRuleBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizRuleBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        String lessonName = getArguments().getString("LessonName");
        if (lessonName.equals("Legal Mandate in the use of Gender-Fair Language")) {
            binding.quizRuleRules.setText(R.string.jumble_rule);
        } else if (lessonName.equals("Sexism in Language")) {
            binding.quizRuleRules.setText(R.string.hangaroo_rule);
        }else if (lessonName.equals("Guidelines for Non-Sexist Writing")){
            binding.quizRuleRules.setText(R.string.fill_ups_rule);
        }else if (lessonName.equals("Sexism in Image or Other Literature")){
            binding.quizRuleRules.setText(R.string.matching_rule);
        } else if (lessonName.equals("Gender-Fair language in the Vulnerable Sectors: PWD")) {
            binding.quizRuleRules.setText(R.string.jumble_rule);
        }else{
            binding.quizRuleRules.setText(R.string.hangaroo_rule);
        }
        binding.quizRuleProceed.setOnClickListener(view1 ->{
            Bundle args = new Bundle();
            args.putString("LessonName",lessonName);
            if (lessonName.equals("Legal Mandate in the use of Gender-Fair Language")) {
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_jumbledQuizFragment,args);
            } else if (lessonName.equals("Sexism in Language")) {
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_quizFragment,args);
            }else if (lessonName.equals("Guidelines for Non-Sexist Writing")){
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_fillUpsQuizWithChoicesFragment,args);
            }else if (lessonName.equals("Sexism in Image or Other Literature")){
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_MatchingTypeQuizFragment,args);
            } else if (lessonName.equals("Gender-Fair language in the Vulnerable Sectors: PWD")) {
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_jumbledQuizFragment,args);
            }else{
                Navigation.findNavController(view).navigate(R.id.action_quizRuleFragment_to_quizFragment,args);
            }
        });

        Glide.with(this)
                .asGif()
                .load(R.drawable.guess)
                .into(binding.quizRuleImage);
    }
}