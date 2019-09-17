package fr.nelfdesign.mareu.Controllers;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import fr.nelfdesign.mareu.Models.Reunion;
import fr.nelfdesign.mareu.Models.RoomItemSpinner;
import fr.nelfdesign.mareu.R;

public class ReunionListActivity extends AppCompatActivity {

    ReunionFragment mReunionFragment;
    List<Reunion> mReunionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reunion_list);

        mReunionList = new ArrayList<>();
        configureFragment();
    }

    // CONFIGURATION ---
    private void configureFragment() {
        mReunionFragment = (ReunionFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_reunion);

        if (mReunionFragment == null) {
            mReunionFragment = new ReunionFragment();

            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_reunion, mReunionFragment)
                    .commit();
        }
    }

}
