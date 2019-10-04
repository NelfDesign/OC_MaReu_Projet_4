package fr.nelfdesign.mareu;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.RootMatchers;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.nelfdesign.mareu.UI.Activity.ReunionListActivity;
import fr.nelfdesign.mareu.UtilsTest.DeleteViewAction;
import fr.nelfdesign.mareu.UtilsTest.RecyclerViewItemCountAssertion;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static android.support.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static fr.nelfdesign.mareu.UtilsTest.ToolbarMatcher.childAtPosition;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
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

    // click on the FAB to go to the creation page
    @Test
    public void reunionList_clickonFab_thenGoToCreatePage(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_reu)).perform(click());

        onView(withId(R.id.create_reunion_fragment)).check(matches(isDisplayed()));
    }

    /**
     * test if when we click on the create button, we add well a new reunion
     */
    @Test
    public void reunionList_clickOnCreateButton_addNewReunion(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));
        onView(withId(R.id.fab_add_reu)).perform(click());
        onView(withId(R.id.edit_title_reu)).perform(replaceText("reunion"));
        onView(withId(R.id.edit_title_mail)).perform(replaceText("marc"));
        onView(withId(R.id.button_create_reunion)).perform(click());

        onView(withId(R.id.reunion_list)).check(RecyclerViewItemCountAssertion.withItemCount(ITEMS_COUNT +1));
    }

    /**
     * check if the delete button removea reunion to the list
     */
    @Test
    public void reunionList_clickOnDeleteButton_ThenDeleteReunion(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));
        onView(withId(R.id.reunion_list)).perform(RecyclerViewActions.actionOnItemAtPosition(0, new DeleteViewAction()));
        onView(withId(R.id.reunion_list)).check(RecyclerViewItemCountAssertion.withItemCount(ITEMS_COUNT - 1));
    }

    /**
     * check if the filter function in the menu filtered well and return good list
     */
    @Test
    public void reunionList_clickOnFilter_thenAttentFilterList(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));

        //Create a ViewInteraction to click on the toolbar hamburger
        ViewInteraction actionMenuItemView = onView(allOf(withContentDescription("Filtrer"),
                childAtPosition(childAtPosition(withId(R.id.toolbar), 1), 0),
                isDisplayed()));

        actionMenuItemView.perform(click());
        // Create a ViewInteraction to click on the item menu
        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.title),
                        withText("Filtrer par salle"),
                        childAtPosition(childAtPosition(withId(R.id.content), 1), 0),
                        isDisplayed()));

        appCompatTextView.perform(click());
        //Create a ViewInteraction to click on the spinner
        ViewInteraction appCompatSpinner = onView(allOf(withId(R.id.spinner_choice),
                        childAtPosition(allOf(withId(R.id.layout_spinner_filter),
                                        childAtPosition(withId(android.R.id.custom), 0)), 0),
                        isDisplayed()));

        appCompatSpinner.perform(click());
        //Click on the fourth position in the spinner list (salle 4)
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(3).perform(click());
        //Create a ViewInteraction to click on the button to accept the filter choice
        ViewInteraction appCompatButton = onView(allOf(withId(android.R.id.button1), withText("Filtrer"),
                        childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0),
                                3)));
        appCompatButton.perform(scrollTo(), click());
        // then attent to show only the filtered list with1 element
        onView(withId(R.id.reunion_list)).check(RecyclerViewItemCountAssertion.withItemCount(1));
    }

    @Test
    public void reunionList_clickOnFilterDate_thenAttentFilterList(){
        onView(withId(R.id.reunion_list)).check(matches(isDisplayed()));

        //Create a ViewInteraction to click on the toolbar hamburger
        ViewInteraction actionMenuItemView = onView(allOf(withContentDescription("Filtrer"),
                childAtPosition(childAtPosition(withId(R.id.toolbar), 1), 0),
                isDisplayed()));

        actionMenuItemView.perform(click());
        // Create a ViewInteraction to click on the item menu
        ViewInteraction appCompatTextView = onView(allOf(withId(R.id.title),
                withText("Filtrer par date"),
                childAtPosition(childAtPosition(withId(R.id.content), 1), 0),
                isDisplayed()));

        appCompatTextView.perform(click());
        //Create a ViewInteraction to click on the spinner
        ViewInteraction appCompatSpinner = onView(allOf(withId(R.id.spinner_choice),
                childAtPosition(allOf(withId(R.id.layout_spinner_filter),
                        childAtPosition(withId(android.R.id.custom), 0)), 0),
                isDisplayed()));

        appCompatSpinner.perform(click());
        //Click on the first position in the spinner list (28/09/19)
        onData(anything()).inRoot(RootMatchers.isPlatformPopup()).atPosition(0).perform(click());
        //Create a ViewInteraction to click on the button to accept the filter choice
        ViewInteraction appCompatButton = onView(allOf(withId(android.R.id.button1), withText("Filtrer"),
                childAtPosition(childAtPosition(withClassName(is("android.widget.ScrollView")), 0),
                        3)));
        appCompatButton.perform(scrollTo(), click());
        // then attent to show only the filtered list with 2 reunion
        onView(withId(R.id.reunion_list)).check(RecyclerViewItemCountAssertion.withItemCount(2));
    }
}
