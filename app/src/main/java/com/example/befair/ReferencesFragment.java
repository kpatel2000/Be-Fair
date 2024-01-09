package com.example.befair;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.befair.databinding.FragmentAboutUsBinding;
import com.example.befair.databinding.FragmentReferencesBinding;

public class ReferencesFragment extends Fragment {

    FragmentReferencesBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentReferencesBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.referenceHome.setOnClickListener(view1 -> {
            Navigation.findNavController(view).navigate(R.id.action_referenceFragment_to_homeFragment);
        });
    }
}