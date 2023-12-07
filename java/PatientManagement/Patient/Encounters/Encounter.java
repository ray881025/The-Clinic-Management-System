/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.Encounters;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.Site;
import PatientManagement.Patient.Patient;



// vorder = encounter.newVaccinationOrder();
// vacorder.newVaccination();

public class Encounter {
    Patient patient;
    String chiefComplaint;
    Diagnosis diagnosis;
    Event event;
    VitalSigns vitalSigns;
    boolean isSickAfterSpecificPeriod;
    // vital signs
    // orders: assessmentorders, ....

    public Encounter(Patient p, String cc, Event ev) { // event is the date when the check was made
        chiefComplaint = cc;
        event = ev;
        //ev.addEncounter(this);
        patient = p;
        vitalSigns = new VitalSigns(this);
    }

    public Encounter(Patient p) {
        patient = p;
        patient.addPatientEncounter(this);
    }

    public Diagnosis newDiagnosis(String diseasetype, boolean confirmed) {
        this.diagnosis = new Diagnosis(diseasetype, confirmed);
        return diagnosis;
    }

    public Event newEvent(Site s, String date){
        this.event = new Event(s, date,this);
        //event.addEncounter(event);
        return event;
    }


    public Diagnosis getDiagnosis() {
        return diagnosis;
    }

    //public Limits getVitalSignLimits(int age, String name) {
    //    VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();
    //    return vsc.findVitalSignLimits(age, name);
    //}

    // public VitalSignMetric addNewVitals(String name, int value) {
    //     return vitalSigns.addNewVitals(name, value);
    // }

    public boolean areVitalsNormal() {
        return vitalSigns.areNormal();
    }

    public Patient getPatient() {
        return patient;
    }

    public Event getEvent() {
        return event;
    }

    public boolean isSick() {
        return isSickAfterSpecificPeriod;
    }

    public void setSick(boolean isSick) {
        this.isSickAfterSpecificPeriod = isSick;
    }
}