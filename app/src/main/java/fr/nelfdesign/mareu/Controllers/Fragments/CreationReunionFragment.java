package fr.nelfdesign.mareu.Controllers.Fragments;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.time.LocalDate;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.nelfdesign.mareu.Controllers.Adapters.RoomAdapter;
import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreationReunionFragment extends Fragment {

    public interface CreateReunionListener{
        void onCreateReunion(Reunion reunion);
    }

    private RoomAdapter mRoomAdapter;
    private RoomItemSpinner mRoomItemSpinner;
    private int mRoomItemId;
    private String mRoomItemName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    CreateReunionListener mCreateReunionListener;

    @BindView(R.id.edit_title_reu)
    public EditText reunionTitle;
    @BindView(R.id.spinner_room)
    public Spinner spinnerRoom;
    @BindView(R.id.button)
    public Button mButton;
    @BindView(R.id.date)
    public TextView mTextDate;
    @BindView(R.id.hour_text)
    public TextView hourText;
    @BindView(R.id.edit_title_mail)
    public EditText editMail;
    @BindView(R.id.button_create_reunion)
    Button mButtonCreateReunion;

    public CreationReunionFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreateReunionListener){
            mCreateReunionListener = (CreateReunionListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implemente interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creation_reunion, container, false);

        ButterKnife.bind(this,view);

        Utils.initRoomSpinner(view, spinnerRoom);
        spinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                mRoomItemSpinner = (RoomItemSpinner) spinnerRoom.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        mButton.setOnClickListener(v -> {
            CreationReunionFragment.this.configureDialogTimer();
            CreationReunionFragment.this.configureDialogCalendar();
        });

        mDateSetListener = (view1, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month + 1) + "/" + year;
            mTextDate.setText(date);
        };

        mTimeSetListener = (view12, hourOfDay, minute) -> {
            if (!checkHour(hourOfDay, minute)){
                Snackbar.make(getView(), "Choisissez une heure comprise entre 9h et 19h.", Snackbar.LENGTH_SHORT).show();

            }else {
                String heure = hourOfDay + ":" + minute;
                hourText.setText(heure);
            }
        };

        mButtonCreateReunion.setOnClickListener(v -> {
            Reunion reunion = createReunion();
            mCreateReunionListener.onCreateReunion(reunion);
        });

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCreateReunionListener = null;
    }
    // Method ***********************************************************
    private void configureDialogCalendar() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialogDate = new DatePickerDialog(getContext(), mDateSetListener,
                year, month, day);
        //block the calendar to current date
        dialogDate.getDatePicker().setMinDate(System.currentTimeMillis());
        dialogDate.show();
    }

    private void configureDialogTimer() {

        Calendar cal = Calendar.getInstance();
        int hour = cal.get(Calendar.HOUR);
        int minute = cal.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), mTimeSetListener,
                hour, minute, true);

        timePickerDialog.show();
    }

    private Reunion createReunion(){
        String str = "";
        if (editMail.getText().toString().isEmpty()){
            str = "";
        }else {
            str = makeMailString(editMail.getText().toString());
        }
       return new Reunion(
                reunionTitle.getText().toString(),
                mRoomItemId = mRoomItemSpinner.getRoomImage(),
                mRoomItemName = mRoomItemSpinner.getRoomName(),
                mTextDate.getText().toString(),
                hourText.getText().toString(),
                str
        );
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

    private boolean checkHour(int hour, int minute){
        if (hour < 9 || (hour >= 19 && minute >= 1)){
            return false;
        }else {
            return true;
        }
    }
}










