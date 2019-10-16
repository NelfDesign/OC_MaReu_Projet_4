package fr.nelfdesign.mareu.utils;

import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toolbar;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import fr.nelfdesign.mareu.ui.adapters.RoomAdapter;
import fr.nelfdesign.mareu.models.Reunion;
import fr.nelfdesign.mareu.models.RoomItem;
import fr.nelfdesign.mareu.models.RoomItemSpinner;

/**
 * Created by Nelfdesign at 27/09/2019
 * fr.nelfdesign.mareu.Utils
 */
public abstract class Utils {

    private static ArrayList<RoomItemSpinner> mRoomItemSpinners;

    public static void initListSpinner(){
       mRoomItemSpinners = new ArrayList<>();

        for ( RoomItem item : RoomItem.values() ){
            mRoomItemSpinners.add(new RoomItemSpinner(item.getIdDrawable(),item.getName()));
        }
    }

    public static void initRoomSpinner(View view, Spinner spinner){
        initListSpinner();
        RoomAdapter mRoomAdapter = new RoomAdapter(view.getContext(), mRoomItemSpinners);
        spinner.setAdapter(mRoomAdapter);
    }

    public static List<String> initSpinnerDate(List<Reunion> mReunions){
        List<String> arrayList = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (Reunion r : mReunions){
            set.add(r.getDate());
        }
        for (String s : set){
            arrayList.add(s);
        }
        return arrayList;
    }

    public static boolean checkRoomAndDate(String room, String date,String hour, List<Reunion> reunions){
        for (Reunion r : reunions){
            if (room.equals(r.getNameRoom()) && date.equalsIgnoreCase(r.getDate()) && hour.equalsIgnoreCase(r.getTime())){
                return false;
            }
        }
        return true;
    }
}
