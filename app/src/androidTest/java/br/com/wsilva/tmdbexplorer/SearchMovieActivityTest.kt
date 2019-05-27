package br.com.wsilva.tmdbexplorer

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.*
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import android.view.KeyEvent
import br.com.wsilva.tmdbexplorer.features.moviesearch.SearchMovieActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import android.widget.AutoCompleteTextView

@RunWith(AndroidJUnit4::class)
class SearchMovieActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<SearchMovieActivity> =
        ActivityTestRule(SearchMovieActivity::class.java, true, false)

    val ITEM_TO_SCROLL: Int = 0

    @Test
    fun test1PleaseInformText() {
        activityTestRule.launchActivity(null)
        onView(withId(R.id.txtMessage))
            .check(matches(withText(R.string.app_message)))
        onView(withId(R.id.recyclerView))
            .check(matches(not(isDisplayed())))
    }

    @Test
    fun test2SearchMovie() {
        activityTestRule.launchActivity(null)

        onView((withId(R.id.action_search)))
            .perform(click())

        onView(isAssignableFrom(AutoCompleteTextView::class.java))
            .perform(typeText("finding nemo"), pressKey(KeyEvent.KEYCODE_ENTER))

        //It's works but we have a better to do this (idling resources)
        Thread.sleep(2000)

        onView(withId(R.id.recyclerView))
            .check(matches(isDisplayed()))

        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(ITEM_TO_SCROLL, click()))
    }
}
