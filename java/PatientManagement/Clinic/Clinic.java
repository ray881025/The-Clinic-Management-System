/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Persona.PersonDirectory;


public class Clinic {
    PatientDirectory patientdirectory;
    PersonDirectory persondirectory;
    SiteCatalog sitelist;
    LocationList locationlist;

    EventSchedule eventschedule;
    VitalSignsCatalog vitalSignsCatalog;
    EncounterHistory encounterHistory;
    MentalHealthCareList mentalHealthCareList;
    PharmacyList pharmacyList;
    String name; // name of the clinic

    LoginDirectory lgd;

    public Clinic(String n) {
        locationlist = new LocationList();
        eventschedule = new EventSchedule();
        persondirectory = new PersonDirectory();
        patientdirectory = new PatientDirectory(this);
        vitalSignsCatalog = new VitalSignsCatalog();
        encounterHistory = new EncounterHistory();
        mentalHealthCareList = new MentalHealthCareList();
        pharmacyList = new PharmacyList();
        lgd = new LoginDirectory();
        name = n;
    }

    public SiteCatalog getSiteCatalog() {
        return sitelist;
    }

    public LocationList getLocationList() {
        return locationlist;
    }

    public Location newLocation(String name) {

        Location s = locationlist.newLocation(name);
        return s;
    }

    public VitalSignsCatalog getVitalSignsCatalog() {
        return vitalSignsCatalog;
    }

    public PersonDirectory getPersonDirectory() {
        return persondirectory;
    }

    public PatientDirectory getPatientDirectory() {
        return patientdirectory;
    }

    public EncounterHistory getEncounterHistory() {
        return encounterHistory;
    }

    public MentalHealthCareList getMentalHealthCareList() {
        return mentalHealthCareList;
    }

    public PharmacyList getPharmacyList() {
        return pharmacyList;
    }

    public LoginDirectory getLgd() {return lgd;}
}
