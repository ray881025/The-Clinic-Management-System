
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


        Scanner sc = new Scanner(System.in);
        boolean exitCode = false;

        final List<String> finalList = new ArrayList<>();
        finalList.add("localserviceChange");
        String input2 = "-1";



        while (!exitCode){


            sc.next();



            if (finalList.get(0).equals("loginPage")) {

                //Create a Login Page
                LoginGUI lgu = new LoginGUI();


                LoginDirectory lgd = c.getLgd();


                JButton loginJB = lgu.getLoginBtn();
                loginJB.addActionListener(e -> {
                    //Validate the login Information
                    String userName = lgu.getUsername();
                    String passWord = lgu.getPassword();
                    if(lgd.findLoginInformation( userName, passWord)){
                        lgu.dispose();
                        finalList.set(0,"homePage");
                    } else {
                        System.out.println("false");
                    };
                });
            }


            if (finalList.get(0).equals("homePage")) {
                Home h = new Home();
                h.setVisible(true);



                JButton homeLogout = h.getLogoutBtn();
                JButton homeToCreate = h.getPatientCreate();
                JButton homeToView = h.getPatientView();
                JButton homeToTrending = h.getDiseaseLocation();
                JButton homeToDiseaseChange = h.getDiseaseChange();


                homeLogout.addActionListener(s -> {
                    h.dispose();
                    finalList.set(0,"loginPage");

                } );

                homeToCreate.addActionListener(a -> {
                    h.dispose();
                    finalList.set(0,"createPage");
                });

                homeToView.addActionListener(b -> {
                    h.dispose();
                    finalList.set(0,"viewPage");
                });

                homeToTrending.addActionListener(f -> {
                    h.dispose();
                    finalList.set(0,"diseaseTrending");
                });

                homeToDiseaseChange.addActionListener(l -> {
                    h.dispose();
                    finalList.set(0,"diseasChange");
                });




            }



            if (finalList.get(0).equals("createPage")) {
                CreatePatient crp = new CreatePatient();
                JButton addBtn = crp.getAddBtn();
                JButton createBackBtn = crp.getBackBtn();


                addBtn.addActionListener(e -> {
                    String newpatientName = crp.getFullnameInput();
                    int newpatientAge = crp.getAgeInput();
                    PatientDirectory patDirectory = c.getPatientDirectory();
                    PersonDirectory perDirectory = c.getPersonDirectory();

                    //Input Persons Name and Age
                    Person newPers = perDirectory.newPerson(newpatientName,newpatientAge);
                    Patient crp_per = patDirectory.newPatient(newPers);

                    //Input vaccination and allergy
                    crp_per.newAlergy(crp.getAllergyDropdown());
                    crp_per.newVaccination(crp.getVaccinationDropdown());

                    //Input Date automatically
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    Date date = new Date(System.currentTimeMillis());
                    String currentdate = simpleDateFormat.format(date);



                    //Input Location and site
                    boolean findSite = false;
                    Location loc = new Location(null);
                    Site sit = new Site(null);
                    LocationList locationList = c.getLocationList();
                    loc = locationList.findLocation(crp.getLocationDropdown());
                    SiteCatalog stc = loc.getSiteCatalog();
                    sit = stc.findSite(crp.getSiteDropdown());




                    //Input event
                    EncounterHistory encounterHistory = c.getEncounterHistory();
                    Encounter fixedEncounter1 = encounterHistory.newEncounter(crp_per);
                    Event ev = fixedEncounter1.newEvent(sit,currentdate);


                });

                createBackBtn.addActionListener(k -> {
                    crp.dispose();
                    finalList.set(0,"homePage");
                });

            }



            if (finalList.get(0).equals("viewPage")) {
                ViewPatient vp = new ViewPatient();
                vp.setVisible(true);


                PatientDirectory patDirectory = c.getPatientDirectory();
                PersonDirectory perDirectory = c.getPersonDirectory();



                JButton viewBtn = vp.getSearchBtn();
                viewBtn.addActionListener(e -> {
                    String vp_patientName = vp.getSearchInput();
                    Patient vp_per = patDirectory.findPatient(vp_patientName);
                    if (vp_per != null) {
                        int ind = vp_per.getEncounters().size();

                        Event lastseen = vp_per.getEncounters().get(ind-1).getEvent();


                        ArrayList<Alergy> al = vp_per.getAlergyhistory().getAlergies();
                        ArrayList<Vaccination> v = vp_per.getVachistory().getVaccinations();
                        String name = vp_per.getPerson().getPersonId();
                        int age = vp_per.getPerson().getAge();

                        vp.getNameInput().setText(name);
                        vp.getAgeInput().setText(String.valueOf(age));

                        if (al.size() != 0) {
                            vp.getAllergyInput().setText(al.get(0).getName());
                        } else {
                            vp.getAllergyInput().setText("N / A");
                        }

                        if (v.size() != 0) {
                            vp.getVaccinationInput().setText(v.get(0).getName());
                        } else {
                            vp.getVaccinationInput().setText("N / A");
                        }

                        vp.getDateInput().setText(lastseen.getDate());
                        vp.getLocationInput().setText(lastseen.getSite().getName());

                        vp.getContBtn().addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                vp.switchToDiseaseType();
                                DiseaseType dst = new DiseaseType();
                                dst.getNameInput().setText(name);
                                ArrayList<String> diaList = vp_per.getConfirmedDiseaseType();
                                System.out.println(diaList);
                                StringBuilder combinedString = new StringBuilder();
                                for (String str : diaList) {
                                    combinedString.append(str+" ");
                                }
                                String result = combinedString.toString();
                                dst.getPreviousInput().setText(result);

                                dst.getAddBtn().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                                        Date date1 = new Date(System.currentTimeMillis());
                                        String currentdate1 = simpleDateFormat1.format(date1);

                                        if (lastseen.getDate().equals(currentdate1)) {
                                            //if the patient is first seen then get last encounter and then get its diseasetype
                                            String diseaseType = dst.getDiseaseDropdown();
                                            if (diseaseType.equals("HIV")||diseaseType.equals("Abola")) {
                                                vp_per.getEncounters().get(0).newDiagnosis(diseaseType,true);
                                            } else {
                                                vp_per.getEncounters().get(0).newDiagnosis(diseaseType,false);
                                            }

                                        } else {
                                            //Input location
                                            String diseaseType = dst.getDiseaseDropdown();
                                            Location loc2 = new Location(null);
                                            Site sit2 = new Site(null);
                                            LocationList locationList2 = c.getLocationList();
                                            loc2 = locationList2.findLocation(dst.getLocationDropdown());
                                            SiteCatalog stc2 = loc2.getSiteCatalog();
                                            sit2 = stc2.findSite(dst.getSiteDropdown());


                                            EncounterHistory encounterHistory = c.getEncounterHistory();
                                            Encounter fixedEncounter1 = encounterHistory.newEncounter(vp_per);

                                            if (diseaseType.equals("HIV")||diseaseType.equals("Abola")) {
                                                fixedEncounter1.newDiagnosis(diseaseType,true);
                                            } else {
                                                fixedEncounter1.newDiagnosis(diseaseType,false);
                                            }
                                            Event ev = fixedEncounter1.newEvent(sit2,currentdate1);
                                        }
                                    }
                                });
                            }
                        });


                    } else {
                        vp.getNameInput().setText("N / A");
                        vp.getAgeInput().setText("N / A");
                        vp.getAllergyInput().setText("N / A");
                        vp.getVaccinationInput().setText("N / A");
                        vp.getDateInput().setText("N / A");
                        vp.getLocationInput().setText("N / A");
                    }
                });
            }



            if (finalList.get(0).equals("diseaseTrending")) {

                ViewSickPatient vsp = new ViewSickPatient();
                vsp.getSearchBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        DefaultTableModel tableModel = vsp.getTableModel();
                        tableModel.setRowCount(0);
                        LocationList reportlc = c.getLocationList();
                        Location reportl = reportlc.findLocation(vsp.getLocationDropdown());
                        SiteCatalog reportsite = reportl.getSiteCatalog();
                        SiteReport reportSiteGenerator = reportsite.generateSiteSickReport();
                        SiteSummary reportSiteSum = reportSiteGenerator.returnReportBySite(vsp.getSiteDropdown());


                        for (int i = 0; i < 12; i++){
                            if (i+1 < 10){
                                Object[] newRow = {"2023-0"+(i+1),reportSiteSum.getSickPatientTotalForThatMonth().get(i),reportSiteSum.getPatientTtForThatMonth().get(i),reportSiteSum.getPercentage().get(i)};
                                tableModel.addRow(newRow);
                            } else {
                                Object[] newRow = {"2023-"+(i+1),reportSiteSum.getSickPatientTotalForThatMonth().get(i),reportSiteSum.getPatientTtForThatMonth().get(i),reportSiteSum.getPercentage().get(i)};
                                tableModel.addRow(newRow);
                            }

                        }

                    }
                });

            }




            if (finalList.get(0).equals("diseaseChange")) {
                DiseaseChange dsg = new DiseaseChange();

                PatientDirectory pd = c.getPatientDirectory();
                SickPatientReport stpatientReport = pd.generateSickPatientReport();
                stpatientReport.generateSickPatientData();

                DefaultTableModel tableM = dsg.getTableModel();
                tableM.setRowCount(0);

                for (int i = 0; i < stpatientReport.getSickName().size(); i++) {
                    Object[] newRowL = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
                    tableM.addRow(newRowL);
                }

                dsg.getSearchBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!dsg.getNameInput().equals("")){
                            tableM.setRowCount(0);
                            for (int i = 0; i < stpatientReport.getSickName().size(); i++){
                                if (dsg.getNameInput().equals(stpatientReport.getSickName().get(i))){
                                    Object[] newRowR = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
                                    tableM.addRow(newRowR);
                                }
                            }
                        } else {
                            tableM.setRowCount(0);
                            for (int i = 0; i < stpatientReport.getSickName().size(); i++){
                                Object[] newRowR = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
                                tableM.addRow(newRowR);
                            }
                        }
                    }
                });
            }



            if (finalList.get(0).equals("localserviceChange")) {
                ViewLocationService vls = new ViewLocationService();
                LocationList lcl = c.getLocationList();
                lcl.generatelocationServiceReport();

                DefaultTableModel tableX = vls.getTableModel();
                tableX.setRowCount(0);
                for (int i = 0; i < lcl.getLocationName().size(); i++) {
                    Object[] newRowL = {lcl.getLocalServiceName().get(i),lcl.getLocalServiceTelphone().get(i),lcl.getLocalServiceAddress().get(i)};
                    tableX.addRow(newRowL);
                }

                vls.getSearchBtn().addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (!vls.getLocationDropdown().equals("")){
                            tableX.setRowCount(0);
                            for (int i = 0; i < lcl.getLocationName().size(); i++) {
                                if (lcl.getLocationName().get(i).equals(vls.getLocationDropdown())){
                                    Object[] newRowL = {lcl.getLocalServiceName().get(i),lcl.getLocalServiceTelphone().get(i),lcl.getLocalServiceAddress().get(i)};
                                    tableX.addRow(newRowL);
                                }
                            }
                        } else {
                            tableX.setRowCount(0);
                            for (int i = 0; i < lcl.getLocationName().size(); i++) {
                                Object[] newRowL = {lcl.getLocalServiceName().get(i),lcl.getLocalServiceTelphone().get(i),lcl.getLocalServiceAddress().get(i)};
                                tableX.addRow(newRowL);
                            }
                        }
                    }
                });
            }


            if (input2.equals("6")){
                exitCode = true;
            }
        }
    

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


    public static void switchToViewPatient(Clinic c, Home home) {

    }


}
