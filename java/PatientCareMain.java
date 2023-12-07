
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import GUI.*;
import PatientManagement.Clinic.*;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.Encounters.EncounterHistory;
import com.github.javafaker.Faker;

import PatientManagement.Catalogs.AgeGroup;
import PatientManagement.Catalogs.VitalSignLimits;
import PatientManagement.Catalogs.VitalSignsCatalog;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.SickPatientReport;
import PatientManagement.Patient.Encounters.VitalSignMetric;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



public class PatientCareMain {


    public static void main(String[] args) throws Exception {

        Clinic c = ConfigureClinic.createAClinicAndLoadALotOfData("NEU");


        LoginGUI lgu = new LoginGUI(c);

        // LocationList lcl = c.getLocationList();
        // System.out.println(lcl.getLocations().size());
        // for (Location l:lcl.getLocations()){
        //     SiteCatalog stCatalog = l.getSiteCatalog();
        //     SiteReport siteReport = stCatalog.generateSiteSickReport();
        //     siteReport.printSiteReport();
        // }
        




        // PatientDirectory pd = c.getPatientDirectory();
        // SickPatientReport stpatientReport = pd.generateSickPatientReport();
        // stpatientReport.printSickPatientReport();



        // ThirdPartyReport tpr = lcl.generateThirdPartyReport();
        // tpr.printReport();

        //sickReport.printSickPatientReport();

        //Clinic clinic = new Clinic("Northeastern Hospitals");

        // Configuring vital signs catalog
        //VitalSignsCatalog vsc = clinic.getVitalSignsCatalog();

        //AgeGroup adults_21_50 = vsc.newAgeGroup("Adults 21-50", 50, 21);
        //VitalSignLimits heartRateLimits = vsc.newVitalSignLimits("HR");
        //VitalSignLimits bloodPressureLimits = vsc.newVitalSignLimits("BP");
        // heartRateLimits.addLimits(adults_21_50, 105, 55);
        // bloodPressureLimits.addLimits(adults_21_50, 140, 70);

        // // Adding a person
        // PersonDirectory pd = clinic.getPersonDirectory();
        // Person archilPerson = pd.newPerson("archil", 49);

        // // Creating a patient
        // PatientDirectory patientDirectory = clinic.getPatientDirectory();
        // Patient archil = patientDirectory.newPatient(archilPerson);

        // Encounter archilsVisitToDoctor = archil.newEncounter("Seasonal Flu", null);
        // archilsVisitToDoctor.addNewVitals("HR", 90);
        // archilsVisitToDoctor.addNewVitals("BP", 100);

        // System.out.println("Does the patient feel well? " + archilsVisitToDoctor.areVitalsNormal());

    }





}
