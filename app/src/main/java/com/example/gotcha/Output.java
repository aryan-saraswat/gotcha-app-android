package com.example.gotcha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Output extends AppCompatActivity {
    Switch switchVibration;
    Switch switchGotcha;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String VIB_PREFS = "vibPrefs";
    public static final String SOUND_PREFS = "soundPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);
        loadData();

        switchVibration = findViewById(R.id.switchV); //vibration switch
        switchGotcha = findViewById(R.id.switchG); //gotcha switch

        ImageButton back_setting = (ImageButton) findViewById(R.id.backsettings);
        back_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settingssite.class);
                startActivity(startIntent);
                //go to settings main
            }
        });

        switchVibration.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (isChecked) { //if vibration is checked
                    editor.putBoolean(VIB_PREFS, true);
                }
                else {
                    editor.putBoolean(VIB_PREFS, false);
                }
                editor.apply();
            }
        });

        switchGotcha.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                if (isChecked) { //if gotcha is checked
                    editor.putBoolean(SOUND_PREFS, true);
                }
                else {
                    editor.putBoolean(SOUND_PREFS, false);
                }
                editor.apply();
            }
        });
    }

    public void loadData() {
        //Toast.makeText(getApplicationContext(), "loaddata executed", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        boolean vib = sharedPreferences.getBoolean(VIB_PREFS, true);
        boolean sound = sharedPreferences.getBoolean(SOUND_PREFS, true);
        //Toast.makeText(getApplicationContext(), lang, Toast.LENGTH_SHORT).show();
        switchVibration = findViewById(R.id.switchV); //vibration switch
        switchGotcha = findViewById(R.id.switchG); //gotcha switch
        switchVibration.setChecked(vib);
        switchGotcha.setChecked(sound);
    }
}