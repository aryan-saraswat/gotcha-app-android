package com.example.gotcha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class test_mainscreen extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DICT = "dict";
    public static final String LANG_PREFS = "langPrefs";
    String lang = "";

    private static final int RECOGNIZER_RESULT = 1;
    TextView speechText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_mainscreen);
        loadData();

        ImageButton go_Settingssite = findViewById(R.id.go_settings);
        go_Settingssite.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settingssite.class);
                startActivity(startIntent);
                //go to settingssite
            }
        });

        ImageButton goToDict = findViewById(R.id.go_to_dict);
        goToDict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(v.getContext(), dictionary.class);
                startActivity(startIntent);
            }
        });


        Button speechButton = findViewById(R.id.imageView);
        speechButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                //speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_COMPLETE_SILENCE_LENGTH_MILLIS, 200000);//toComment
                //speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_POSSIBLY_COMPLETE_SILENCE_LENGTH_MILLIS, 200000);//toComment
                //speechIntent.putExtra(RecognizerIntent.EXTRA_SPEECH_INPUT_MINIMUM_LENGTH_MILLIS, 20000);//toComment
                //speechIntent.putExtra("android.speech.extra.DICTATION_MODE", true);//toCut
                //speechIntent.putExtra(RecognizerIntent.EXTRA_PARTIAL_RESULTS, false);//toCut
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, lang);
                speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                speechIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speech to text");
                startActivityForResult(speechIntent, RECOGNIZER_RESULT);
            }
        });
        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) { //results of speech recognizer
        speechText = findViewById(R.id.textView);
        String result = "";

        if(requestCode == RECOGNIZER_RESULT && resultCode == RESULT_OK) {
            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            result = matches.get(0).toString();
            speechText.setText(result);
        }

        if(result!= null && !result.trim().isEmpty()) {
            processWords(result.toLowerCase());
            super.onActivityResult(requestCode, resultCode, data);
        }


    }

    public void processWords(String speechResult) {
        String[] temp = speechResult.split(" "); //split the string. z.B.: "Hello my name is Dennis" = {"Hello", "my", "name", ..., "Dennis"}
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        String words = sharedPreferences.getString(DICT, ""); //getting saved dictionary from shared preferences

        //Toast.makeText(getApplicationContext(), words, Toast.LENGTH_SHORT).show();
        String[] dict = words.split(";"); //splitting the words from dictionary and making an array
        //int k = 0;
        Boolean counter = false;

        for(String i:temp) {
            for(String j:dict) {
                if(i.contains("*")) {
                    int profanityLength = i.length();
                    String str = Character.toString(i.charAt(0));

                    if (j.startsWith(str) && j.length() == profanityLength) {
                        counter = true;
                        //(MediaPlayer.create(test_mainscreen.this, R.raw.gotchasound)).start();
                        speechText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));//toComment
                        //Toast.makeText(getApplicationContext(), j, Toast.LENGTH_SHORT).show();
                        vibrateForTime(500);
                    }
                }

                else {
                    if(j.equals(i)){
                        counter = true;
                        //(MediaPlayer.create(test_mainscreen.this, R.raw.gotchasound)).start();
                        speechText.startAnimation(AnimationUtils.loadAnimation(this, R.anim.shake));//toComment
                        //Toast.makeText(getApplicationContext(), j, Toast.LENGTH_SHORT).show();
                        vibrateForTime(500);
                    }
                }
            }
        }

        if(counter) {
            (MediaPlayer.create(test_mainscreen.this, R.raw.gotchasound)).start();
        }
        //Toast.makeText(getApplicationContext(), String.valueOf(k) + " matches", Toast.LENGTH_SHORT).show();
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        lang = sharedPreferences.getString(LANG_PREFS, "en-US");
    }

    public void vibrateForTime(int time) { //function to make phone vibrate for time (milliseconds)
        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        VibrationEffect vibrationEffect = VibrationEffect.createOneShot(time, VibrationEffect.DEFAULT_AMPLITUDE);
        vibrator.vibrate(vibrationEffect);
    }
}

