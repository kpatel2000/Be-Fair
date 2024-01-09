package com.example.befair;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.befair.databinding.FragmentQuizBinding;
import com.example.befair.databinding.FragmentQuizTimeBinding;
import com.google.gson.Gson;

import java.io.InputStream;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import kotlin.text.Charsets;

public class QuizFragment extends Fragment implements OnOkClickListener {
    private boolean quizCompleted = false;

    public QuizFragment() {
    }

    FragmentQuizBinding binding;
//    CountDownTimer ct;
    String lessonName;
//    long timerTime = 20000;
//    Long timeLeft = 0L;
    int incorrectAnswer = 0;
    char[] answer;
    List<QuizContent> quiz;
    char[] answerCharacter;
    int index=0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);
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
        getQuiz();
//        Queue<Character> answerCharacter = new LinkedList<>();
        setAnswer();
        /*for (char ch : answerArray){
            answerCharacter.add(ch);
        }*/
        /*if (!quiz.get(0).image.equals("")) {
            Glide.with(this)
                    .load(requireContext().getResources().getIdentifier(quiz.get(0).image, "drawable", requireContext().getPackageName()))
                    .circleCrop()
                    .into(binding.quizImage);
        }*/
        setQuestion();
        /*if (quiz != null) {
            binding.quizQuestion.setText(quiz.get(0).question);
        }*/
        /*ct = new CountDownTimer(timerTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                timeLeft = millisUntilFinished;
                binding.quizTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Timer finished, you can perform any actions here
                Bundle args = new Bundle();
                args.putString("LessonName", lessonName);
                Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
            }
        }.start();*/

        binding.quizBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ExitDialog cd = new ExitDialog(getContext(), QuizFragment.this);
                cd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cd.show();
            }
        });
        binding.a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("a", view, answerCharacter);
            }
        });
        binding.b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("b", view, answerCharacter);
            }
        });
        binding.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("c", view, answerCharacter);
            }
        });
        binding.d.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("d", view, answerCharacter);
            }
        });
        binding.e.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("e", view, answerCharacter);
            }
        });
        binding.f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("f", view, answerCharacter);
            }
        });
        binding.g.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("g", view, answerCharacter);
            }
        });
        binding.h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("h", view, answerCharacter);
            }
        });
        binding.i.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("i", view, answerCharacter);
            }
        });
        binding.j.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("j", view, answerCharacter);
            }
        });
        binding.k.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("k", view, answerCharacter);
            }
        });
        binding.l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("l", view, answerCharacter);
            }
        });
        binding.m.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("m", view, answerCharacter);
            }
        });
        binding.n.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("n", view, answerCharacter);
            }
        });
        binding.o.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("o", view, answerCharacter);
            }
        });
        binding.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("p", view, answerCharacter);
            }
        });
        binding.q.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("q", view, answerCharacter);
            }
        });
        binding.r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("r", view, answerCharacter);
            }
        });
        binding.s.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("s", view, answerCharacter);
            }
        });
        binding.t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("t", view, answerCharacter);
            }
        });
        binding.u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("u", view, answerCharacter);
            }
        });
        binding.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("v", view, answerCharacter);
            }
        });
        binding.w.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("w", view, answerCharacter);
            }
        });
        binding.x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("x", view, answerCharacter);
            }
        });
        binding.y.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("y", view, answerCharacter);
            }
        });
        binding.z.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCharacter("z", view, answerCharacter);
            }
        });
        binding.NNot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (answerCharacter.peek().equals("Ñ")) {
                    answerCharacter.poll();
                    if (answerCharacter.isEmpty()) {
                        quizCompleted = true;
                    }
                    incrementTimer("Ñ", view);
                } else {
                    decrementTimer(view);
                }*/
                if (answer == null){
                    answer = new char[answerCharacter.length];
                    for (int start=0;start<answerCharacter.length;start++){
                        answer[start] = '_';
                    }
                }else {
                    if (String.valueOf(answer).contains("Ñ")){
                        return;
                    }
                }
                boolean isContainCharacter = false;
                for (int start = 0; start < answerCharacter.length; start++) {
                    if (answerCharacter[start] == 'Ñ') {
                        isContainCharacter = true;
                        answer[start]=answerCharacter[start];
                        if (String.valueOf(answer).equals(quiz.get(index).correctAnswer)) {
                            quizCompleted = true;
                        }
                    }
                }
                if (isContainCharacter) {
                    binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.correct_character));
                    incrementTimer(String.valueOf(answer), view);
                } else {
                    binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.wrong_character));
                    decrementTimer(view);
                }
            }
        });

        binding.space.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*if (answerCharacter.peek().equals(' ')) {
                    answerCharacter.poll();
                    if (answerCharacter.isEmpty()) {
                        quizCompleted = true;
                    }
                    incrementTimer(" ", view);
                } else {
                    decrementTimer(view);
                }*/
                if (answer == null){
                    answer = new char[answerCharacter.length];
                    for (int start=0;start<answerCharacter.length;start++){
                        answer[start] = '_';
                    }
                }else {
                    if (String.valueOf(answer).contains(" ")){
                        return;
                    }
                }
                boolean isContainCharacter = false;
                for (int start = 0; start < answerCharacter.length; start++) {
                    if (answerCharacter[start] == ' ') {
                        isContainCharacter = true;
                        answer[start]=answerCharacter[start];
                        if (String.valueOf(answer).equals(quiz.get(index).correctAnswer)) {
                            quizCompleted = true;
                        }
                    }
                }
                if (isContainCharacter) {
                    binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.correct_character));
                    incrementTimer(String.valueOf(answer), view);
                } else {
                    binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.wrong_character));
                    decrementTimer(view);
                }
            }
        });

        binding.quizSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String answer = binding.quizAnswer.getText().toString();
                if (answer.trim().toLowerCase().equals(quiz.get(index).correctAnswer)) {
//                    ct.cancel();
                    Bundle args = new Bundle();
                    args.putString("LessonName", lessonName);
                    Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_quizPassFragment, args);
                } else {
                    Bundle args = new Bundle();
                    args.putString("LessonName", lessonName);
                    Navigation.findNavController(view).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
                }
            }
        });
    }

    private void setQuestion() {
        binding.quizQuestion.setText(quiz.get(index).question);
    }

    private void setAnswer() {
        answer = null;
        quizCompleted=false;
        answerCharacter = quiz.get(index).correctAnswer.toCharArray();
    }

    private void checkCharacter(String character, View view, char[] answerCharacter) {
        if (answer == null){
            answer = new char[answerCharacter.length];
            for (int start=0;start<answerCharacter.length;start++){
                answer[start] = '_';
            }
        }else {
            if (String.valueOf(answer).contains(character)){
                return;
            }
        }
        boolean isContainCharacter = false;
        for (int start = 0; start < answerCharacter.length; start++) {
            if (answerCharacter[start] == character.toCharArray()[0]) {
                isContainCharacter = true;
                answer[start]=answerCharacter[start];
                if (String.valueOf(answer).equals(quiz.get(index).correctAnswer)) {
                    quizCompleted = true;
                }
            }
        }
        if (isContainCharacter) {
            binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.correct_character));
            incrementTimer(String.valueOf(answer), view);
        } else {
            binding.quizImage.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.wrong_character));
            decrementTimer(view);
        }
    }

    private void decrementTimer(View view) {
        if (incorrectAnswer==0){
            binding.incorrectCharacter1.setVisibility(View.VISIBLE);
        } else if (incorrectAnswer == 1) {
            binding.incorrectCharacter2.setVisibility(View.VISIBLE);
        }else {
            binding.incorrectCharacter3.setVisibility(View.VISIBLE);
        }
        if (incorrectAnswer == 2) {
            Bundle args = new Bundle();
            args.putString("LessonName", lessonName);
            if (getView() != null) {
                Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
            }
        }
        incorrectAnswer++;
        /*ct.cancel();
        timeLeft -= 2000;
        if (timeLeft <= 0L) {
            Bundle args = new Bundle();
            args.putString("LessonName", lessonName);
            if (getView() != null) {
                Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
            }
        } else {
            ct = new CountDownTimer(timeLeft, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    // Update the TextView with the remaining time
                    long secondsRemaining = millisUntilFinished / 1000;
                    timeLeft = millisUntilFinished;
                    binding.quizTimer.setText(String.valueOf(secondsRemaining));
                }

                @Override
                public void onFinish() {
                    // Timer finished, you can perform any actions here
                    Bundle args = new Bundle();
                    args.putString("LessonName", lessonName);
                    if (getView() != null) {
                        Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
                    }
                }
            }.start();
        }*/
    }

    private void incrementTimer(String character, View view) {
//        String answer = binding.quizAnswer.getText().toString();
//        answer += character;
        binding.quizAnswer.setText(character);
        /*ct.cancel();
        timeLeft += 2000;
        ct = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                // Update the TextView with the remaining time
                long secondsRemaining = millisUntilFinished / 1000;
                timeLeft = millisUntilFinished;
                binding.quizTimer.setText(String.valueOf(secondsRemaining));
            }

            @Override
            public void onFinish() {
                // Timer finished, you can perform any actions here
                Bundle args = new Bundle();
                args.putString("LessonName", lessonName);
                if (getView() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizFailFragment, args);
                }
            }
        }.start();*/
        if (quizCompleted) {
//            ct.cancel();
            index++;
            if (index<10){
                binding.quizAnswer.getText().clear();
                setAnswer();
                setQuestion();
            }else {
                Bundle args = new Bundle();
                args.putString("LessonName", lessonName);
                if (getView() != null) {
                    Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizPassFragment, args);
                }
            }
        }
    }

    private void getQuiz() {
        String fileName = "Quiz.json";
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
            QuizModel quizModel = gson.fromJson(jsonString, QuizModel.class);
            for (int start = 0; start < quizModel.quizes.size(); start++) {
                if (quizModel.quizes.get(start).LessonName.equals(lessonName)) {
                    quiz = quizModel.quizes.get(start).quiz;
                    break;
                }
            }
            //Randomise Quizzes
            Collections.shuffle(quiz);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        /*if (ct != null) {
            ct.cancel();
        }*/
        Intent intent = new Intent(requireContext(),BackgroundMusicService.class);
        requireContext().stopService(intent);
        intent.putExtra("From", "MainActivity");
        requireContext().startService(intent);
    }

    @Override
    public void onOkCLick() {
        Bundle args = new Bundle();
        args.putString("LessonName", lessonName);
        Navigation.findNavController(getView()).navigate(R.id.action_quizFragment_to_quizTimeFragment, args);

    }
}