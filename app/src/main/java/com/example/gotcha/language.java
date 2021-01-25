package com.example.gotcha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class language extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String LANG_PREFS = "langPrefs";
    RadioGroup radioGroup;
    String lang = ""; //language

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);
        loadData();

        radioGroup = findViewById(R.id.RGroup);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                RadioButton rb=(RadioButton)findViewById(checkedId);
                if(rb.getText().equals("English")) {
                    lang = "en-US";
                }
                else if(rb.getText().equals("German")) {
                    lang = "de-DE";
                }
                editor.putString(LANG_PREFS, lang);
                editor.apply();
                //Toast.makeText(getApplicationContext(), sharedPreferences.getString(LANG_PREFS, "")+" selected", Toast.LENGTH_SHORT).show();
            }
        });

        ImageButton back_setting = (ImageButton) findViewById(R.id.backsettings2);
        back_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settingssite.class);
                startActivity(startIntent);
                //go to settings main
            }
        });
    }

    public void loadData() {
        //Toast.makeText(getApplicationContext(), "loaddata executed", Toast.LENGTH_SHORT).show();
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        lang = sharedPreferences.getString(LANG_PREFS, "en-US");
        //Toast.makeText(getApplicationContext(), lang, Toast.LENGTH_SHORT).show();
        radioGroup = findViewById(R.id.RGroup);

        if (lang.equals("en-US")) {
            //Toast.makeText(getApplicationContext(), "english checked", Toast.LENGTH_SHORT).show();
            radioGroup.check(R.id.english);
        }
        else if (lang.equals("de-DE")) {
            //Toast.makeText(getApplicationContext(), "german checked", Toast.LENGTH_SHORT).show();
            radioGroup.check(R.id.german);
        }
        /*else {
            ((RadioButton)findViewById(R.id.english)).setChecked(true);
        }*/
    }
}