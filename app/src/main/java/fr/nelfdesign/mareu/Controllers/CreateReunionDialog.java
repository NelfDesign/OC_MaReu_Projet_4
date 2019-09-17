package fr.nelfdesign.mareu.Controllers;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import fr.nelfdesign.mareu.R;

public class CreateReunionDialog extends DialogFragment {

    public interface CreateReunionListener{
        void onPositiveclick();
        void onNegativeClick();
    }

    CreateReunionListener mCreateReunionListener;

    @NonNull
    @Override
    public AlertDialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());

        dialog.setTitle("Création d'une réunion");
        dialog.setView(R.layout.create_reunion_dialog);
        dialog.setPositiveButton( "Créer réunion", (dialog1, which) -> {
            mCreateReunionListener.onPositiveclick();
        });
        dialog.setNegativeButton("Annuler", (dialog12, which) -> mCreateReunionListener.onNegativeClick());

        return dialog.create();
    }
}
