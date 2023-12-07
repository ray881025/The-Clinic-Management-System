import java.lang.reflect.Array;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import PatientManagement.Clinic.*;
import PatientManagement.Patient.Patient;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.Encounters.Diagnosis;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Patient.Encounters.VitalSign;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;
import com.github.javafaker.Faker;

public class ConfigureClinic {
    
    private static String[] locationToInput;
    private static String[] mentalHealthCareToInput;
    private static String[] pharmacyToInput;
    public static  ArrayList<Location> loclst;

    public static ArrayList<String> fakername;
    public static ArrayList<Integer> fakerage;
    public static ArrayList<String> fakerLoginName;
    public static ArrayList<String> fakerLoginPassword;
    public static String[] diseaseType = {"HIV","Abola","Stomache","Fever"};
    public static String[] confirmedType = {"HIV","Abola"};
    public static String[] vaccinationToInput = new String[]{"BNT","AZ","Moderna"};
    public static String[] alergyToInput = new String[]{"Food allergies","Seasonal allergies","Drug allergies","N/A"};
    public static String startDate = "2023-01-01";



    public static Clinic createAClinicAndLoadALotOfData(String name) throws Exception{
        Clinic clinic = new Clinic(name);

        loadLoginInformation(clinic,20);

        loadLocations(clinic);

        loadSites(clinic);

        loadPatients(clinic,200);

        loadEncounters(clinic,200,10);

        changeEncounterOrder(clinic);

        changeSickConditions(clinic);

        changeEventOrder(clinic);

        changePatientEncounterOrder(clinic);
        // for (Encounter e : clinic.getEncounterHistory().getEncounterList()){
        //     System.out.println(e.getEvent().getsubDate());
        // }
        // LocationList ct = clinic.getLocationList();
        // for (Location l : ct.getLocations()){
        //     for (Site s : l.getSiteCatalog().getSites()){
        //         System.out.println(s.getName());
        //         for (Event e:s.getEventlists()){
        //             System.out.println(e.getsubDate());
        //         }
        //     }
        // }

        System.out.println("Before everything,print patient name for search purpose");
        clinic.getPatientDirectory().getRandomName(5);

        //openClinic(clinic);

        return clinic;

    }

    public static void loadLoginInformation(Clinic c,int LoginCount) {
        fakerLoginName = generateFakerName(LoginCount);
        fakerLoginPassword = generateFakerName(LoginCount);

        LoginDirectory lgd = c.getLgd();

        for (int i = 0; i < LoginCount; i++) {
            lgd.newLoginUser(fakerLoginName.get(i),fakerLoginPassword.get(i));
        }

        System.out.println("Login name: "+fakerLoginName.get(0)+" Password: "+fakerLoginPassword.get(0));
    }



    public static void loadLocations(Clinic c){
        LocationList locationList = c.getLocationList();
        locationToInput = new String[] {"Malden","Allston","Cambridge"};
        MentalHealthCareList mentalHealthCareList = c.getMentalHealthCareList();
        mentalHealthCareToInput = new String[] {"Psychotherapy Center","Behavioral Center","Support Center","Children Care Center","Old Care Center","Recovery","Recreational Center"};
        PharmacyList pharmacyList = c.getPharmacyList();
        pharmacyToInput = new String[] {"CVS","Walgreens","DPH"};

        Location malden = locationList.newLocation(locationToInput[0]);
        Location allston = locationList.newLocation(locationToInput[1]);
        Location cambridge = locationList.newLocation(locationToInput[2]);

        malden.newMentalHealthCare(mentalHealthCareToInput[0],"(555) 123-4567","1234 Beacon Street",mentalHealthCareList);
        malden.newMentalHealthCare(mentalHealthCareToInput[1], "987-654-3210","5678 Commonwealth Avenue",mentalHealthCareList);
        allston.newMentalHealthCare(mentalHealthCareToInput[1],"800-555-7890","910 Boylston Street",mentalHealthCareList);
        cambridge.newMentalHealthCare(mentalHealthCareToInput[2],"333-222-1111","2468 Tremont Street",mentalHealthCareList);

        malden.newPharmacy(pharmacyToInput[0],pharmacyList);
        allston.newPharmacy(pharmacyToInput[1],pharmacyList);
        cambridge.newPharmacy(pharmacyToInput[2],pharmacyList);
    }

    public static void loadSites(Clinic c){
        LocationList locationList = c.getLocationList();
        ArrayList<Location> locations = locationList.getLocations();
        SiteCatalog maldenSiteCatalog = locations.get(0).getSiteCatalog();
        SiteCatalog allstonCatalog = locations.get(1).getSiteCatalog();
        SiteCatalog cambridgeCatalog = locations.get(2).getSiteCatalog();
        maldenSiteCatalog.newSite(new String[] {"160 Pleasant","150 Exchange","240 Kyle","360 Opsl","420 Quil"});
        allstonCatalog.newSite(new String[] {"122 kkllll","33322 sdwds","2211 dws"});
        cambridgeCatalog.newSite(new String[] {"133 Oxford St.","288 Cambridge St.","888 Oxford St."});
    }

