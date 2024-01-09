package com.example.befair;

import android.content.res.AssetManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.befair.databinding.FragmentLessonBinding;
import com.example.befair.databinding.FragmentTopicBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;

public class TopicFragment extends Fragment implements onTopicItemClickListener{
    public TopicFragment() {
    }

    FragmentTopicBinding binding;
    List<Contents> topics;
    String lessonName;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTopicBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonName = getArguments().getString("LessonName");
        topics = getTopicsData();
        binding.topicLessonTitle.setText(lessonName);
        binding.topicRecView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.topicRecView.setAdapter(new TopicAdapter(getContext(), topics,this));

        binding.topicBack.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_topicFragment_to_lessonFragment);
        });

        binding.topicHome.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_topicFragment_to_homeFragment);
        });
    }

    private List<Contents> getTopicsData(){
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
            for (int start=0;start<lessonModel.lessons.size();start++){
                if (lessonModel.lessons.get(start).LessonName.equals(lessonName)){
                    return lessonModel.lessons.get(start).content;
                }
            }
            return null;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
//        List<ItemTopic> newTopicList = new ArrayList<>();
//        newTopicList.add(new ItemTopic("Republic Act Number 1970"));
//        newTopicList.add(new ItemTopic("Civil Services Commission"));
//        newTopicList.add(new ItemTopic("Commission on Higher Education"));
//        return newTopicList;
    }

    @Override
    public void onTopicItemClick(int position) {
        Bundle args = new Bundle();
        args.putInt("TopicPosition", position);
        args.putString("LessonName",lessonName);
        Navigation.findNavController(getView()).navigate(R.id.action_topicFragment_to_detailFragment,args);
    }
}
interface onTopicItemClickListener{
    void onTopicItemClick(int position);
}