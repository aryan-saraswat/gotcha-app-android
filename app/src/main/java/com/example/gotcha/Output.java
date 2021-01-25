package com.example.gotcha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class Output extends AppCompatActivity {
    Switch switchVibration;
    Switch switchGotcha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_output);

        ImageButton back_setting = (ImageButton) findViewById(R.id.backsettings);
        back_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), Settingssite.class);
                startActivity(startIntent);
                //go to settings main
            }
        });
    }
}