package com.example.matip


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers.containsString
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTests {

//    ActivityScenarioRule comes from the AndroidX Test library. It tells the device to launch an
//    activity specified by the developer. You'll need to annotate it with @get:Rule, which
//    specifies that the subsequent rule, in this case launching an activity, should execute
//    before every test in the class.

    @get:Rule
    val activity = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun calculate_20_percent_tip() {

//    The onView() function takes a ViewMatcher object parameter.
//    A ViewMatcher is essentially a UI component that matches a particular criteria,
//    which in this case is a component that has the ID R.id.cost_of_service_edit_text.

//    The function withId() returns a ViewMatcher that is the UI component with the ID that
//    is passed to it. onView() returns a ViewInteraction, which is an object that we can
//    interact with as if we were controlling the device ourselves. To input some text,
//    you call perform() on the ViewInteraction. Then, perform() takes a ViewAction object.
//    There are a number of methods that return a ViewAction but for now we are going to use
//    the typeText() method.
        onView(withId(R.id.cost_of_service_edit_text))
            .perform(typeText("50.00"))
            // Method to close the keyboard
            .perform(ViewActions.closeSoftKeyboard())

        onView(withId(R.id.calculate_button))
            .perform(click())

        onView(withId(R.id.tip_result))
            .check(matches(withText(containsString("$5.00"))))
    }
}

