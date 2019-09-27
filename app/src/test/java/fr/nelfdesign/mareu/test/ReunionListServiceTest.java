package fr.nelfdesign.mareu.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Service.ReunionApi;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Nelfdesign at 26/09/2019
 * fr.nelfdesign.mareu.test
 */
public class ReunionListServiceTest {

    //Field interface
    private ReunionApi mReunionApi;
    private List<Reunion> mReunionList = Arrays.asList(
            new Reunion("reunion1", R.drawable.reunion,"salle 1","26/09/19",14,"14:00","marc"),
            new Reunion("reunion2",R.drawable.reunion2,"salle 2","28/09/19",18,"10:00","marc,sophie"),
            new Reunion("reunion3",R.drawable.reunion4,"salle 4","29/09/19",1,"09:00","marc")
    );

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getReunionList() {
        assertNotNull(mReunionList.isEmpty());
    }

    @Test
    public void addReunion() {
    }

    @Test
    public void deleteReunion() {
    }

    @Test
    public void filterPerRoom() {
    }

    @Test
    public void filterPerDate() {
    }
}