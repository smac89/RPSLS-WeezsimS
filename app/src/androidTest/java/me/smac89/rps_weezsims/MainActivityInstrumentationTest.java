package me.smac89.rps_weezsims;

import android.support.annotation.IdRes;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.isDialog;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withChild;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalToIgnoringCase;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;


@RunWith(AndroidJUnit4.class)
public class MainActivityInstrumentationTest {
    public static final String TAG = MainActivityInstrumentationTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void testRockPaper() throws Exception {
        playersSelect("rock", "paper");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testPaperScissors() throws Exception {
        playersSelect("paper", "scissors");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testScissorsSpock() throws Exception {
        playersSelect("scissors", "spock");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testSpockPaper() throws Exception {
        playersSelect("spock", "paper");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testPaperLizard() throws Exception {
        playersSelect("paper", "lizard");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testRockLizard() throws Exception {
        playersSelect("rock", "lizard");
        expectedMessage("Player 1 wins");
    }

    @Test
    public void testSpockRock() throws Exception {
        playersSelect("spock", "rock");
        expectedMessage("Player 1 wins");
    }

    @Test
    public void testLizardSpock() throws Exception {
        playersSelect("lizard", "spock");
        expectedMessage("Player 1 wins");
    }

    @Test
    public void testLizardScissors() throws Exception {
        playersSelect("lizard", "scissors");
        expectedMessage("Player 2 wins");
    }

    @Test
    public void testRockScissors() throws Exception {
        playersSelect("rock", "scissors");
        expectedMessage("Player 1 wins");
    }

    /**
     * Selects the spinner text that matches the given string
     * @param id The id for the spinner
     * @param select The text to look for in the spinner
     */
    private void selectSpinnerOption(@IdRes final int id, final String select) {
        onView(withId(id))
                .perform(click());

        onData(is(equalToIgnoringCase(select))).perform(click());

        onView(withId(id)).check(matches(withSpinnerText(equalToIgnoringCase(select))));
    }

    private void playersSelect(final String p1, final String p2) {
        selectSpinnerOption(R.id.player1Choice, p1);
        selectSpinnerOption(R.id.player2Choice, p2);

        onView(withId(R.id.action_button))
                .perform(click());
    }

    private void expectedMessage(String text) {
        onView(withChild(allOf(instanceOf(TextView.class), withText(containsString(text)))))
                .inRoot(isDialog())
                .check(matches(isDisplayed()));
    }
}
