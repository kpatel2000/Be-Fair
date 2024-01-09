package com.example.befair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.ActivityMainBinding;
import com.example.befair.databinding.ActivitySplashBinding;

public class SplashActivity extends AppCompatActivity {

    ActivitySplashBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        setBackground();
        new Handler().postDelayed(()->{
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        },3000);
    }

    private void setBackground() {
        Glide.with(this)
                .asGif()
                .load(R.drawable.background)
                .into(binding.splashBackground);
    }
}