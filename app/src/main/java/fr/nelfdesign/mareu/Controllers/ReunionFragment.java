package fr.nelfdesign.mareu.Controllers;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Service.ReunionListService;

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
    fabListener mFabListener;

    public ReunionFragment() {}

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ReunionFragment.fabListener){
            mFabListener = (ReunionFragment.fabListener) context;
        }else {
            throw new RuntimeException(context.toString() + " must implemente interface");
        }

         if ( ReunionListActivity.mReunionListService == null){
             mReunionList = new ReunionListService();
         }else{
             mReunions = ReunionListActivity.mReunionListService.getReunionList();
         }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_reunion, container, false);

        mRecyclerView = view.findViewById(R.id.reunion_list);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        mFloatingActionButton = view.findViewById((R.id.fab_add_reu));

        initListAdapter();
        configurFab();
        return view;
    }

    private void initListAdapter() {
        mReunions = ReunionListActivity.mReunionListService.getReunionList();

        mRecyclerView.setAdapter( new ReunionListAdapter(mReunions));
        Log.i("reunion Fragment", String.valueOf(mReunions.size()));
    }

    private void configurFab(){
        mFloatingActionButton.setOnClickListener(view ->{
                mFabListener.onFabClicked();
        });
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.reunion_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mFabListener = null;
    }
}
