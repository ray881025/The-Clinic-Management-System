/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.AlergyHistory;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.ClinicalHistory.VaccinationHistory;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.Person;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;


public class Patient {
    ArrayList<Encounter> encounters;
    VaccinationHistory vachistory;
    Person person;
    AlergyHistory alergyhistory;

    ArrayList<String> diagList;


    public Patient(Person p) {
        encounters = new ArrayList<Encounter>(); // link this patient object back
        person = p;
        alergyhistory = new AlergyHistory();
        vachistory = new VaccinationHistory();
        diagList = new ArrayList<>();
    }

    public ArrayList<Encounter> getEncounters() {
        return encounters;
    }
    // the below method will return the encounter where the infection occured
    // from the returned encounter you pull the event, the site, etc.

    public Encounter getConfirmedEncounter() {
        ArrayList<Encounter> patientencounterlist = this.encounters;

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public Encounter newEncounter(String chiefcomplaint, Event ev) {
        Encounter e = new Encounter(this, chiefcomplaint,ev);
        encounters.add(e);
        return e;
    }

    public boolean isConfirmedPositive() {

        ArrayList<Encounter> patientencounterlist = this.encounters;

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            return diag.isConfirmed();

        }
        return false;
    }

    public Encounter getConfirmedReversedEncounter() {
        ArrayList<Encounter> patientencounterlisttoReverse = this.encounters;
        Collections.reverse(patientencounterlisttoReverse);
        for (Encounter currentencounter : patientencounterlisttoReverse) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag.isConfirmed()) {
                return currentencounter;// send back the whole encounter so we extract event and site
            }
        }
        return null;
    }

    public ArrayList<String> getConfirmedDiseaseType() {
        ArrayList<Encounter> patientencounterlist = this.encounters;

        for (Encounter currentencounter : patientencounterlist) {
            Diagnosis diag = currentencounter.getDiagnosis();
            if (diag != null){
                if (diag.isConfirmed()) {
                    if (!diagList.contains(diag.getDiseaseType())) {
                        diagList.add(diag.getDiseaseType());// send back the whole encounter so we extract event and site
                    }
                 }
            }
        }
        return diagList;
    }
    public boolean isMatch(String id){
        if (person.getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public Person getPerson() {
        return person;
    }

    
    public VaccinationHistory getVachistory() {
        return vachistory;
    }

    public AlergyHistory getAlergyhistory() {
        return alergyhistory;
    }

    public void addPatientEncounter(Encounter e) {
        encounters.add(e);
    }

    public Alergy newAlergy(String name){
        Alergy a = new Alergy(name);
        alergyhistory.getAlergies().add(a);
        return a;
    }

    public Vaccination newVaccination(String name){
        Vaccination v = new Vaccination(name);
        vachistory.getVaccinations().add(v);
        return v;
    }

    

}
