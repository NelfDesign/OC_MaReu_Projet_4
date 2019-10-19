package fr.nelfdesign.mareu.ui.fragments;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.ui.activity.ReunionListActivity;
import fr.nelfdesign.mareu.ui.adapters.ReunionListAdapter;
import fr.nelfdesign.mareu.models.Reunion;
import fr.nelfdesign.mareu.models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.service.ReunionListService;
import fr.nelfdesign.mareu.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReunionFragment extends Fragment {

    public interface fabListener{
        void onFabClicked();
    }

    private RecyclerView mRecyclerView;
    private ReunionListService mReunionList;
    List<Reunion> mReunions;
    private FloatingActionButton mFloatingActionButton;
    public fabListener mFabListener;
    String itemName = "";

    public ReunionFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReunionFragment.fabListener){
            mFabListener = (ReunionFragment.fabListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implemente interface");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        if (ReunionListActivity.mReunionListService == null){
            mReunionList = new ReunionListService();
        }else{
            mReunions = ReunionListActivity.mReunionListService.getReunionList();
        }
    }

    @SuppressLint("RestrictedApi")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);

        mRecyclerView = view.findViewById(R.id.reunion_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mFloatingActionButton = view.findViewById((R.id.fab_add_reu));

        //hide the FAB on tablette
        if (getResources().getConfiguration().screenWidthDp > 600 &&
                getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            mFloatingActionButton.setVisibility(View.GONE);
        }

        initListAdapter(mReunions);
        configurFab();
        return view;
    }

    private void initListAdapter(List<Reunion> reunions) {
        mRecyclerView.setAdapter(new ReunionListAdapter(reunions));
    }

    private void configurFab(){
        mFloatingActionButton.setOnClickListener(view ->{
                mFabListener.onFabClicked();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_reunion, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_filter_room: {
                configureAndShowAlertDialog();
                return true;
            }
            case R.id.action_filter_date: {
                configureAndShowAlertDate();
                return true;
            }
            case R.id.action_no_filter:{
                initListAdapter(mReunions);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFabListener = null;
    }

    private void configureAndShowAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_list_dialog, null);

        Spinner spinner = view.findViewById(R.id.spinner_choice);
        Utils.initRoomSpinner(view, spinner);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               RoomItemSpinner roomItemSpinner = (RoomItemSpinner) spinner.getSelectedItem();
               itemName = roomItemSpinner.getRoomName();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle("Sélectionnez une valeur")
                .setView(view)
                .setPositiveButton("Filtrer",
                        (dialog, which) -> {
                            ArrayList<Reunion> reunion = ReunionListActivity.mReunionListService.filter(itemName);
                            initListAdapter(reunion);
                })
                .setNegativeButton("Annuler",
                        (dialog, which) -> {});

        builder.create().show();
    }

    private void configureAndShowAlertDate(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        View view = LayoutInflater.from(getContext()).inflate(R.layout.filter_list_dialog, null);

        Spinner spinner = view.findViewById(R.id.spinner_choice);
        List<String> arrayList= Utils.initSpinnerDate(mReunions);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                itemName = spinner.getSelectedItem().toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        builder.setTitle("Sélectionnez une valeur")
                .setView(view)
                .setPositiveButton("Filtrer",
                        (dialog, which) -> {
                            ArrayList<Reunion> reunion = ReunionListActivity.mReunionListService.filter(itemName);
                            initListAdapter(reunion);
                        })
                .setNegativeButton("Annuler",
                        (dialog, which) -> {});

        builder.create().show();
    }
}
