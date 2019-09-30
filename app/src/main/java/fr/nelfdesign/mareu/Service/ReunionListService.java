package fr.nelfdesign.mareu.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;

/**
 * Created by Nelfdesign at 22/09/2019
 * fr.nelfdesign.mareu.Service
 */
public class ReunionListService implements ReunionApi {

    private List<Reunion> mReunionList = generatorOfReunion();

    private static List<Reunion> mReunionListGenerate = Arrays.asList(
            new Reunion("reunion1", R.drawable.reunion,"salle 1","26/9/2019",14,"14:00","marc@lamzone.com"),
            new Reunion("reunion2",R.drawable.reunion2,"salle 2","28/9/2019",18,"10:00","marc@lamzone.com,sophie@lamzone.com"),
            new Reunion("reunion3",R.drawable.reunion4,"salle 4","29/9/2019",1,"09:00","marc@lamzone.com")
    );


    private static List<Reunion> generatorOfReunion() {
        return new ArrayList<>(mReunionListGenerate);
    }

    @Override
    public List<Reunion> getReunionList() {
        return this.mReunionList;
    }

    @Override
    public void addReunion(Reunion reunion){
        this.mReunionList.add(reunion);
    }

    @Override
    public void deleteReunion(Reunion reunion){
        this.mReunionList.remove(reunion);
    }

    @Override
    public ArrayList<Reunion> filter(String text){
        ArrayList<Reunion> reunionSorted = new ArrayList<>();
        for (Reunion r : this.mReunionList) {
            if (r.getNameRoom().equalsIgnoreCase(text) || r.getDate().equals(text)){
                reunionSorted.add(r);
            }
        }
        return reunionSorted;
    }

}
