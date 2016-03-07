package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.bgirlogic.joketellinglibrary.JokeTellingAsyncTask;
import com.bgirlogic.joketellinglibrary.OnJokeRetrievedListener;

import java.lang.Exception;
import java.lang.Override;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class JokeAsyncTaskTest extends ApplicationTestCase<Application> implements OnJokeRetrievedListener {
    CountDownLatch signal;
    String joke;

    public JokeAsyncTaskTest() {
        super(Application.class);
    }

    public void testJoke() {
        try {
            signal = new CountDownLatch(1);
            new JokeTellingAsyncTask(getContext()).execute(this);
            signal.wait(20000);
            assertNotNull("joke is null", joke);
        } catch (Exception ex) {
            fail();
        }
    }

    @Override
    public void onJokeRetrieved(String joke) {
        this.joke = joke;
        signal.countDown();
    }
}