package com.example.traveladventures

import android.R
import android.app.PendingIntent.getActivity
import android.view.MotionEvent
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.example.traveladventures.ui.activity.AddNoteActivity
import com.example.traveladventures.ui.activity.LoginActivity
import com.example.traveladventures.ui.activity.MainActivity
import com.example.traveladventures.ui.activity.SignUpActivity
import org.junit.Rule
import org.junit.Test


public class TravelAdventuresActivityTest {
/*
    fun TravelAdventuresActivityTest() {

    }

    /*
    @Test
    fun testPreconditions() {
        Assert.assertNotNull(MainActivity)
    }
    */

    @get:Rule
    var addNoteActivityRule: ActivityScenarioRule<AddNoteActivity> = ActivityScenarioRule(
        AddNoteActivity::class.java
    )

    @get:Rule
    var loginActivityRule: ActivityScenarioRule<LoginActivity> = ActivityScenarioRule(
        LoginActivity::class.java
    )

    @get:Rule
    var mainActivityRule: ActivityScenarioRule<MainActivity> = ActivityScenarioRule(
        MainActivity::class.java
    )

    @get:Rule
    var signUpActivityRule: ActivityScenarioRule<SignUpActivity> = ActivityScenarioRule(
        SignUpActivity::class.java
    )

    @Test
    fun testAddNoteActivityUI() {

        onView(withId(R.id.AddNoteFragment)) // Replace with actual view ID
            .check(matches(isDisplayed()))
    }

    @Test
    fun testLoginActivityUI() {
        onView(withId(R.id.login_email_edit_text)) // Replace with actual view ID
            .check(matches(isDisplayed()))
    }

    @Test
    fun testMainActivityUI() {
        onView(withId(R.id.trips_recycler_view)) // Replace with actual view ID
            .check(matches(isDisplayed()))
    }

    @Test
    fun testSignUpActivityUI() {
        onView(withId(R.id.signup_email_edit_text)) // Replace with actual view ID
            .check(matches(isDisplayed()))
    }

 */
}


