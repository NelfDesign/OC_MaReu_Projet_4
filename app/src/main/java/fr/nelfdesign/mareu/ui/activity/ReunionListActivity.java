package fr.nelfdesign.mareu.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import java.util.List;

import fr.nelfdesign.mareu.R;
import fr.nelfdesign.mareu.models.Reunion;
import fr.nelfdesign.mareu.service.ReunionListService;
import fr.nelfdesign.mareu.ui.fragments.CreationReunionFragment;
import fr.nelfdesign.mareu.ui.fragments.ReunionFragment;

public class ReunionListActivity extends AppCompatActivity implements ReunionFragment.fabListener, CreationReunionFragment.CreateReunionListener {

    private ReunionFragment mReunionFragment;
    private CreationReunionFragment mCreationReunionFragment;
    public static ReunionListService mReunionListService;
    List<Reunion> mReunionList;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(v -> {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_container, mReunionFragment)
                    .commit();
            configToolbar("MaReu", false);
        });

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
        //Mode tablette
        if (mCreationReunionFragment == null && findViewById(R.id.framelayout_container_create) != null) {
            mCreationReunionFragment = new CreationReunionFragment();

            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_container_create, mCreationReunionFragment)
                    .commit();
        }
        mReunionFragment.mFabListener = this;
    }

    @Override
    public void onFabClicked() {
            mCreationReunionFragment = new CreationReunionFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_container, mCreationReunionFragment)
                    .commit();
            configToolbar("Création d'une réunion", true);
    }

    @Override
    public void onCreateReunion(Reunion reunion) {
        mReunionListService.addReunion(reunion);
        // mode landscape
        if (mReunionFragment != null && findViewById(R.id.framelayout_container_create) != null){
            mReunionFragment = new ReunionFragment();
            mCreationReunionFragment = new CreationReunionFragment();
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_container, mReunionFragment)
                    .replace(R.id.framelayout_container_create, mCreationReunionFragment)
                    .commit();
        }else {
            //mode normal
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framelayout_container, mReunionFragment)
                    .commit();
            configToolbar("MaReu", false);
        }
    }

    private void configToolbar(String text, Boolean bool){
        toolbar.setTitle(text);
        getSupportActionBar().setDisplayShowHomeEnabled(bool);
        getSupportActionBar().setDisplayHomeAsUpEnabled(bool);
    }

}
