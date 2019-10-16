package fr.nelfdesign.mareu.utils;

import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
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

    public static String checkDate(String date){
        if (date == null){
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.FRENCH);
            date = simpleDateFormat.format(date1);
            return date;
        }else {
            return date;
        }
    }
    public static String checkHourNull(String hour){
        if (hour == null){
            Date date1 = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.FRENCH);
            hour = simpleDateFormat.format(date1);
            return hour;
        }else {
            return hour;
        }
    }

    public static boolean checkHour(int hour, int minute){
        if (hour < 9 || (hour >= 19 && minute >= 1)){
            return false;
        }else {
            return true;
        }
    }

    public static boolean checkInputText(EditText textView){
        if (textView.getText().toString().equals("")){
            return false;
        }else {
            return true;
        }
    }

    public static String makeMailString(String mail){
        String str = "";
        String[] arrayString = mail.toLowerCase().split("[,;.:!§/$@?&#|]+");

        for (String a : arrayString){
            a += "@lamzone.com, ";
            str += a;
        }
        return str;
    }
}
