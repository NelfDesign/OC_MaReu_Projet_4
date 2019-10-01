package fr.nelfdesign.mareu;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.nelfdesign.mareu.Controllers.Activity.ReunionListActivity;
import fr.nelfdesign.mareu.UtilsTest.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ReunionListTest {

    // Fields
    private ReunionListActivity mReunionListActivity;
    private static int ITEMS_COUNT = 3;

    // rules
    @Rule
    public ActivityTestRule<ReunionListActivity> mActivityRule =
            new ActivityTestRule(ReunionListActivity.class);

    // start new activity
    @Before
    public void setUp() {
        mReunionListActivity = mActivityRule.getActivity();
        assertThat(mReunionListActivity, notNullValue());
    }

    @Test
    public void reunionList_shouldNotBeEmpty() {
        // First scroll to the position that needs to be matched and click on it.
        onView(ViewMatchers.withId(R.id.reunion_list))
                .check(matches(hasMinimumChildCount(1)));
    }

    // clickon the FAB to go to the creation page
    @Test
    public void reunionList_clickonFab_thenGoToCreatePage(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_reu)).perform(click());

        onView(withId(R.id.create_reunion_fragment)).check(matches(isDisplayed()));
    }

    @Test
    public void reunionList_clickOnCreateButton_addNewReunion(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_reu)).perform(click());
        onView(withId(R.id.button_create_reunion)).perform(click());

        onView(withId(R.id.reunion_list)).check(RecyclerViewItemCountAssertion.withItemCount(ITEMS_COUNT +1));
    }
}