    public static void loadPatients(Clinic c,int patientCount){
        Faker faker = new Faker();
        PatientDirectory patientDirectory = c.getPatientDirectory();
        PersonDirectory personDirectory = c.getPersonDirectory();
        
        fakername = generateFakerName(patientCount);
        fakerage = generateFakerAge(patientCount);


        for (int i =0; i<patientCount; i++){
            //System.out.println(faker.name().fullName());
            //System.out.println(faker.random().nextInt(10, 80));
            Person newPerson = personDirectory.newPerson(fakername.get(i), fakerage.get(i));
            Patient p = patientDirectory.newPatient(newPerson);
            p.newAlergy(alergyToInput[getRandom(0, alergyToInput.length-1)]);
            p.newVaccination(vaccinationToInput[getRandom(0, vaccinationToInput.length-1)]);
        }
    }

    public static void loadEncounters(Clinic c,int patientCount,int patientEncounters) throws Exception{
        PatientDirectory patientDirectory = c.getPatientDirectory();
        LocationList locationList = c.getLocationList();
        EncounterHistory encounterHistory = c.getEncounterHistory();
        ArrayList<Location> locat = locationList.getLocations();

        for (int patientIndex = 0; patientIndex < patientCount; patientIndex++){
            Patient patient = patientDirectory.findPatient(fakername.get(patientIndex));
            //System.out.println(fakername.get(patientIndex));
            int randomEncounterCount =  getRandom(1, patientEncounters);
            
            //For each encounter we have a different location            
            for (int randomEncounterIndex = 0; randomEncounterIndex < randomEncounterCount; randomEncounterIndex++){
                int randomLocationGet = getRandom(0, locat.size()-1);
                Location l = locat.get(randomLocationGet);
                SiteCatalog siteCatalog = l.getSiteCatalog();
                Site site = siteCatalog.pickRandomSite();

                if (site == null) {
                    System.out.println("Nothing in Site Catalog");
                    return;
                }


                Encounter fixedEncounter = encounterHistory.newEncounter(patient);

                ///////////////////date filled,///////
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            
                long randomDate = randomDate(startDate);
                String rd = sdf.format(randomDate);
                ///////////////////////////////////////Random Date/////////////////////
                Event ev = fixedEncounter.newEvent(site,rd);   ///////////////////date filled,///////
                String disea = pickRandomDiseaseType(diseaseType,8,2);
                Boolean contagiousType = checkDiseaseType(disea, confirmedType);
                Diagnosis diag = fixedEncounter.newDiagnosis(disea, contagiousType);
            }

        }
    }

    public static void changeEncounterOrder(Clinic c){
        EncounterComparator ec = new EncounterComparator();
        Collections.sort(c.getEncounterHistory().getEncounterList(),ec);
    }

    public static void changeEventOrder(Clinic c){
        EventComparator ec = new EventComparator();
        for (Location l : c.getLocationList().getLocations()){
            for (Site s: l.getSiteCatalog().getSites()){
                Collections.sort(s.getEventlists(),ec);
            }
        }
    }

    public static void changePatientEncounterOrder(Clinic c){
        EncounterComparator ec = new EncounterComparator();
        for (Patient p:c.getPatientDirectory().getPatients()){
            Collections.sort(p.getEncounters(),ec);
        }
    }

    public static void changeSickConditions(Clinic c){
        PatientDirectory pd = c.getPatientDirectory();
        for (Patient p: pd.getPatients()){
            boolean flag = false;
            for (Encounter e: p.getEncounters()){
                if (e.getDiagnosis().isConfirmed()){
                    flag = true;
                }
                if (flag == true){
                    e.setSick(true);
                } else {
                    e.setSick(false);
                }
            }
        }

    }

