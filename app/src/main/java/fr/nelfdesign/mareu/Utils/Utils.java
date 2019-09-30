package fr.nelfdesign.mareu.Utils;

import android.view.View;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.nelfdesign.mareu.Controllers.RoomAdapter;
import fr.nelfdesign.mareu.Models.RoomItem;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;

/**
 * Created by Nelfdesign at 27/09/2019
 * fr.nelfdesign.mareu.Utils
 */
public abstract class Utils {

    private static ArrayList<RoomItemSpinner> mRoomItemSpinners;
    private static RoomItemSpinner roomItemSpinner;

    private static void initListSpinner(){
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

}
