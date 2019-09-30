package fr.nelfdesign.mareu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Service.ReunionListService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Nelfdesign at 26/09/2019
 * fr.nelfdesign.mareu.test
 */
public class ReunionListServiceTest {

    //Field interface
    private List<Reunion> mReunionList = new ReunionListService().getReunionList();

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getReunionList() {
        assertEquals(3, mReunionList.size());
        assertNotNull(mReunionList.isEmpty());
    }

    @Test
    public void addReunionWithSuccess() {
       Reunion r4 = new Reunion("reunion1", R.drawable.reunion,"salle 1",
               "26/9/2019",14,"14:00","marc@lamzone.com");
       mReunionList.add(r4);
       assertEquals(4, mReunionList.size());
    }

    @Test
    public void deleteReunionWithSuccess() {
        mReunionList.remove(0);
        assertEquals(2,mReunionList.size());
    }

    @Test
    public void filterWithSuccess() {
        String text = "salle 4";
        Reunion r4 = new Reunion("reunion1", R.drawable.reunion,"salle 4",
                "26/9/2019",14,"14:00","marc@lamzone.com");
        mReunionList.add(r4);
        ArrayList<Reunion> reunionSorted = new ArrayList<>();
        for (Reunion r : this.mReunionList) {
            if (r.getNameRoom().equalsIgnoreCase(text)){
                reunionSorted.add(r);
            }
        }
        assertEquals(2,reunionSorted.size());
    }

}