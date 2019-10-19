package fr.nelfdesign.mareu.ui.fragments;


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
import android.widget.EditText;
import android.widget.Spinner;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import fr.nelfdesign.mareu.ui.activity.ReunionListActivity;
import fr.nelfdesign.mareu.models.Reunion;
import fr.nelfdesign.mareu.models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.utils.Utils;

import static fr.nelfdesign.mareu.utils.Utils.checkDate;
import static fr.nelfdesign.mareu.utils.Utils.checkHour;
import static fr.nelfdesign.mareu.utils.Utils.checkHourNull;
import static fr.nelfdesign.mareu.utils.Utils.checkInputText;
import static fr.nelfdesign.mareu.utils.Utils.checkRoomAndDate;
import static fr.nelfdesign.mareu.utils.Utils.makeMailString;

/**
 * A simple {@link Fragment} subclass.
 */
public class CreationReunionFragment extends Fragment {

    public interface CreateReunionListener{
        void onCreateReunion(Reunion reunion);
    }

    private RoomItemSpinner mRoomItemSpinner;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener;
    public CreateReunionListener mCreateReunionListener;
    private String date;
    private String hour;

    @BindView(R.id.edit_title_reu)
    public EditText reunionTitle;
    @BindView(R.id.spinner_room)
    public Spinner spinnerRoom;
    @BindView(R.id.button)
    public Button mButton;
    @BindView(R.id.date)
    public EditText mTextDate;
    @BindView(R.id.hour_text)
    public EditText hourText;
    @BindView(R.id.edit_title_mail)
    public EditText editMail;
    @BindView(R.id.button_create_reunion)
    Button mButtonCreateReunion;

    public CreationReunionFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof CreationReunionFragment.CreateReunionListener){
            mCreateReunionListener = (CreationReunionFragment.CreateReunionListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implemente interface");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

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

        mTextDate.setText(checkDate(date));
        hourText.setText(checkHourNull(hour));

        mDateSetListener = (view1, year, month, dayOfMonth) -> {
            String day = String.valueOf(dayOfMonth);
            String monthS = String.valueOf(month+1);
            if (dayOfMonth < 10){
                day = "0" + dayOfMonth;
            }
            if (month < 9){
                monthS = "0" + (month+1);
            }
            date = day + "/" + monthS + "/" + year;
            mTextDate.setText(date);
        };

        mTimeSetListener = (view12, hourOfDay, minute) -> {
            if (!checkHour(hourOfDay, minute)){
                Snackbar.make(getView(), "Choose a time between 9h and 19h.", Snackbar.LENGTH_LONG).show();
            }else {
                String heureString = String.valueOf(hourOfDay);
                String minuteString = String.valueOf(minute);
                if (hourOfDay < 10){
                    heureString = "0" + hourOfDay;
                }
                if (minute < 10){
                    minuteString = "0" + minute;
                }
                hour= heureString + ":" + minuteString;
                hourText.setText(hour);
            }
        };

        mButtonCreateReunion.setOnClickListener(v -> {
            if (!checkInputText(reunionTitle) ||
                    !checkInputText(editMail) ){
                Snackbar.make(getView(), "You must write topic and name.", Snackbar.LENGTH_SHORT).show();

            }else if(!checkRoomAndDate(mRoomItemSpinner.getRoomName(),
                    mTextDate.getText().toString(),
                    hourText.getText().toString(),
                    ReunionListActivity.mReunionListService.getReunionList())){
                Snackbar.make(this.getView(),
                        "Select a new date for the mmeting in the room " + mRoomItemSpinner.getRoomName(),
                        Snackbar.LENGTH_LONG).show();
            }else{
                Reunion reunion = createReunion();
                mCreateReunionListener.onCreateReunion(reunion);
            }
        });

        return view;
    }

    @Override
    public void onDetach() {
        mCreateReunionListener = null;
        super.onDetach();
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
        int hour = cal.get(Calendar.HOUR_OF_DAY);
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
                mRoomItemSpinner.getRoomImage(),
                mRoomItemSpinner.getRoomName(),
                mTextDate.getText().toString(),
                hourText.getText().toString(),
                str
        );
    }

}










