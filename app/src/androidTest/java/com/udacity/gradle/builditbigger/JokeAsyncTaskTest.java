package com.udacity.gradle.builditbigger;

import android.app.Application;
import android.test.ApplicationTestCase;

import com.bgirlogic.joketellinglibrary.JokeTellingAsyncTask;

import java.lang.Override;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class JokeAsyncTaskTest extends ApplicationTestCase<Application> {
    CountDownLatch signal = new CountDownLatch(1);

    public JokeAsyncTaskTest() {
        super(Application.class);
    }

    public void testJoke() {
        new JokeTellingAsyncTask(getContext(), new TestJokeListener()).execute();
        try {
            boolean success = signal.await(30, TimeUnit.SECONDS);
            if (!success) {
                fail("Test timed out");
            }
        } catch (InterruptedException ex) {
            fail();
        }
    }

    private class TestJokeListener implements JokeTellingAsyncTask.OnJokeRetrievedListener {
        @Override
        public void onRetrieved(String joke) {
            assertTrue(joke != null && joke.length() > 0);
            signal.countDown();
        }
    }
}