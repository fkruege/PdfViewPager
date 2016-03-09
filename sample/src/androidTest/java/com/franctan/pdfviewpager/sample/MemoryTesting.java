package com.franctan.pdfviewpager.sample;

import com.franctan.pdfviewpager.RegularPDFActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import android.support.test.espresso.intent.rule.IntentsTestRule;
import android.support.test.runner.AndroidJUnit4;

import es.voghdev.pdfviewpager.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeLeft;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.fail;

/**
 * Created by fkruege on 3/6/16.
 */
@RunWith(AndroidJUnit4.class)
public class MemoryTesting {

    @Rule
    public IntentsTestRule<RegularPDFActivity> activityRule =
            new IntentsTestRule<>(RegularPDFActivity.class, true, false);

    @Before
    public void before() {
        startActivity();
    }

    @Test
    public void test_MemoryHandling() {
        try {
            swipeBackAndForth();
        } catch (Exception ex) {
            fail("Error paging");
        }
    }

    private void swipeBackAndForth() {
        swipeToEnd();
        swipeToBeginning();
    }

    private RegularPDFActivity startActivity() {
        return activityRule.launchActivity(null);
    }

    private void swipeToEnd() {
        for (int i = 0; i < 100; i++) {
            onView(withId(R.id.pdfViewPager)).perform(swipeLeft());
        }
    }

    private void swipeToBeginning() {
        for (int i = 0; i < 100; i++) {
            onView(withId(R.id.pdfViewPager)).perform(swipeRight());
        }
    }


}
