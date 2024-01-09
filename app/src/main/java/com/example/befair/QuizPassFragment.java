package com.example.befair;

import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.FragmentQuizFailBinding;
import com.example.befair.databinding.FragmentQuizPassBinding;
import com.google.gson.Gson;

import java.io.InputStream;

import kotlin.text.Charsets;

public class QuizPassFragment extends Fragment {
    public QuizPassFragment() {
    }

    FragmentQuizPassBinding binding;
    String lessonName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizPassBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this)
                .asGif()
                .load(R.drawable.pass)
                .into(binding.quizPassImage);

        lessonName = getArguments().getString("LessonName");
        unlockNextLesson();
        binding.quizPassNextLesson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_quizPassFragment_to_lessonFragment);
            }
        });
    }

    private void unlockNextLesson() {
        String fileName = "Lessons.json";
        try {
            //For accessing JSON file from asset folder
            AssetManager assetManager = getResources().getAssets();
            InputStream inputStream = assetManager.open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            //Convert byte array to String
            String jsonString = new String(buffer, Charsets.UTF_8);
            Gson gson = new Gson();
            LessonModel lessonModel = gson.fromJson(jsonString, LessonModel.class);
            SharedPreferences pref = getContext().getSharedPreferences("MyPref",0);
            int count = pref.getInt("LessonCount",-1);
            if (lessonModel.lessons.get(count-1).LessonName.equals(lessonName) && count+1 < 7){
                count++;
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("LessonCount", count);
                editor.commit();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}