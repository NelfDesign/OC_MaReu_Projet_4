package fr.nelfdesign.mareu.Controllers;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.Service.ReunionListService;

public class ReunionListActivity extends AppCompatActivity implements ReunionFragment.fabListener, CreationReunionFragment.CreateReunionListener {

    private ReunionFragment mReunionFragment;
    private CreationReunionFragment mCreationReunionFragment;
    static ReunionListService mReunionListService;
    List<Reunion> mReunionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);

        mReunionListService = new ReunionListService();
        mReunionList = mReunionListService.getReunionList();
        this.configureFragment();

    }

    // CONFIGURATION ---
    private void configureFragment() {
        mReunionFragment = (ReunionFragment) getSupportFragmentManager().findFragmentById(R.id.framelayout_container);

        if (mReunionFragment == null) {
            mReunionFragment = new ReunionFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.framelayout_container, mReunionFragment)
                    .commit();
            mReunionFragment.mFabListener = this;
        }
        mReunionFragment.mFabListener = this;
    }

    @Override
    public void onFabClicked() {
        mCreationReunionFragment = new CreationReunionFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, mCreationReunionFragment)
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void onCreateReunion(Reunion reunion) {
        mReunionListService.addReunion(reunion);
        Log.i("reunion", mReunionList.get(0).getReunionObject());
        Log.i("reunion", String.valueOf(mReunionList.size()));

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.framelayout_container, mReunionFragment)
                .commit();
    }
}
