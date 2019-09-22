package fr.nelfdesign.mareu.Service;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;

/**
 * Created by Nelfdesign at 22/09/2019
 * fr.nelfdesign.mareu.Service
 */
public class ReunionListService {

    private List<Reunion> mReunionList = new ArrayList<>();

    public List<Reunion> getReunionList() {
        return mReunionList;
    }

    public void addReunion(Reunion reunion){
        mReunionList.add(reunion);
    }

    public void deleteReunion(Reunion reunion){
        mReunionList.remove(reunion);
    }
}
