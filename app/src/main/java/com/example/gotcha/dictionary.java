package com.example.gotcha;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class dictionary extends AppCompatActivity {
    Button confirm;
    Button clearDict;
    Button restoreDict;
    EditText editText;
    ArrayList<String> list = new ArrayList<String>();
    ListView showList;
    String words = "";

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String DICT = "dict";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dictionary);
        loadData(); //loading data from shared preferences

        confirm = findViewById(R.id.confirm);
        editText = findViewById(R.id.addWords);
        clearDict = findViewById(R.id.clearDict);
        restoreDict = findViewById(R.id.restoreDict);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                list);

        showList = findViewById(R.id.listView);
        showList.setAdapter(adapter);

        confirm.setOnClickListener(new View.OnClickListener() { //onClickListener for tickmark
            @Override
            public void onClick(View v) {
                String addWord = editText.getText().toString().trim().toLowerCase();
                if (!addWord.isEmpty()) { //if entered word is not empty
                    if(list.contains(addWord)){ //if dictionary already contains word
                        Toast.makeText(getApplicationContext(), '\"' + addWord + '\"' + " is already in the dictionary", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Toast.makeText(getApplicationContext(), '\"' + addWord + '\"' + " added to dictionary!", Toast.LENGTH_SHORT).show();
                        saveData(addWord, false);
                        refresh(getIntent());
                    }
                }
                else { //if entered word empty
                    Toast.makeText(getApplicationContext(), "Type something in!", Toast.LENGTH_SHORT).show();
                }

            }
        });

        clearDict.setOnClickListener(new View.OnClickListener() { //onClickListener for clear dictionary button
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                editor.putString(DICT, "");
                editor.apply();
                Toast.makeText(getApplicationContext(), "Dictionary deleted", Toast.LENGTH_SHORT).show();
                refresh(getIntent());
            }
        });


        showList.setOnItemClickListener(new AdapterView.OnItemClickListener() { //set onClickListener for list items

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String itemValue = (String) showList.getItemAtPosition(position); //extracting string value from clicked item
                deleteFromDict(dictionary.this, itemValue); //calling function to delete the string from the dictionary
            }
        });

        restoreDict.setOnClickListener(new View.OnClickListener() { //to restore original dictionary
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();

                String[] og = {"cunt", "wanker", "shit", "bullshit", "fuck", "motherfucker", "bastard", "prick", "bitch", "dick", "asshole", "pussy", "balls"}; //sample swear words
                String temp = "";
                for (String i:og) {
                    temp = temp+i+";";
                }

                editor.putString(DICT, temp);
                editor.apply();
                Toast.makeText(getApplicationContext(), "Dictionary restored", Toast.LENGTH_SHORT).show();
                refresh(getIntent());
            }
        });
    }

    public void deleteFromDict(Context context, String itemValue) { //delete string from dictionary

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setMessage("DELETE \"" + itemValue + "\" from dictionary?").setTitle("DELETION");

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                words = words.replace(itemValue + ";", "");
                Toast.makeText(getApplicationContext(), "\"" + itemValue + "\"" + " deleted", Toast.LENGTH_SHORT).show();
                saveData("", true);
                refresh(getIntent());
            }
        });

        builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    public void refresh(Intent intent) { //refresh activity to update all views
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);
    }

    public void saveData(String newWord, boolean easySave) { //to save data in SHARED_PREFS. easySave = true when no new words to be added, false when words need to be added
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        if (!easySave) { //if we want to save a new word
            //String oldDict = sharedPreferences.getString(DICT, ""); //extracting already saved dictionary
            //Toast.makeText(getApplicationContext(), oldDict, Toast.LENGTH_SHORT).show();
            words = (String) (words + newWord + ";");
            //Toast.makeText(getApplicationContext(), words, Toast.LENGTH_SHORT).show();
        }
        editor.putString(DICT, words); //starts from here if we only want to save an updated dictionary
        editor.apply();


    }

    public void loadData() { //function called at start of onCreate to load stored data
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        words = sharedPreferences.getString(DICT, "");//function always splits words to get list of words in dictionary

        String temp[] = words.split(";");
        for (String i:temp) {
            list.add(i);
        }
    }

    public ArrayList<String> getList() {
        return list;
    }
}
