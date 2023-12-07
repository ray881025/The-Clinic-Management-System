/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Patient;
import java.util.ArrayList;


public class EncounterHistory {
    ArrayList<Encounter> encounters;
    //Patient patient;

    public EncounterHistory() {
        encounters = new ArrayList<Encounter>();
    }

    // each encounter must link to the event at the site
    public Encounter newEncounter(Patient p,String chiefcomplaint, Event ev) {
        Encounter e = new Encounter(p, chiefcomplaint,ev);
        encounters.add(e);
        return e;
    }

    public ArrayList<Encounter> getEncounterList() {
        return encounters;
    }

    public Encounter newEncounter(Patient patient) {
        Encounter e = new Encounter(patient);
        encounters.add(e);
        return e;
    }



}
