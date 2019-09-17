package fr.nelfdesign.mareu.Models;

/**
 * Created by Nelfdesign at 17/09/2019
 * fr.nelfdesign.mareu.Models
 */
public class RoomItemSpinner {

    private String roomName;
    private int roomImage;

    public RoomItemSpinner(String roomName, int roomImage) {
        this.roomName = roomName;
        this.roomImage = roomImage;
    }

    public String getRoomName() {
        return roomName;
    }

    public int getRoomImage() {
        return roomImage;
    }

}
