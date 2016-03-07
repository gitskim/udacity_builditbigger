package com.bgirlogic.joketellinglibrary;

import android.content.Context;
import android.os.AsyncTask;

import com.google.api.client.extensions.android.http.AndroidHttp;
import com.google.api.client.extensions.android.json.AndroidJsonFactory;
import com.google.api.client.googleapis.services.AbstractGoogleClientRequest;
import com.google.api.client.googleapis.services.GoogleClientRequestInitializer;
import com.udacity.gradle.builditbigger.backend.myApi.MyApi;

import java.io.IOException;

/**
 * Created by kimsuh on 3/6/16.
 */
public class JokeTellingAsyncTask extends AsyncTask<OnJokeRetrievedListener, Void, String> {
    private static MyApi sMyApi = null;
    private Context mContext;
    private OnJokeRetrievedListener onJokeRetrievedListener;

    public JokeTellingAsyncTask(Context context) {
        mContext = context;
    }

    @Override
    protected String doInBackground(OnJokeRetrievedListener... params) {
        if (sMyApi == null) {
            MyApi.Builder builder = new MyApi.Builder(AndroidHttp.newCompatibleTransport(),
                    new AndroidJsonFactory(), null)
                    //option for running agasint local server
                    .setRootUrl("http://10.0.2.2:8080/_ah/api/")
                    .setGoogleClientRequestInitializer(new GoogleClientRequestInitializer() {
                        @Override
                        public void initialize(AbstractGoogleClientRequest<?> request) throws IOException {
                            request.setDisableGZipContent(true);
                        }
                    });
            sMyApi = builder.build();
        }

        onJokeRetrievedListener = params[0];

        try {
            return sMyApi.sayHi("").execute().getData();
        } catch (IOException e) {
            return e.getMessage();
        }
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        onJokeRetrievedListener.onJokeRetrieved(s);
        mContext.startActivity(JokeTellingMainActivity.newIntent(mContext, s));
    }
}
