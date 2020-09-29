package mx.rokegcode.ordermanagement.view.activity

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import mx.rokegcode.ordermanagement.R
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    @Test
    fun test_isActivityInView() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()))
    }

    @Test
    fun test_visibility_components() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.loginToolbar)).check(matches(isDisplayed()))
        onView(withId(R.id.imageLogoCompany)).check(matches(isDisplayed()))
        onView(withId(R.id.loginInputUser)).check(matches(isDisplayed()))
        onView(withId(R.id.loginInputPassword)).check(matches(isDisplayed()))
        onView(withId(R.id.loginCheckRemember)).check(matches(isDisplayed()))
        onView(withId(R.id.titleSignIn)).check(matches(isDisplayed()))
        onView(withId(R.id.textSignIng)).check(matches(isDisplayed()))
        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))
    }

    @Test
    fun test_btn_onclick() {
        val activityScenario = ActivityScenario.launch(LoginActivity::class.java)
        onView(withId(R.id.btnLogin)).perform(click())
    }

}