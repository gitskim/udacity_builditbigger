package com.udacity.gradle.builditbigger;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.bgirlogic.joketellinglibrary.JokeTellingAsyncTask;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    private Button mJokeButton;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);

        mJokeButton = (Button) root.findViewById(R.id.joke_button);
        setJokeButtonClickListener();
        return root;
    }

    private void setJokeButtonClickListener() {
        mJokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new JokeTellingAsyncTask(getActivity(), new JokeTellingAsyncTask.OnJokeRetrievedListener() {
                    @Override
                    public void onRetrieved(String joke) {
                        //do nothing
                    }
                }).execute();
            }
        });
    }
}
