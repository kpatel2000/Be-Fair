package com.example.befair;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity{

    ActivityMainBinding binding;
    SharedPreferences pref;
//    MediaPlayer music;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        setBackground();
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        boolean firstTime = pref.getBoolean("FirstTimeLoggedIn",true);
        if (firstTime) {
            setupSharedPref();
        }

//        music = MediaPlayer.create(MainActivity.this, R.raw.music);
//        music.start();
        Intent intent = new Intent(MainActivity.this,BackgroundMusicService.class);
        intent.putExtra("From", "MainActivity");
        startService(intent);
    }

    private void setupSharedPref() {
        pref = getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("LessonCount",1);
        editor.putBoolean("FirstTimeLoggedIn",false);
        editor.commit();
    }

    private void setBackground() {
        Glide.with(this)
                .asGif()
                .load(R.drawable.background)
                .into(binding.mainBackground);
    }

//    @Override
//    public void onMusicClick(boolean volume) {
//        if(volume){
//            music.stop();
//        }else{
//            music.start();
//        }
//    }


    @Override
    protected void onStop() {
        super.onStop();
        stopService(new Intent(MainActivity.this, BackgroundMusicService.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Intent intent = new Intent(MainActivity.this,BackgroundMusicService.class);
        intent.putExtra("From", "MainActivity");
        startService(intent);
    }
}