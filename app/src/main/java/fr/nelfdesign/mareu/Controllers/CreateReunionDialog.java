package fr.nelfdesign.mareu.Controllers;

import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.Models.RoomItem;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;

public class CreateReunionDialog extends DialogFragment{

    public interface CreateReunionListener{
        void onPositiveclick(Reunion reunion);
        void onNegativeClick();
    }

    CreateReunionListener mCreateReunionListener;
    private ArrayList<RoomItemSpinner> mRoomItemSpinners;
    private RoomAdapter mRoomAdapter;
    private int mRoomItemId;
    private String mRoomItemName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    @BindView(R.id.edit_title_reu)
    public EditText reunionTitle;
    @BindView(R.id.spinner_room)
    public Spinner spinnerRoom;
    @BindView(R.id.button)
    public Button mButton;
    @BindView(R.id.date)
    public TextView mTextDate;
    @BindView(R.id.spinner_hour)
    public Spinner spinnerhour;
    @BindView(R.id.edit_title_mail)
    public EditText editMail;

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View view = requireActivity().getLayoutInflater().inflate(R.layout.create_reunion_dialog,null ,false);
        dialog.setTitle("Création d'une réunion");

        ButterKnife.bind(this,view);

        initRoomSpinner(view);
        initHourSpinner(view);

        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                RoomItemSpinner roomItemSpinner = (RoomItemSpinner) spinnerRoom.getSelectedItem();
                mRoomItemId = roomItemSpinner.getRoomImage();
                mRoomItemName = roomItemSpinner.getRoomName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               configureDialogCalendar();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String date = dayOfMonth + "/" + (month +1) + "/" + year;
                mTextDate.setText(date);
            }
        };

        dialog.setPositiveButton( "Créer réunion", (dialog1, which) -> {
            mCreateReunionListener.onPositiveclick(createReunion(view));
        });
        dialog.setNegativeButton("Annuler", (dialog12, which) -> mCreateReunionListener.onNegativeClick());

        dialog.setView(view);
        return dialog.create();
    }

    private void initListSpinner(){
        mRoomItemSpinners = new ArrayList<>();

        for ( RoomItem item : RoomItem.values() ){
            mRoomItemSpinners.add(new RoomItemSpinner(item.getIdDrawable(),item.getName()));
        }
    }

    private void initRoomSpinner(View view){
        final Spinner mSpinner = view.findViewById(R.id.spinner_room);
        initListSpinner();
        mRoomAdapter = new RoomAdapter(view.getContext(), mRoomItemSpinners);
        mSpinner.setAdapter(mRoomAdapter);
    }

    private void initHourSpinner(View view){
        String[] list = {"9h00", "10h00", "11h00", "12h00", "13h00", "14h00", "15h00", "16h00", "17h00", "18h00", "19h00"};
        final Spinner mSpinner = view.findViewById(R.id.spinner_hour);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(view.getContext(),android.R.layout.simple_spinner_dropdown_item, list);
        mSpinner.setAdapter(adapter);
    }

    private void configureDialogCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialogDate = new DatePickerDialog(getContext(),
                android.R.style.Theme_Holo_Light_Dialog_MinWidth, mDateSetListener,
                year, month, day);
        dialogDate.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialogDate.show();
    }

    public Reunion createReunion(View view){
        Reunion reunion;

        reunion = new Reunion(reunionTitle.getText().toString(),
                mRoomItemId,
                mRoomItemName,
                mTextDate.getText().toString(),
                spinnerhour.getSelectedItem().toString(),
                makeMailString(editMail.getText().toString()));

        Log.i("ru", String.valueOf(reunion.getIdRoom()) + String.valueOf(reunion.getNameRoom()));

       return reunion;
    }

    private String makeMailString(String mail){
        String str = "";
        String[] arrayString = mail.toLowerCase().split("[,;.:!§/$@?&#|]+");

        for (String a : arrayString){
            a += "@lamzone.com, ";
            str += a;
        }

        return str;
    }

}
