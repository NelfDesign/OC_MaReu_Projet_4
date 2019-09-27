package fr.nelfdesign.mareu.Utils;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Spinner;

import java.util.ArrayList;

import fr.nelfdesign.mareu.Controllers.RoomAdapter;
import fr.nelfdesign.mareu.Models.RoomItem;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;

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
        //final Spinner mSpinner = view.findViewById(R.id.spinner_room);
        initListSpinner();
        RoomAdapter mRoomAdapter = new RoomAdapter(view.getContext(), mRoomItemSpinners);
        spinner.setAdapter(mRoomAdapter);
    }

    public static RoomItemSpinner getSpinnerValues(Spinner spinner){

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 roomItemSpinner = (RoomItemSpinner) spinner.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        return roomItemSpinner;
    }
}
