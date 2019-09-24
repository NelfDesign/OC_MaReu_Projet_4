package fr.nelfdesign.mareu.Service;

import android.os.Build;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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

    public ArrayList<Reunion> filterPerRoom(){
        ArrayList<Reunion> reunionSortedByRoom = (ArrayList<Reunion>) mReunionList;
        Collections.sort(reunionSortedByRoom, new Comparator<Reunion>() {
            @Override
            public int compare(Reunion o1, Reunion o2) {
                return (int)(o1.getIdRoom() - o2.getIdRoom());
            }
        });
        return reunionSortedByRoom;
    }

    public ArrayList<Reunion> filterPerDate(){
        ArrayList<Reunion> reunionSortedByDate = (ArrayList<Reunion>) mReunionList;

        Collections.sort(reunionSortedByDate, new Comparator<Reunion>() {
            @Override
            public int compare(Reunion o1, Reunion o2) {
                return o1.getDateInt() - o2.getDateInt();
            }
        });

        return reunionSortedByDate;
    }
}
