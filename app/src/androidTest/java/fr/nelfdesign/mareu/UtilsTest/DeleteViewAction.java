package fr.nelfdesign.mareu.UtilsTest;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import fr.nelfdesign.mareu.R;

/**
 * Created by Nelfdesign at 02/10/2019
 * fr.nelfdesign.mareu.UtilsTest
 */
public class DeleteViewAction implements ViewAction {
    @Override
    public Matcher<View> getConstraints() {
        return null;
    }

    @Override
    public String getDescription() {
        return "Click on specific button";
    }

    @Override
    public void perform(UiController uiController, View view) {
        View button = view.findViewById(R.id.icon_delete);
        // Maybe check for null
        button.performClick();
    }
}