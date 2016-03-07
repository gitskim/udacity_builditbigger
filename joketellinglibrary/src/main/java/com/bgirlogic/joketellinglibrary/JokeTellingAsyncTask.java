package com.bgirlogic.joketellinglibrary;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by kimsuh on 3/6/16.
 */
public class JokeTellingAsyncTask extends AsyncTask<Void, Void, String> {
    private static final String TAG = JokeTellingAsyncTask.class.getSimpleName();
    private static MyApi sMyApi = null;
    private Context mContext;
    private OnJokeRetrievedListener onJokeRetrievedListener;

    public JokeTellingAsyncTask(Context context, OnJokeRetrievedListener onJokeRetrievedListener) {
        mContext = context;
        this.onJokeRetrievedListener = onJokeRetrievedListener;
        Log.d(TAG, "inside the constructor received onJokeListener");
    }

    @Override
    protected String doInBackground(Void... params) {
        if (sMyApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //option for running agasint local server
                    .setRootUrl("http://10.0.3.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            sMyApi = builder.build();
        }

        try {
            return sMyApi.sayHi("hi").execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mContext.startActivity(JokeTellingMainActivity.newIntent(mContext, s)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
        onJokeRetrievedListener.onRetrieved(s);
        Log.d(TAG, "onpostexecute of the asynctask, sent the string to onRetrieved");
    }

    public interface OnJokeRetrievedListener {
        void onRetrieved(String joke);
    }
}
