package fr.nelfdesign.mareu.Models;

import java.util.List;

/**
 * Created by Nelfdesign at 15/09/2019
 * fr.nelfdesign.mareu.Models
 */
public class Reunion {

    //Fields
    private int time;
    private String room;
    private String reunionObject;
    private String mDate;
    private List<Mail> mMail;

    //constructor

    public Reunion(String reunionObject, String room, String  date, int time, List<Mail> mails) {
        this.time = time;
        this.room = room;
        this.mDate = date;
        this.reunionObject = reunionObject;
        this.mMail = mails;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getReunionObject() {
        return reunionObject;
    }

    public void setReunionObject(String reunionObject) {
        this.reunionObject = reunionObject;
    }

    public List<Mail> getMail() {
        return mMail;
    }

    public String getDate() {
        return mDate;
    }

    public void setDate(String date) {
        mDate = date;
    }
}
