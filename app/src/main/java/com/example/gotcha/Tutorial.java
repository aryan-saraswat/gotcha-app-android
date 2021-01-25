package com.example.gotcha;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Tutorial extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        Button go_activity_test_mainscreen = (Button)findViewById(R.id.button_skip);
        go_activity_test_mainscreen.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent startIntent = new Intent(getApplicationContext(), test_mainscreen.class);
                startActivity(startIntent);
                //go to next page
            }
        });
    }
}