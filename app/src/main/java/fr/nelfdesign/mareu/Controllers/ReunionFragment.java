package fr.nelfdesign.mareu.Controllers;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

    private RecyclerView mRecyclerView;
    private List<Reunion> mReunionList;

    public ReunionFragment() {}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);
        mRecyclerView = (RecyclerView) view;
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        initList();
        return view;
    }

    /**
     *
     */
    private void initList() {
        this.mReunionList = new ArrayList<>();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        String date = df.format(Calendar.getInstance().getTime());
        Reunion reunion1 = new Reunion( "Creation application", "salle 14",date, 14,null);
        Reunion reunion2 = new Reunion( "Réunion 2", "salle 15",date, 15,null);
        mReunionList.add(0, reunion1);
        mReunionList.add(1, reunion2);
        mRecyclerView.setAdapter(new ReunionListAdapter(mReunionList));
    }

}