    public static void openClinic(Clinic c){
        System.out.println("--------------Welcome to A Day-------------------");
        boolean findLocation = false;
        Scanner sc = new Scanner(System.in);
        Location loc = new Location(null);
        Site sit = new Site(null);
        Faker fake = new Faker();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String currentdate = simpleDateFormat.format(date);
        while (!findLocation) {
            System.out.println("Enter Your Location where you opened your clinic:");
            String input = sc.next();
            LocationList locationList = c.getLocationList();
            if (locationList.findLocation(input) == null){
                System.out.println("Your location input is not found in System");
                System.out.println("Please reenter again");
            } else {
                loc = locationList.findLocation(input);
                findLocation = true;
            }
        }

        boolean exitCode = false;

        while (!exitCode) {
            System.out.println("Enter the site:");
            sc.useDelimiter("\n");
            String input2 = sc.next();
            //System.out.println(input2);
            SiteCatalog stc = loc.getSiteCatalog();
            if (stc.findSite(input2) == null){
                System.out.println("Your Site input is not found in System");
                System.out.println("Please reenter again");
            } else {
                sit = stc.findSite(input2);
                exitCode = true;
            }
        }

        PatientDirectory patDirectory = c.getPatientDirectory();
        PersonDirectory perDirectory = c.getPersonDirectory();
        System.out.println("------------------------------------Loading Data-----------------------------------------");
        boolean isFinished = false;
        while (!isFinished) {
            System.out.println("Enter the patient Name:");
            VitalSign vtsign = new VitalSign(140, 40, 200, 140);
            String input3 = sc.next();
            vtsign.printVitalSigns();
            Patient per = patDirectory.findPatient(input3);
            if (per == null) {
                System.out.println("We did not find your Past Encounters");
                System.out.println("What's your age?");
                Integer input4 = sc.nextInt();
                Person newPers = perDirectory.newPerson(input3,input4);
                per = patDirectory.newPatient(newPers);
                per.newAlergy(alergyToInput[getRandom(0, alergyToInput.length-1)]);
                per.newVaccination(vaccinationToInput[getRandom(0, vaccinationToInput.length-1)]);

                System.out.print("Successfully load your data. Doing the lab test. ");
            } else {
                int ind = per.getEncounters().size();
                Event lastseen = per.getEncounters().get(ind-1).getEvent();
                Alergy al = per.getAlergyhistory().getAlergies().get(0);
                Vaccination v = per.getVachistory().getVaccinations().get(0);
                System.out.println();
                System.out.println("Hello "+input3);
                System.out.println();
                
                System.out.println("-----------------------------We have found your past history-----------------------------");
                System.out.println("Your last seen location is: " + lastseen.getSite().getName() + " Your last seen Date is: " + lastseen.getDate());
                System.out.println("Allergy: "+ al.getName());
                System.out.println("Vaccination: "+ v.getName());
                System.out.println("-----------------------------------------------------------------------------------------");
                }
            System.out.println("This Patient Encounter for this time is Finished!");
            System.out.println("-----------------------------------------------------------------------------------------");
            EncounterHistory encounterHistory = c.getEncounterHistory();
            Encounter fixedEncounter1 = encounterHistory.newEncounter(per);
            Event ev = fixedEncounter1.newEvent(sit,currentdate);
            String diseaInteractive = pickRandomDiseaseType(diseaseType,8,2);
            Boolean contagiousTypeInteractive = checkDiseaseType(diseaInteractive, confirmedType);
            Diagnosis diagInteractive = fixedEncounter1.newDiagnosis(diseaInteractive, contagiousTypeInteractive);
            System.out.println("Any new patient?");
            String input5 = sc.next();
            String quitCode = "no";
            if (quitCode.equals(input5.toLowerCase())){
                isFinished = true;
            } 

        }




    }





    static int getRandom(int lower, int upper) {
        Random r = new Random();
    
        // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
        // numbers from 10 to 15
        // I will have result = 10 + nextInt(5)
        int randomInt = lower + r.nextInt(upper - lower + 1);
        return randomInt;
      }

    public static ArrayList<String> generateFakerName(int patientCount){
        ArrayList<String> fakerName = new ArrayList<String>();
        Faker faker = new Faker();
        for (int i =0; i < patientCount; i++){
            fakerName.add(faker.name().fullName());
        }
        return fakerName;
    }

    public static ArrayList<Integer> generateFakerAge(int patientCount){
        Faker faker = new Faker();
        ArrayList<Integer> fakerAge = new ArrayList<Integer>();
        for (int i =0; i < patientCount; i++){
            fakerAge.add(faker.random().nextInt(80));
        }
        return fakerAge;
    }

    public static String pickRandomDiseaseType(String[] diseaseType,int x,int y){
        if (diseaseType.length == 0) return null;
        Random s = new Random();
        int d = s.nextInt(11);
        if (d > x){
            Random r = new Random();
            int randomIndex = r.nextInt(y);
            return diseaseType[randomIndex];
        } else {
            Random r = new Random();
            int randomIndex = r.nextInt(diseaseType.length-y)+y;
            return diseaseType[randomIndex];
        }
    }


    public static boolean checkDiseaseType(String diseasety,String[] confirmedtype){
        for (int i = 0; i < confirmedtype.length; i++){
            if (diseasety.equals(confirmedtype[i]))  return true;
        }
        return false;
    }
        
    public static long randomDate(String startDate) throws Exception{
        Random r = new Random();
        Date d = new Date();
        long after = d.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date d2 = sdf.parse(startDate);
        long before = d2.getTime();
        long randomDate = (long) (before + (r.nextFloat()*(after-before+1)));
        return randomDate;
        }
    }


