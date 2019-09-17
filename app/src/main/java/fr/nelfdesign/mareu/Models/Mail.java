package fr.nelfdesign.mareu.Models;

/**
 * Created by Nelfdesign at 15/09/2019
 * fr.nelfdesign.mareu.Models
 */
public class Mail {

    // Fields
    private String name;

    // constructor
    public Mail(String name) {
        this.name = name;
    }

    // getter and setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail(){
        return this.getName() +"@lamzone.com";
    }
}
