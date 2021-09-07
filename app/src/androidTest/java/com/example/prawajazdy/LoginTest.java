package com.example.prawajazdy;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
public class LoginTest {
    @Rule
    public ActivityTestRule<Login> activityActivityTestRule =
            new ActivityTestRule<>(Login.class);

    @Test
    public void userCanWriteLoginTest() {
        onView(withId(R.id.emailLogin))
                .perform(typeText("admin"));
    }

    @Test
    public void userCanWritePasswordTest() {
        onView(withId(R.id.passwordLogin))
                .perform(typeText("admin"));
    }

    @Test
    public void userLoginEmailTextBoxIsDisplayedTest() {
        onView(withId(R.id.emailLogin))
                .check(matches(isDisplayed()));
    }

    @Test
    public void userPasswordTextBoxIsDisplayedTest() {
        onView(withId(R.id.passwordLogin))
                .check(matches(isDisplayed()));
    }

    @Test
    public void btnLoginIsDispolayedTest() {
        onView(withId(R.id.loginBtn))
                .check(matches(isDisplayed()));
    }

    @Test
    public void btnLoginIsClickableTest() {
        onView(withId(R.id.loginBtn))
                .check(matches(isClickable()));
    }
}
