package fr.nelfdesign.mareu.UtilsTest;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;

/**
 * Created by Nelfdesign at 03/10/2019
 * fr.nelfdesign.mareu.UtilsTest
 */
public class SpinnerMatcher {

    public static <T> Matcher<T> withMyValue(final String name) {
        return new BaseMatcher<T>() {
            @Override
            public boolean matches(Object item) {
                return item.getClass().getName().equals(name);
            }

            @Override
            public void describeTo(Description description) {

            }
        };
    }
}
