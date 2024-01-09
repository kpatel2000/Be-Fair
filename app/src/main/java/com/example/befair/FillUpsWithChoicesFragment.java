package com.example.befair;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.befair.databinding.FragmentFillUpsWithChoicesBinding;
import com.example.befair.databinding.FragmentJumbledQuizBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kotlin.text.Charsets;

public class FillUpsWithChoicesFragment extends Fragment implements ChoiceAdapter.OnChoiceClicked, OnOkClickListener{

    FragmentFillUpsWithChoicesBinding binding;
    String lessonName;
    ArrayList<ChoiceModel> selectedSequence = new ArrayList<>();
    CountDownTimer ct;
    ArrayList<FillUpsQuizWithChoicesContent> listOfQuizes;
    boolean first = true;
    int index = 0;
    private String answer;
    long timerTime = 60000;
    long timeLeft = 0L;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFillUpsWithChoicesBinding.inflate(inflater, container, false);
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
        getChoicesList();
        showQuestion();
        showAdapter();
        //Verify answer and show next quiz

        binding.nextQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedSequence.sort(new Comparator<ChoiceModel>() {
                    @Override
                    public int compare(ChoiceModel choiceModel, ChoiceModel t1) {
                        return choiceModel.index - t1.index;
                    }
                });
                for (int start=0;start<selectedSequence.size();start++){
                    if (!selectedSequence.get(start).choice.equals(listOfQuizes.get(0).choices.get(start))){
                        Toast.makeText(requireContext(),"Incorrect Sequence, Please Check Again!",Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                ct.cancel();
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(view).navigate(R.id.action_fillUpsQuizWithChoicesFragment_to_quizPassFragment,args);
            }
        });

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
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(view).navigate(R.id.action_fillUpsQuizWithChoicesFragment_to_quizFailFragment,args);
            }
        }.start();

        binding.quizBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitDialog cd = new ExitDialog(getContext(),FillUpsWithChoicesFragment.this);
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
    }

    private void showAdapter() {
        ChoiceAdapter adapter = new ChoiceAdapter(requireContext(),getRandomChoicesList(),this);
        binding.choicesGv.setAdapter(adapter);
    }

    private List<ChoiceModel> getRandomChoicesList() {
        ArrayList<ChoiceModel> randomChoices = new ArrayList<>();

        if (first) {
            ArrayList<String> temp = new ArrayList<>(listOfQuizes.get(0).choices);
            for (int start=0;start<listOfQuizes.get(0).choices.size();start++){
                int index = (int)(Math.random() * temp.size());
                randomChoices.add(new ChoiceModel(temp.get(index)));
                temp.remove(temp.get(index));
            }
        }else {
            ArrayList<String> temp = new ArrayList<>(listOfQuizes.get(index).choices);
            for (int start=0;start<listOfQuizes.get(index).choices.size();start++){
                int index = (int)(Math.random() * temp.size());
                randomChoices.add(new ChoiceModel(temp.get(index)));
                temp.remove(temp.get(index));
            }
        }
        return randomChoices;
    }

    private void showQuestion() {
        if (first){
            binding.quizQuestion.setText(listOfQuizes.get(0).question);
            answer = listOfQuizes.get(0).correctAnswer;
        }else {
            binding.quizQuestion.setText(listOfQuizes.get(index).question);
            answer = listOfQuizes.get(index).correctAnswer;
        }
    }

    private void getChoicesList() {
        String fileName = "FillUpsQuizWithChoices.json";
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
            FillUpsQuizWithChoicesModel fillUpsQuizWithChoicesModel = gson.fromJson(jsonString, FillUpsQuizWithChoicesModel.class);
            listOfQuizes = new ArrayList<>();
            for (int start=0;start<fillUpsQuizWithChoicesModel.fillUpsQuizWithChoices.size();start++){
                if (fillUpsQuizWithChoicesModel.fillUpsQuizWithChoices.get(start).LessonName.equals(lessonName)){
                    listOfQuizes = fillUpsQuizWithChoicesModel.fillUpsQuizWithChoices.get(start).quizes;
                    break;
                }
            }
            /*binding.quizQuestion.setText(listOfQuizes.get(0).question);
            answer = listOfQuizes.get(0).correctAnswer;
            ArrayList<ChoiceModel> randomChoices = new ArrayList<>();
            ArrayList<String> temp = new ArrayList<>(listOfQuizes.get(0).choices);
            for (int start=0;start<listOfQuizes.get(0).choices.size();start++){
                int index = (int)(Math.random() * temp.size());
                randomChoices.add(new ChoiceModel(temp.get(index)));
                temp.remove(temp.get(index));
            }
            return randomChoices;*/
        }catch (Exception e){
            e.printStackTrace();
//            return null;
        }
    }

    @Override
    public void onClick(ChoiceModel choiceModel, boolean isRemoved, int nextSelection) {
        /*if (nextSelection > 12){
            binding.nextSelection.setVisibility(View.GONE);
        }else {
            binding.nextSelection.setVisibility(View.VISIBLE);
            binding.nextSelection.setText("Next Choice to be selected : "+nextSelection);
        }
        if (!isRemoved){
            selectedSequence.add(choiceModel);
        }else {
            selectedSequence.remove(choiceModel);
        }*/
        if (choiceModel.choice.equals(answer)){
            first = false;
            ct.cancel();
            index++;
            if (index<10){
                increaseTimer();
                showQuestion();
                showAdapter();
            }else {
                Bundle args = new Bundle();
                args.putString("LessonName", lessonName);
                Navigation.findNavController(getView()).navigate(R.id.action_fillUpsQuizWithChoicesFragment_to_quizPassFragment, args);
            }
        }else {
            Toast.makeText(requireContext(), "Incorrect selection!", Toast.LENGTH_SHORT).show();
        }
    }

    private void increaseTimer() {
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
                Navigation.findNavController(getView()).navigate(R.id.action_fillUpsQuizWithChoicesFragment_to_quizFailFragment,args);
            }
        }.start();
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
        Navigation.findNavController(getView()).navigate(R.id.action_fillUpsWithChoicesFragment_to_quizTimeFragment,args);
    }
}

