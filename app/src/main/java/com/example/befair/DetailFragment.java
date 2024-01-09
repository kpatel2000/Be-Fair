package com.example.befair;

import android.content.Intent;
import android.content.res.AssetManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.Toast;

import com.example.befair.databinding.FragmentDetailBinding;
import com.example.befair.databinding.FragmentLessonBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import kotlin.text.Charsets;

public class DetailFragment extends Fragment {
    public DetailFragment() {
    }

    FragmentDetailBinding binding;
    List<Contents> lessonDetails;
    String lessonName;
    boolean videoPlaying = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentDetailBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        lessonName = getArguments().getString("LessonName");
        lessonDetails = getLessonDetailData();
        /*if (lessonName.equals("Sexism in Language")){
            Intent intent = new Intent(requireContext(),BackgroundMusicService.class);
            requireContext().stopService(intent);
            binding.videoView.setVisibility(View.VISIBLE);
            String path = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.video;
            binding.videoView.setVideoURI(Uri.parse(path));
            MediaController mediaController = new MediaController(requireContext());
            mediaController.setAnchorView(binding.videoView);
            mediaController.setMediaPlayer(binding.videoView);
            binding.videoView.setMediaController(mediaController);
            binding.videoView.start();
        }else {
            binding.videoView.setVisibility(View.GONE);
        }*/
        LessonDetailAdapter lessonDetailAdapter = new LessonDetailAdapter(lessonDetails, requireContext());
        binding.detailViewPager.setAdapter(lessonDetailAdapter);
        binding.detailViewPager.setClipToPadding(false);
        binding.detailViewPager.setClipChildren(false);
        binding.detailViewPager.setOffscreenPageLimit(1);
        binding.detailViewPager.getChildAt(0).setOverScrollMode(View.OVER_SCROLL_NEVER);
//        binding.detailViewPager.setCurrentItem(position);
        binding.detailBack.setOnClickListener(view1 -> {
            if (binding.detailViewPager.getCurrentItem() == 0) {
                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_lessonFragment);
            } else {
                binding.detailViewPager.setCurrentItem(binding.detailViewPager.getCurrentItem() - 1);
            }
        });
        binding.detailViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                if (position != 0) {
                    if (lessonName.equals("Sexism in Language") && videoPlaying) {
                        videoPlaying = false;
                        binding.videoView.stopPlayback();
                        Intent intent = new Intent(requireContext(), BackgroundMusicService.class);
                        intent.putExtra("From", "MainActivity");
                        requireContext().startService(intent);
                    }
                    binding.videoView.setVisibility(View.GONE);
                } else {
                    if (lessonName.equals("Sexism in Language")) {
                        videoPlaying = true;
                        Intent intent = new Intent(requireContext(), BackgroundMusicService.class);
                        requireContext().stopService(intent);
                        binding.videoView.setVisibility(View.VISIBLE);
                        String path = "android.resource://" + requireContext().getPackageName() + "/" + R.raw.video;
                        binding.videoView.setVideoURI(Uri.parse(path));
                        /*MediaController mediaController = new MediaController(requireContext());
                        binding.videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                            @Override
                            public void onPrepared(MediaPlayer mediaPlayer) {
                                mediaPlayer.setOnVideoSizeChangedListener(new MediaPlayer.OnVideoSizeChangedListener() {
                                    @Override
                                    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i1) {
                                        mediaController.setAnchorView(binding.videoView);
                                        mediaController.setMediaPlayer(binding.videoView);
                                    }
                                });
                            }
                        });
                        binding.videoView.setMediaController(mediaController);*/
                        binding.videoView.start();
                    } else {
                        binding.videoView.setVisibility(View.GONE);
                    }
                }
                if (position == lessonDetails.size() - 1) {
                    binding.detailNext.setVisibility(View.VISIBLE);
                    if (lessonName.equals("Legal Mandate in the use of Gender-Fair Language")) {
                        binding.referenceLink.setVisibility(View.GONE);
                    } else if (lessonName.equals("Sexism in Language")) {
                        binding.referenceLink.setVisibility(View.VISIBLE);
                        binding.referenceLink.setText("https://www.youtube.com/watch?v=TF9qhSNDQL0");
                    } else if (lessonName.equals("Guidelines for Non-Sexist Writing")) {
                        binding.referenceLink.setVisibility(View.VISIBLE);
                        binding.referenceLink.setText("https://www.youtube.com/watch?v=4nHQ57KYj_w");
                    } else if (lessonName.equals("Sexism in Image or Other Literature")) {
                        binding.referenceLink.setVisibility(View.VISIBLE);
                        ConstraintLayout.LayoutParams params = (ConstraintLayout.LayoutParams) binding.referenceLink.getLayoutParams();
                        params.setMargins(params.leftMargin,params.topMargin,params.rightMargin,params.bottomMargin-60);
                        binding.referenceLink.setText("https://www.youtube.com/watch?v=Ulh0DnFUGsk");
                    } else if (lessonName.equals("Gender-Fair language in the Vulnerable Sectors: PWD")) {
                        binding.referenceLink.setVisibility(View.VISIBLE);
                        binding.referenceLink.setText("https://www.youtube.com/watch?v=Q4YjIx2-l2A");
                    } else {
                        binding.referenceLink.setVisibility(View.GONE);
                    }
//                    binding.view.setVisibility(View.VISIBLE);
                } else {
                    binding.detailNext.setVisibility(View.GONE);
                    binding.referenceLink.setVisibility(View.GONE);
//                    binding.view.setVisibility(View.GONE);
                }
            }
        });

        binding.detailHome.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_homeFragment);
        });

        binding.detailNext.setOnClickListener(view1 -> {
            if (binding.detailViewPager.getCurrentItem() == lessonDetails.size() - 1) {
                Bundle args = new Bundle();
                args.putString("LessonName", lessonName);
                Navigation.findNavController(view).navigate(R.id.action_detailFragment_to_quizTimeFragment, args);
            } else {
                binding.detailViewPager.setCurrentItem(binding.detailViewPager.getCurrentItem() + 1);
            }
        });

        binding.referenceLink.setOnClickListener(view12 -> {
            switch (lessonName) {
                case "Sexism in Language":
                    openReferenceLink("https://www.youtube.com/watch?v=TF9qhSNDQL0");
                    break;
                case "Guidelines for Non-Sexist Writing":
                    openReferenceLink("https://www.youtube.com/watch?v=4nHQ57KYj_w");
                    break;
                case "Sexism in Image or Other Literature":
                    openReferenceLink("https://www.youtube.com/watch?v=Ulh0DnFUGsk");
                    break;
                case "Gender-Fair language in the Vulnerable Sectors: PWD":
                    openReferenceLink("https://www.youtube.com/watch?v=Q4YjIx2-l2A");
                    break;
            }
        });
    }

    private void openReferenceLink(String url) {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        requireContext().startActivity(intent);
    }

    private List<Contents> getLessonDetailData() {
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
            for (int start = 0; start < lessonModel.lessons.size(); start++) {
                if (lessonModel.lessons.get(start).LessonName.equals(lessonName)) {
                    return lessonModel.lessons.get(start).content;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
//        List<ItemLessonDetail> newLessonDetailList = new ArrayList<>();
//
//        int [] lessonImage = {R.drawable.lesson_one, R.drawable.lesson_two, R.drawable.lesson_three};
//        String [] lessonHeading = {"Republic Act Number 1970", "Civil Services Commission", "Commission on Higher Education"};
//        for(int i=0; i<lessonImage.length; i++){
//            newLessonDetailList.add(new ItemLessonDetail(lessonHeading[i], lessonImage[i]));
//        }
//        return newLessonDetailList;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (lessonName.equals("Sexism in Language")) {
            Intent intent = new Intent(requireContext(), BackgroundMusicService.class);
            requireContext().stopService(intent);
            intent.putExtra("From", "MainActivity");
            requireContext().startService(intent);
        }
    }
}