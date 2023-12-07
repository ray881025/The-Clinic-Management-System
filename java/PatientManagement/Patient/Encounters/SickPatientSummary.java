package PatientManagement.Patient.Encounters;

import java.util.ArrayList;

import PatientManagement.Catalogs.Limits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.Site;
import PatientManagement.Patient.Patient;


public class SickPatientSummary {
    Patient p;
    boolean sick;
    //Encounter sickEncounterLastTest;
    ArrayList<String> locChange;


    public SickPatientSummary(Patient p) {
        this.p = p;
        sick = p.isConfirmedPositive();
        locChange = new ArrayList<String>();

        //this.sickEncounterLastTest = p.getConfirmedReversedEncounter();
        // if (sickEncounterLastTest == null) {
        //     sickpatientlastseen = null;
        // } else {
        //     sickpatientlastseen = sickEncounterLastTest.getEvent().getSite();
        // }
    }

    public boolean isSick() {
        return sick;
    }


    public void sickPatientLoc(){
        for (Encounter s : p.getEncounters()){
            if (s.isSick()){
                if (!locChange.contains(s.getEvent().getSite().getName())){
                    locChange.add(s.getEvent().getSite().getName());
                }
            }
        }
    }

    public void printSickPatientLoc(){
        if (locChange.size()>1){
            System.out.println("--------------------------------Sick Location Report---------------------------------");
            System.out.println(p.getPerson().getPersonId());
            System.out.println("The patient change "+ locChange.size());
            System.out.println(locChange.toString());
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    public ArrayList<String> getLocChange() {
        return locChange;
    }

    public Patient getP() {
        return p;
    }
}
