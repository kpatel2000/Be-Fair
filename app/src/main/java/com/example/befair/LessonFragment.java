package com.example.befair;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.FragmentLessonBinding;
import com.google.gson.Gson;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;

public class LessonFragment extends Fragment implements onLessonItemClickListener {
    public LessonFragment() {
    }
//    List<ItemLesson> newLessonList = new ArrayList<>();
    FragmentLessonBinding binding;
    List<Lessons> lessons;
    Boolean isClicked=false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentLessonBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessons = getLessonData();

        binding.lessonRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.lessonRecView.setAdapter(new LessonAdapter(getContext(), lessons, this));

        binding.lessonBack.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_homeFragment);
        });

        binding.lessonHome.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_lessonFragment_to_homeFragment);
        });
    }

    private List<Lessons> getLessonData(){
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
            return lessonModel.lessons;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        newLessonList.add(new ItemLesson("Legal Mandate in the use of Gender-Fair Language", false));
//        newLessonList.add(new ItemLesson("Sexism in Language", true));
//        newLessonList.add(new ItemLesson("Guidelines for Non-Sexist Writing", true));
//        newLessonList.add(new ItemLesson("Sexism in Image or Other Literature", true));
//        newLessonList.add(new ItemLesson("Gender-Fair language in the Vulnerable Sectors: PWD", true));
//        newLessonList.add(new ItemLesson("Contextualizing Sexist Language", true));
//        newLessonList.add(new ItemLesson("Extra-Extra: Gay Lingo", true));
//        return newLessonList;
    }

    @Override
    public void onLessonItemClick(int position) {
        binding.lessonName.setText(lessons.get(position).LessonName);
        if (!isClicked) {
            isClicked = true;
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    isClicked = false;
                    Bundle args = new Bundle();
                    args.putString("LessonName", lessons.get(position).LessonName);
                    Navigation.findNavController(getView()).navigate(R.id.action_lessonFragment_to_detailFragment, args);
                }
            }, 1000);
        }
    }
}

interface onLessonItemClickListener{
    void onLessonItemClick(int position);
}