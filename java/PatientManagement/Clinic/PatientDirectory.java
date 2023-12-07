/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.SickPatientReport;
import PatientManagement.Patient.Encounters.SickPatientSummary;
import PatientManagement.Persona.Person;

import java.util.ArrayList;
import java.util.Random;




public class PatientDirectory {
    Clinic clinic;
    ArrayList<Patient> patients;
    ArrayList<SickPatientSummary> sickPatientsSummary;

    PatientDirectory(Clinic clinic) {
        this.clinic = clinic;
        patients = new ArrayList<Patient>();
    }

    public int getConfirmedPositiveTotals() {
        int sum = 0;

        for (Patient p : patients) {
            if (p.isConfirmedPositive()) {
                sum = sum + 1;
            }
        }
        return sum;
    }

    public ArrayList<Patient> getAllConfirmedPositives() {
        ArrayList<Patient> temp = new ArrayList<Patient>();
        for (Patient p : patients) {
            if (p.isConfirmedPositive() == true) {
                temp.add(p);
            }
        }
        return temp; // has the list of encounters with confirmed diagnosis
    }

    public Patient newPatient(Person person) {
        Patient patient = new Patient(person);
        patients.add(patient);
        return patient;
    }

    public Patient findPatient(String id){
        for (Patient p: patients){
            if (p.isMatch(id)) {
                return p;
            }
        }
        return null;
    }

    public SickPatientReport generateSickPatientReport(){
        SickPatientReport sickPatientReport = new SickPatientReport();
        
        for (Patient p : patients) {
            SickPatientSummary sps = new SickPatientSummary(p);
            sickPatientReport.addSickPatientSummary(sps);
        }
        return sickPatientReport;
    }
        //return sickPatientReport;

    public ArrayList<Patient> getPatients() {
        return patients;
    }

    public void getRandomName(int num){
        for (int i = 0; i < num;i++ ){
            Random r = new Random();
            int s = r.nextInt(patients.size());
            System.out.println(patients.get(s).getPerson().getPersonId());
        }
    }
}