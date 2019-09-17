package fr.nelfdesign.mareu.Controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;

public class CreateReunionDialog extends DialogFragment {

    public interface CreateReunionListener{
        void onPositiveclick(Reunion reunion);
        void onNegativeClick();
    }

    CreateReunionListener mCreateReunionListener;
    private ArrayList<RoomItemSpinner> mRoomItemSpinners;
    private RoomAdapter mRoomAdapter;


    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity());

        View view = requireActivity().getLayoutInflater().inflate(R.layout.create_reunion_dialog,null ,false);
        dialog.setTitle("Création d'une réunion");

        initRoomSpinner(view);
        initHourSpinner(view);

        dialog.setPositiveButton( "Créer réunion", (dialog1, which) -> {
            mCreateReunionListener.onPositiveclick(createReunion(view));
        });
        dialog.setNegativeButton("Annuler", (dialog12, which) -> mCreateReunionListener.onNegativeClick());

        dialog.setView(view);
        return dialog.create();
    }


    private void initListSpinner(){
        mRoomItemSpinners = new ArrayList<>();
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 1", R.drawable.reunion));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 2", R.drawable.reunion2));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 3", R.drawable.reunion3));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 4", R.drawable.reunion4));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 5", R.drawable.reunion5));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 6", R.drawable.reunion6));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 7", R.drawable.reunion7));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 8", R.drawable.reunion8));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 9", R.drawable.reunion9));
        mRoomItemSpinners.add(new RoomItemSpinner("Salle 10", R.drawable.reunion10));
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

    private Reunion createReunion(View view){
        Reunion reunion;
       TextView reunionTitle = view.findViewById(R.id.edit_title_reu);
       Spinner spinnerRoom = view.findViewById(R.id.spinner_room);
       Spinner spinnerhour = view.findViewById(R.id.spinner_hour);

        reunion = new Reunion(reunionTitle.getText().toString(), spinnerRoom.getSelectedItem().toString(),null,spinnerhour.getSelectedItem().toString(),null);
        return reunion;
    }

}
