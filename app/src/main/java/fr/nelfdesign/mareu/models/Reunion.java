package fr.nelfdesign.mareu.models;


/**
 * Created by Nelfdesign at 15/09/2019
 * fr.nelfdesign.mareu.Models
 */
public class Reunion {

    //Fields
    private String reunionObject;
    private int idRoom;
    private String nameRoom;
    private String mDate;
    private String time;
    private String mMail;

    //constructor
    public Reunion(String reunionObject, int idRoom, String nameRoom, String date, String time, String mail) {
        this.reunionObject = reunionObject;
        this.idRoom = idRoom;
        this.nameRoom = nameRoom;
        this.mDate = date;
        this.time = time;
        this.mMail = mail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public void setNameRoom(String nameRoom) {
        this.nameRoom = nameRoom;
    }

    public String getReunionObject() {
        return reunionObject;
    }

    public void setReunionObject(String reunionObject) {
        this.reunionObject = reunionObject;
    }

    public String getMail() {
        return mMail;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
