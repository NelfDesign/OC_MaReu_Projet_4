package fr.nelfdesign.mareu.Controllers;


import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ReunionFragment extends Fragment {

    private CoordinatorLayout mCoordinatorLayout;
    private RecyclerView mRecyclerView;
    private List<Reunion> mReunionList;
    private FloatingActionButton mFloatingActionButton;
    private ReunionListAdapter mReunionListAdapter;

    public ReunionFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);

        mRecyclerView = view.findViewById(R.id.reunion_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mFloatingActionButton = view.findViewById((R.id.fab_add_reu));
        initList();
        configurFab();
        mCoordinatorLayout = (CoordinatorLayout) view;
        return view;
    }

    /**
     *
     */
    private void initList() {
        this.mReunionList = new ArrayList<>();
        mReunionListAdapter = new ReunionListAdapter(mReunionList);
        mRecyclerView.setAdapter(mReunionListAdapter);
    }

    private void configurFab(){
        mFloatingActionButton.setOnClickListener(view ->{
            CreateReunionDialog createReunion = new CreateReunionDialog();

            createReunion.mCreateReunionListener = new CreateReunionDialog.CreateReunionListener() {
                @Override
                public void onPositiveclick(Reunion reunion) {
                    mReunionList.add(reunion);
                    mReunionListAdapter.notifyDataSetChanged();
                }

                @Override
                public void onNegativeClick() {
                }
            };
            //show the DialogFragment
            createReunion.show(getFragmentManager(), "CreateReunionDialog");
        });
    }


}
