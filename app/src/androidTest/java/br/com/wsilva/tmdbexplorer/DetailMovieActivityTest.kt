package br.com.wsilva.tmdbexplorer

import android.content.Intent
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import br.com.wsilva.tmdbexplorer.constants.AppConstants
import br.com.wsilva.tmdbexplorer.features.moviedetail.DetailMovieActivity

@RunWith(AndroidJUnit4::class)
class DetailMovieActivityTest {

    @Rule
    @JvmField
    var activityTestRule: ActivityTestRule<DetailMovieActivity> =
        ActivityTestRule(DetailMovieActivity::class.java, true, false)

    val MOVIE_ID = 12

    @Test
    fun test1ShowDetail() {
        val intent = Intent()
        intent.putExtra(AppConstants.API_KEY, MOVIE_ID)
        activityTestRule.launchActivity(intent)

        onView(withId(R.id.txtTitle))
            .check(matches(withText("Finding Nemo")))
    }
}
