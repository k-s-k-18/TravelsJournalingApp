package com.example.traveladventures

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.testing.FragmentScenario
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.example.traveladventures.model.UserAccount
import com.example.traveladventures.model.viewmodel.UserAccountViewModel
import com.example.traveladventures.ui.fragment.LoginFragment
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class LoginTest {
/*
    @get:Rule
    var mainActivityRule = androidx.test.ext.junit.rules.ActivityScenarioRule(com.example.traveladventures.ui.activity.MainActivity::class.java)
    private lateinit var viewModel: UserAccountViewModel

    @Test
    fun testFragmentUI() {
        val scenario = launchFragmentInContainer<LoginFragment>()

        // Perform UI interactions and assertions using Espresso
        onView(withId(R.id.)).check(matches(isDisplayed()))
    }


    @Test
    fun testLoginSuccess() {
        // Mock the ViewModel to return a valid user
        val user = UserAccount(1, "test@example.com", "password")
        runBlocking { `when`(viewModel.getUserAccount("test@example.com", "password")).thenReturn(user) }

        // Launch the fragment
        val scenario = launchFragmentInContainer<LoginFragment>()

        // Enter valid credentials
        onView(withId(R.id.emailEditText)).perform(typeText("test@example.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("password"), closeSoftKeyboard())

        // Click the login button
        onView(withId(R.id.loginButton)).perform(click())

        // Verify that the fragment transitioned to the RESUMED state
        scenario.moveToState(Lifecycle.State.RESUMED)

        // Verify that the MainActivity is launched (you might need to adjust this based on your navigation)
        //intended(hasComponent(MainActivity::class.java.name))
        //intended(hasExtra("userId", user.userId.toString()))
    }

    @Test
    fun testLoginFailure() {
        // Mock the ViewModel to return null (invalid credentials)
        //runBlocking { `when`(viewModel.getUserAccount("invalid@example.com", "wrongpassword")).thenReturn(null) }

        // Launch the fragment
        launchFragmentInContainer<LoginFragment>()

        // Enter invalid credentials
        onView(withId(R.id.emailEditText)).perform(typeText("invalid@example.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("wrongpassword"), closeSoftKeyboard())

        // Click the login button
        onView(withId(R.id.loginButton)).perform(click())

        // Verify that the error message is displayed
        onView(withId(R.id.invalidLoginError)).check(matches(isDisplayed()))
    }

    private fun <T : Fragment> launchFragmentInContainer(
        fragmentArgs: Bundle? = null,
        themeResId: Int = R.style.Theme_TravelAdventures
    ): FragmentScenario<T> {
        return this.launchFragmentInContainer(fragmentArgs, themeResId)
    }

    @Test
    fun testSignUpNavigation() {
        // Launch the fragment
        launchFragmentInContainer<LoginFragment>()

        // Click the sign-up button
        onView(withId(R.id.signUpButton)).perform(click())

        // Verify that the SignUpActivity is launched (you might need to adjust this based on your navigation)
        //intended(hasComponent(SignUpActivity::class.java.name))
    }

 */
}





