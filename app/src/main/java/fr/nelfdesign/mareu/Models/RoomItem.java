package fr.nelfdesign.mareu.Models;

import fr.nelfdesign.mareu.R;

/**
 * Created by Nelfdesign at 17/09/2019
 * fr.nelfdesign.mareu.Models
 */
public enum RoomItem {
    SALLE1(R.drawable.reunion, "Salle 1"),
    SALLE2(R.drawable.reunion2, "Salle 2"),
    SALLE3(R.drawable.reunion3, "Salle 3"),
    SALLE4(R.drawable.reunion4, "Salle 4"),
    SALLE5(R.drawable.reunion5, "Salle 5"),
    SALLE6(R.drawable.reunion6, "Salle 6"),
    SALLE7(R.drawable.reunion7, "Salle 7"),
    SALLE8(R.drawable.reunion8, "Salle 8"),
    SALLE9(R.drawable.reunion9, "Salle 9"),
    SALLE10(R.drawable.reunion10, "Salle 10");

    private int idDrawable;
    private String name;

    RoomItem(int idDrawable, String name) {
        this.idDrawable = idDrawable;
        this.name = name;
    }

    public int getIdDrawable() {
        return idDrawable;
    }

    public String getName() {
        return name;
    }

    public void setIdDrawable(int idDrawable) {
        this.idDrawable = idDrawable;
    }

    public void setName(String name) {
        this.name = name;
    }
}
