package com.example.prawajazdy;

import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class RegisterTest {
    @Rule
    public ActivityTestRule<Register> activityActivityTestRule =
            new ActivityTestRule<>(Register.class);

    @Test
    public void userCanWriteLoginTest() {
        onView(withId(R.id.email))
                .perform(typeText("admin"));
    }

    @Test
    public void userCanWritePasswordTest() {
        onView(withId(R.id.password))
                .perform(typeText("admin"));
    }

    @Test
    public void userCanWriteTelefonTest() {
        onView(withId(R.id.phoneNumber))
                .perform(typeText("123456789"));
    }

    @Test
    public void loginTextBoxIsDisplayedTest() {
        onView(withId(R.id.email))
                .check(matches(isDisplayed()));
    }

    @Test
    public void passwordTextBoxIsDisplayedTest() {
        onView(withId(R.id.password))
                .check(matches(isDisplayed()));
    }

    @Test
    public void telefonTextBoxIsDisplayedTest() {
        onView(withId(R.id.phoneNumber))
                .check(matches(isDisplayed()));
    }

    @Test
    public void buttonRegisterButtonIsClickableTest() {
        onView(withId(R.id.registerBtn))
                .check(matches(isClickable()));
    }

    @Test
    public void buttonRegisterButtonIsDisplayedTest() {
        onView(withId(R.id.registerBtn))
                .check(matches(isDisplayed()));
    }
}
