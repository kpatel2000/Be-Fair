package com.example.befair;

import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.befair.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    FragmentHomeBinding binding;
    boolean volume = true;

//    private static onMusicClickListener musicClickListener;

    public HomeFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.homeLessonBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_lessonFragment);
        });

        binding.homeAboutUsBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_aboutUsFragment);
        });
        binding.homeReferencesBtn.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_referenceFragment);
        });

        binding.homeExitBtn.setOnClickListener(v -> {
            getActivity().finish();
            System.exit(0);
        });
        binding.settingsLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MusicDialog musicDialog = new MusicDialog(requireContext());
                musicDialog.show();
            }
        });

//        binding.music.setOnClickListener(v -> {
//            if(volume){
//                binding.music.setImageResource(R.drawable.mute);
//                musicClickListener.onMusicClick(volume);
//                volume = false;
//            }else {
//                binding.music.setImageResource(R.drawable.volume);
//                musicClickListener.onMusicClick(volume);
//                volume = true;
//
//            }
//        });
    }
}

interface onMusicClickListener{
    void onMusicClick(boolean volume);
}