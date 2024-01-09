package com.example.befair;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.befair.databinding.FragmentJumbledQuizBinding;
import com.example.befair.databinding.FragmentQuizBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;

public class JumbledQuizFragment extends Fragment implements OnOkClickListener{


    FragmentJumbledQuizBinding binding;
    String lessonName;
    boolean first5 = true;
    long timerTime = 60000;
    CountDownTimer ct;
    long timeLeft = 0L;
    ClueDialog cd;
    private boolean isAnsweredOne=false;
    private boolean isAnsweredTwo=false;
    private boolean isAnsweredThree=false;
    private boolean isAnsweredFour=false;
    private boolean isAnsweredFive=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentJumbledQuizBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        lessonName = getArguments().getString("LessonName");
        Intent intent = new Intent(requireContext(),BackgroundMusicService.class);
        requireContext().stopService(intent);
        intent.putExtra("From", "Quiz");
        requireContext().startService(intent);
        binding.quizLessonName.setText(lessonName);
        ArrayList<JumbledQuizContent> quizes = getListOfQuizes();

        showFirst5Questions(quizes);
        ct = new CountDownTimer(timerTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                timeLeft = millisUntilFinished;
                binding.quizTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Timer finished, you can perform any actions here
                if (cd != null){
                    cd.dismiss();
                }
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(getView()).navigate(R.id.action_jumbledQuizFragment_to_quizFailFragment,args);
            }
        }.start();
        binding.nextQuestions.setOnClickListener(view1 -> {
            binding.quizAnswer5.clearFocus();
            /*if (validateAnswer(quizes,first5) && first5){
                binding.quizAnswer1.getText().clear();
                binding.quizAnswer2.getText().clear();
                binding.quizAnswer3.getText().clear();
                binding.quizAnswer4.getText().clear();
                binding.quizAnswer5.getText().clear();
                isAnsweredOne=false;
                isAnsweredTwo=false;
                isAnsweredThree=false;
                isAnsweredFour=false;
                isAnsweredFive=false;
                increaseTimer(view);
                showNext5Questions(quizes);
            }else if (validateAnswer(quizes,first5) && !first5){
                Toast.makeText(requireContext(),"Pass",Toast.LENGTH_SHORT).show();
                ct.cancel();
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(view).navigate(R.id.action_jumbledQuizFragment_to_quizPassFragment,args);
            }*/
            if (isAnsweredOne && isAnsweredTwo && isAnsweredThree && isAnsweredFour && isAnsweredFive && first5){
                binding.quizAnswer1.getText().clear();
                binding.quizAnswer2.getText().clear();
                binding.quizAnswer3.getText().clear();
                binding.quizAnswer4.getText().clear();
                binding.quizAnswer5.getText().clear();
                isAnsweredOne=false;
                isAnsweredTwo=false;
                isAnsweredThree=false;
                isAnsweredFour=false;
                isAnsweredFive=false;
                increaseTimer(view);
                showNext5Questions(quizes);
            }else if (isAnsweredOne && isAnsweredTwo && isAnsweredThree && isAnsweredFour && isAnsweredFive && !first5){
                Toast.makeText(requireContext(),"Pass",Toast.LENGTH_SHORT).show();
                ct.cancel();
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                if (lessonName.equals("Legal Mandate in the use of Gender-Fair Language")) {
                    Navigation.findNavController(view).navigate(R.id.action_jumbledQuizFragment_to_congratsFragment);
                }else{
                    Navigation.findNavController(view).navigate(R.id.action_jumbledQuizFragment_to_quizPassFragment,args);
                }
            }
        });

        binding.quizBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitDialog cd = new ExitDialog(getContext(),JumbledQuizFragment.this);
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.clueQuestion1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first5) {
                    cd = new ClueDialog(requireContext(), quizes.get(0).clue);
                }else {
                    cd = new ClueDialog(requireContext(), quizes.get(5).clue);
                }
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.clueQuestion2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first5) {
                    cd = new ClueDialog(requireContext(), quizes.get(1).clue);
                }else {
                    cd = new ClueDialog(requireContext(), quizes.get(6).clue);
                }
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.clueQuestion3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first5) {
                    cd = new ClueDialog(requireContext(), quizes.get(2).clue);
                }else {
                    cd = new ClueDialog(requireContext(), quizes.get(7).clue);
                }
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.clueQuestion4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first5) {
                    cd = new ClueDialog(requireContext(), quizes.get(3).clue);
                }else {
                    cd = new ClueDialog(requireContext(), quizes.get(8).clue);
                }
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                cd.show();
            }
        });
        binding.clueQuestion5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (first5) {
                    cd = new ClueDialog(requireContext(), quizes.get(4).clue);
                }else {
                    cd = new ClueDialog(requireContext(), quizes.get(9).clue);
                }
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.quizAnswer1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (first5 && !isAnsweredOne) {
                        if (!binding.quizAnswer1.getText().toString().trim().equalsIgnoreCase(quizes.get(0).correctAnswer.toLowerCase())) {
                            binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredOne = true;
                            binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    } else if (!isAnsweredOne) {
                        if (!binding.quizAnswer1.getText().toString().trim().equalsIgnoreCase(quizes.get(5).correctAnswer.toLowerCase())) {
                            binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredOne = true;
                            binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    }
                }
            }
        });
        binding.quizAnswer2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (first5 && isAnsweredOne && !isAnsweredTwo) {
                        if (!binding.quizAnswer2.getText().toString().trim().equalsIgnoreCase(quizes.get(1).correctAnswer.toLowerCase())) {
                            binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredTwo=true;
                            binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    } else if (isAnsweredOne && !isAnsweredTwo) {
                        if (!binding.quizAnswer2.getText().toString().trim().equalsIgnoreCase(quizes.get(6).correctAnswer.toLowerCase())) {
                            binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredTwo=true;
                            binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    }
                }
            }
        });
        binding.quizAnswer3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (first5 && isAnsweredOne && isAnsweredTwo && !isAnsweredThree) {
                        if (!binding.quizAnswer3.getText().toString().trim().equalsIgnoreCase(quizes.get(2).correctAnswer.toLowerCase())) {
                            binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredThree=true;
                            binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    } else if (isAnsweredOne && isAnsweredTwo && !isAnsweredThree) {
                        if (!binding.quizAnswer3.getText().toString().trim().equalsIgnoreCase(quizes.get(7).correctAnswer.toLowerCase())) {
                            binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredThree=true;
                            binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    }
                }
            }
        });
        binding.quizAnswer4.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (first5 && isAnsweredOne && isAnsweredTwo && isAnsweredThree && !isAnsweredFour) {
                        if (!binding.quizAnswer4.getText().toString().trim().equalsIgnoreCase(quizes.get(3).correctAnswer.toLowerCase())) {
                            binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredFour=true;
                            binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    } else if (isAnsweredOne && isAnsweredTwo && isAnsweredThree && !isAnsweredFour) {
                        if (!binding.quizAnswer4.getText().toString().trim().equalsIgnoreCase(quizes.get(8).correctAnswer.toLowerCase())) {
                            binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredFour=true;
                            binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    }
                }
            }
        });
        binding.quizAnswer5.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus){
                    if (first5 && isAnsweredOne && isAnsweredTwo && isAnsweredThree && isAnsweredFour && !isAnsweredFive) {
                        if (!binding.quizAnswer5.getText().toString().trim().equalsIgnoreCase(quizes.get(4).correctAnswer.toLowerCase())) {
                            binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredFive=true;
                            binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                            increaseTimer(view);
                        }
                    } else if (isAnsweredOne && isAnsweredTwo && isAnsweredThree && isAnsweredFour && !isAnsweredFive) {
                        if (!binding.quizAnswer5.getText().toString().trim().equalsIgnoreCase(quizes.get(9).correctAnswer.toLowerCase())) {
                            binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_wrong_answer));
                        } else {
                            isAnsweredFive=true;
                            binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(), R.drawable.bg_answer));
                        }
                    }
                }
            }
        });
    }

    private void increaseTimer(View view) {
        ct.cancel();
        timeLeft += 2000;
        ct = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                timeLeft = millisUntilFinished;
                binding.quizTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Timer finished, you can perform any actions here
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(view).navigate(R.id.action_jumbledQuizFragment_to_quizFailFragment,args);
            }
        }.start();
    }

    private void showNext5Questions(ArrayList<JumbledQuizContent> quizes) {
        first5 = false;
        binding.quizQuestion1.setText(quizes.get(5).jumbledLetter);
        binding.quizQuestion2.setText(quizes.get(6).jumbledLetter);
        binding.quizQuestion3.setText(quizes.get(7).jumbledLetter);
        binding.quizQuestion4.setText(quizes.get(8).jumbledLetter);
        binding.quizQuestion5.setText(quizes.get(9).jumbledLetter);
    }

    private boolean validateAnswer(ArrayList<JumbledQuizContent> quizes, boolean first5) {
        boolean isAllCorrect = true;
        if (first5) {
            if (!binding.quizAnswer1.getText().toString().trim().equals(quizes.get(0).correctAnswer)) {
                binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer2.getText().toString().trim().equals(quizes.get(1).correctAnswer)) {
                binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer3.getText().toString().trim().equals(quizes.get(2).correctAnswer)) {
                binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer4.getText().toString().trim().equals(quizes.get(3).correctAnswer)) {
                binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer5.getText().toString().trim().equals(quizes.get(4).correctAnswer)) {
                binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
        }else{
            if (!binding.quizAnswer1.getText().toString().trim().equals(quizes.get(5).correctAnswer)) {
                binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer1.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer2.getText().toString().trim().equals(quizes.get(6).correctAnswer)) {
                binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer2.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer3.getText().toString().trim().equals(quizes.get(7).correctAnswer)) {
                binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer3.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer4.getText().toString().trim().equals(quizes.get(8).correctAnswer)) {
                binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer4.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
            if (!binding.quizAnswer5.getText().toString().trim().equals(quizes.get(9).correctAnswer)) {
                binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_wrong_answer));
                isAllCorrect = false;
            }else{
                binding.quizAnswer5.setBackground(AppCompatResources.getDrawable(requireContext(),R.drawable.bg_answer));
            }
        }
        return isAllCorrect;
    }

    private void showFirst5Questions(ArrayList<JumbledQuizContent> quizes) {
        binding.quizQuestion1.setText(quizes.get(0).jumbledLetter);
        binding.quizQuestion2.setText(quizes.get(1).jumbledLetter);
        binding.quizQuestion3.setText(quizes.get(2).jumbledLetter);
        binding.quizQuestion4.setText(quizes.get(3).jumbledLetter);
        binding.quizQuestion5.setText(quizes.get(4).jumbledLetter);
    }

    private ArrayList<JumbledQuizContent> getListOfQuizes() {
        String fileName = "JumbledQuiz.json";
        try {
            //For accessing JSON file from asset folder
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            String jsonString = new String(buffer, Charsets.UTF_8);
            Gson gson = new Gson();
            JumbledQuizModel jumbledQuizModel = gson.fromJson(jsonString, JumbledQuizModel.class);
            ArrayList<JumbledQuizContent> listOfQuizes = new ArrayList<>();
            for (int start=0;start<jumbledQuizModel.jumbledQuizes.size();start++){
                if (jumbledQuizModel.jumbledQuizes.get(start).LessonName.equals(lessonName)){
                    listOfQuizes = jumbledQuizModel.jumbledQuizes.get(start).quizes;
                    break;
                }
            }
            ArrayList<JumbledQuizContent> listOfQuiz = new ArrayList<>();
            for (int start=0;start<10;start++){
                int index = (int)(Math.random() * listOfQuizes.size());
                listOfQuiz.add(listOfQuizes.get(index));
                listOfQuizes.remove(listOfQuizes.get(index));
            }
            return listOfQuiz;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (ct != null){
            ct.cancel();
        }
        Intent intent = new Intent(requireContext(),BackgroundMusicService.class);
        requireContext().stopService(intent);
        intent.putExtra("From", "MainActivity");
        requireContext().startService(intent);
    }

    @Override
    public void onOkCLick() {
        Bundle args = new Bundle();
        args.putString("LessonName",lessonName);
        Navigation.findNavController(getView()).navigate(R.id.action_jumbledQuizFragment_to_quizTimeFragment,args);
    }
}