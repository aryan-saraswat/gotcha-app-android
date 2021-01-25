package com.example.gotcha;


import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Settingssite extends AppCompatActivity {
    Button viewStats;
    Vibrator vibrator;
    Switch switchMic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        ImageButton go_home = (ImageButton)findViewById(R.id.button_home);
        go_home.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), test_mainscreen.class);
                startActivity(startIntent);
                //go to test mainscreen/home
            }
        });

        Button go_Output = (Button)findViewById(R.id.button_Output);
        go_Output.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Output.class);
                startActivity(startIntent);
                //go to Output setting
            }
        });

        Button go_language = (Button)findViewById(R.id.button_language);
        go_language.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), language.class);
                startActivity(startIntent);
                //go to language settings
            }
        });


        vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        viewStats = findViewById(R.id.button_stats);
        viewStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                VibrationEffect vibrationEffect = VibrationEffect.createOneShot(2000, VibrationEffect.DEFAULT_AMPLITUDE);
                vibrator.vibrate(vibrationEffect);
            }
        });
    }
}