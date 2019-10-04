package fr.nelfdesign.mareu.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Service.ReunionListService;

import static org.junit.Assert.assertEquals;

/**
 * Created by Nelfdesign at 26/09/2019
 * fr.nelfdesign.mareu.test
 */
public class ReunionListServiceTest {

    //Field interface
    private ReunionListService mReunionListService = new ReunionListService();
    List<Reunion> mReunionList = mReunionListService.getReunionList();

    @Test
    public void getReunionList() {
        assertEquals(3, mReunionList.size());
    }

    @Test
    public void addReunionWithSuccess() {
       Reunion r4 = new Reunion("reunion1", R.drawable.reunion,"salle 1",
               "26/09/2019","14:00","marc@lamzone.com");
       mReunionListService.addReunion(r4);
       assertEquals(4, mReunionList.size());
    }

    @Test
    public void deleteReunionWithSuccess() {
        mReunionListService.deleteReunion(mReunionList.get(0));
        assertEquals(2,mReunionList.size());
    }

    @Test
    public void filterWithSuccess() {
        String text = "salle 4";
        Reunion r4 = new Reunion("reunion1", R.drawable.reunion,"salle 4",
                "26/9/2019","14:00","marc@lamzone.com");
        mReunionListService.addReunion(r4);

        ArrayList<Reunion> reunionSorted = mReunionListService.filter(text);

        assertEquals(2, reunionSorted.size());
    }

}