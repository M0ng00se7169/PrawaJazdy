package com.example.prawajazdy;


import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class MenuActivityTest {
    @Rule
    public ActivityTestRule<MenuActivity> activityActivityTestRule =
            new ActivityTestRule<>(MenuActivity.class);

    @Test
    public void testEgzaminButtonIsDisplayed() {
        onView(withId(R.id.button4))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testEgzaminButtonIsClickable() {
        onView(withId(R.id.button4))
                .check(matches(isClickable()));
    }

    @Test
    public void testTestyButtonIsDisplayed() {
        onView(withId(R.id.button2))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testTestyButtonIsClickable() {
        onView(withId(R.id.button2))
                .check(matches(isClickable()));
    }

    @Test
    public void testPytaniaButtonIsDisplayed() {
        onView(withId(R.id.button1))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testPytaniaButtonIsClickable() {
        onView(withId(R.id.button1))
                .check(matches(isClickable()));
    }

    @Test
    public void testUstawieniaButtonIsDisplayed() {
        onView(withId(R.id.button3))
                .check(matches(isDisplayed()));
    }

    @Test
    public void testUstawieniaButtonIsClickable() {
        onView(withId(R.id.button3))
                .check(matches(isClickable()));
    }
}
