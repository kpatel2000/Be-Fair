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
import com.example.befair.databinding.FragmentCongratsBinding;
import com.example.befair.databinding.FragmentDetailBinding;

public class CongratsFragment extends Fragment {
    public CongratsFragment() {
        // Required empty public constructor
    }

    FragmentCongratsBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCongratsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Glide.with(this)
                .asGif()
                .load(R.drawable.congratulations)
                .into(binding.congratsImage);

        binding.congratsContinue.setOnClickListener(v -> {
            Navigation.findNavController(view).navigate(R.id.action_congratsFragment_to_homeFragment);
        });
    }
}