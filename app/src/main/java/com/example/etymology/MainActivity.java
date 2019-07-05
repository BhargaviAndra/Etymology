package com.example.etymology;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText enterWord ;
    private TextView wordOrigin;
    private Button button;
    String  url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.button);
       // button2=(Button)findViewById(R.id.button2);
        enterWord = (EditText) findViewById(R.id.enterWord);

        wordOrigin = (TextView) findViewById(R.id.wordOrigin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (enterWord.getText().toString().equals(""))
                enterWord.setError("Please enter the word");
                 else
                     requestApiButtonClick();

            }
        });


    }

    public void requestApiButtonClick() {
        url=dictionaryEntries();
        Request Request = new Request(this,wordOrigin);
        Request.execute(url);
    }

    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word =enterWord.getText().toString(); ;
        final String fields = "etymologies";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
}

