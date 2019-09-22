package fr.nelfdesign.mareu.Controllers;


import android.app.DatePickerDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class CreationReunionFragment extends Fragment {

    public interface CreateReunionListener{
        void onCreateReunion(Reunion reunion);
    }

    private ArrayList<RoomItemSpinner> mRoomItemSpinners;
    private RoomAdapter mRoomAdapter;
    private int mRoomItemId;
    private String mRoomItemName;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    CreateReunionListener mCreateReunionListener;

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
    @BindView(R.id.button_create_reunion)
    Button mButtonCreateReunion;

    public CreationReunionFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_creation_reunion, container, false);

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

        mButton.setOnClickListener(v -> configureDialogCalendar());

        mDateSetListener = (view1, year, month, dayOfMonth) -> {
            String date = dayOfMonth + "/" + (month +1) + "/" + year;
            mTextDate.setText(date);
        };

        mButtonCreateReunion.setOnClickListener(v -> {
            Reunion reunion = createReunion();
            mCreateReunionListener.onCreateReunion(reunion);
        });

        return view;
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
    public void onDetach() {
        super.onDetach();
        mCreateReunionListener = null;
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

    private Reunion createReunion(){
       return new Reunion(
                reunionTitle.getText().toString(),
                mRoomItemId,
                mRoomItemName,
                mTextDate.getText().toString(),
                spinnerhour.getSelectedItem().toString(),
                makeMailString(editMail.getText().toString())
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

}










