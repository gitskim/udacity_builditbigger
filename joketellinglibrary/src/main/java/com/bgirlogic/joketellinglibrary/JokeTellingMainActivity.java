package com.bgirlogic.joketellinglibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class JokeTellingMainActivity extends AppCompatActivity {

    public static Intent newIntent(Context context) {
        Intent intent = new Intent(context, JokeTellingMainActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling_main);
    }
}
