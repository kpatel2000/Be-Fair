package com.example.befair;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.befair.databinding.FragmentMatchingTypeQuizBinding;
import com.example.befair.databinding.FragmentQuizFailBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kotlin.text.Charsets;

public class MatchingTypeQuizFragment extends Fragment{
    public MatchingTypeQuizFragment() {
    }

    FragmentMatchingTypeQuizBinding binding;
    String lessonName;

    List<ColumnModel> columnList;
    ArrayList<MatchingTypeQuizContent> matchingTypeQuizContentsList;
    CountDownTimer ct;
    ColumnAdapter adapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMatchingTypeQuizBinding.inflate(inflater, container, false);
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
        columnList = getListData();
        binding.quizMatchingRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ColumnAdapter(requireContext(),columnList);
        binding.quizMatchingRecView.setAdapter(adapter);
        ct = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                binding.quizTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Timer finished, you can perform any actions here
                Bundle args = new Bundle();
                args.putString("LessonName",lessonName);
                Navigation.findNavController(view).navigate(R.id.action_matchingTypeQuizFragment_to_quizFailFragment,args);
            }
        }.start();
        binding.quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isAllCorrect = true;
                columnList = adapter.columnModelList;
                for (int start=0;start<matchingTypeQuizContentsList.get(0).correctAnswerList.size();start++){
                    if (!matchingTypeQuizContentsList.get(0).correctAnswerList.get(start).equals(columnList.get(start).answer)){
                        isAllCorrect = false;
                    }
                }
                if (isAllCorrect){
                    ct.cancel();
                    Bundle args = new Bundle();
                    args.putString("LessonName",lessonName);
                    Navigation.findNavController(view).navigate(R.id.action_matchingTypeQuizFragment_to_quizPassFragment,args);
                }else{
                    Toast.makeText(requireContext(), "Incorrect Sequence!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private List<ColumnModel> getListData() {
        String fileName = "MatchingType.json";
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
            MatchingTypeQuizModel matchingTypeQuizModel = gson.fromJson(jsonString, MatchingTypeQuizModel.class);
            matchingTypeQuizContentsList = new ArrayList<>();
            for (int start = 0; start < matchingTypeQuizModel.matchingTypeQuiz.size(); start++) {
                if (matchingTypeQuizModel.matchingTypeQuiz.get(start).LessonName.equals(lessonName)) {
                    matchingTypeQuizContentsList = matchingTypeQuizModel.matchingTypeQuiz.get(start).quizes;
                    break;
                }
            }
            Collections.shuffle(matchingTypeQuizContentsList);
            ArrayList<ColumnModel> columnModelList = new ArrayList<>();
            for (int start = 0; start < matchingTypeQuizContentsList.get(0).columnAList.size(); start++) {
                columnModelList.add(new ColumnModel("", matchingTypeQuizContentsList.get(0).columnAList.get(start), matchingTypeQuizContentsList.get(0).columnBList.get(start)));
            }
            return columnModelList;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        /*model.add(new ColumnModel("", "1. afa", "a. egs"));
        model.add(new ColumnModel("", "2. afa", "b. egs"));
        model.add(new ColumnModel("", "3. afa", "c. egs"));
        model.add(new ColumnModel("", "4. afa", "d. egs"));
        model.add(new ColumnModel("", "5. afa", "e. egs"));
        model.add(new ColumnModel("", "6. afa", "f. egs"));
        model.add(new ColumnModel("", "7. afa", "g. egs"));
        model.add(new ColumnModel("", "8. afa", "h. egs"));
        model.add(new ColumnModel("", "9. afa", "i. egs"));
        model.add(new ColumnModel("", "10. afa", "j. egs"));
        return model;*/
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
}