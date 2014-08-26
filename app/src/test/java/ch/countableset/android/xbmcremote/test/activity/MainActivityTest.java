package ch.countableset.android.xbmcremote.test.activity;

import android.app.Activity;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import ch.countableset.android.xbmcremote.MainActivity;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@Config(emulateSdk = 18)
@RunWith(RobolectricTestRunner.class)
public class MainActivityTest {

    @Test
    public void doesSomething() {
        Activity activity = Robolectric.buildActivity(MainActivity.class).create().get();
        assertThat(activity, notNullValue());
    }
}
