package com.bgirlogic.joketellinglibrary;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeTellingMainActivity extends AppCompatActivity {

    private String mJoke;

    public static final String JOKE_PARAMS = "joke_params";

    public static Intent newIntent(Context context, String joke) {
        Intent intent = new Intent(context, JokeTellingMainActivity.class);
        intent.putExtra(JOKE_PARAMS, joke);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke_telling_main);

        TextView jokeTextView = (TextView) findViewById(R.id.joke_display_text);

        if (getIntent() != null) {
            mJoke = getIntent().getStringExtra(JOKE_PARAMS);
            jokeTextView.setText(mJoke);
        }

    }
}
