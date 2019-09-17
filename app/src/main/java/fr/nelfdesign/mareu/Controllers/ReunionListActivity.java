package fr.nelfdesign.mareu.Controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import fr.nelfdesign.mareu.R;

public class ReunionListActivity extends AppCompatActivity {

    ReunionFragment mReunionFragment;
    FloatingActionButton mFloatingActionButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);

        this.configureFragment();
        configurFab();

    }

    // CONFIGURATION ---
    private void configureFragment() {
        mReunionFragment = (ReunionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_reunion);

        if (mReunionFragment == null) {
            // B - Create new main fragment
            mReunionFragment = new ReunionFragment();
            // C - Add it to FrameLayout container
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_reunion, mReunionFragment)
                    .commit();
        }
    }

    private void configurFab(){
        mFloatingActionButton = findViewById(R.id.fab_add_reu);
        mFloatingActionButton.setOnClickListener(view ->{
            CreateReunionDialog createReunion = new CreateReunionDialog();
            createReunion.mCreateReunionListener = new CreateReunionDialog.CreateReunionListener() {
                @Override
                public void onPositiveclick() {

                }

                @Override
                public void onNegativeClick() {

                }
            };
            createReunion.show(getSupportFragmentManager(), "CreateReunionDialog");
            });
    }
}
