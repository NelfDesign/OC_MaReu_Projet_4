package fr.nelfdesign.mareu.service;

import java.util.ArrayList;
import java.util.List;

import fr.nelfdesign.mareu.models.Reunion;

/**
 * Created by Nelfdesign at 26/09/2019
 * fr.nelfdesign.mareu.Service
 */
public interface ReunionApi {

     List<Reunion> getReunionList();
     void addReunion(Reunion reunion);

     void deleteReunion(Reunion reunion);

     ArrayList<Reunion> filter(String text);
}
