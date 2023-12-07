package PatientManagement.Clinic;

import java.text.DecimalFormat;
import java.util.ArrayList;

import PatientManagement.Patient.Patient;
import PatientManagement.Patient.Encounters.Encounter;

public class SiteSummary {
    Site site;
    ArrayList<Event> eventlists;
    ArrayList<String> tempLists; //return the month ocurred in the eventlis
    ArrayList<Integer> sickPatientTotalForThatMonth;
    ArrayList<Integer> patientTtForThatMonth;
    ArrayList<String> Percentage;

    

    public ArrayList<Event> getEventlists() {
        return eventlists;
    }


    public void setEventlists(ArrayList<Event> eventlists) {
        this.eventlists = eventlists;
    }


    public ArrayList<Integer> getPatientTtForThatMonth() {
        return patientTtForThatMonth;
    }


    public void setPatientTtForThatMonth(ArrayList<Integer> patientTtForThatMonth) {
        this.patientTtForThatMonth = patientTtForThatMonth;
    }


    public SiteSummary(Site s){
        this.site = s;
        eventlists = s.getEventlists();
        tempLists = new ArrayList<String>();
        sickPatientTotalForThatMonth = new ArrayList<Integer>();
        patientTtForThatMonth = new ArrayList<Integer>();
        Percentage = new ArrayList<String>();
    }


    public ArrayList<String> pullOutEvent(Site s){
        for (Event e : eventlists){
            if (!tempLists.contains(e.getsubDate())){
                tempLists.add(e.getsubDate());
            }
        }
        return tempLists;
    }

    public ArrayList<Integer> calculateSitePatientTotalForThatMonth(){
        int j = 0;
        for (int i = 0; i < tempLists.size();i++){
            int sicksum = 0;
            ArrayList<String> patientNoDuplicate = new ArrayList<String>();
            boolean flag = true;
            while (flag &&(j<eventlists.size())){
                Event s = eventlists.get(j);
                Patient p = s.getEncounter().getPatient();
                if (tempLists.get(i).equals(s.getsubDate())){    //Check if it is the same month
                    if (s.getEncounter().isSick()){ //check if a patient has been in that place for twice
                        if (!patientNoDuplicate.contains(p.getPerson().getPersonId())){
                            sicksum += 1;
                            patientNoDuplicate.add(p.getPerson().getPersonId());
                        }
                    }
                    j++ ;
                } else {
                    flag = false;                                   //if it is not the same month
                    sickPatientTotalForThatMonth.add(sicksum);
                }                                     
            }
            if (j == eventlists.size()) sickPatientTotalForThatMonth.add(sicksum);
        }
        return sickPatientTotalForThatMonth;
    }

    public ArrayList<Integer> calculatePatientTotalForThatMonth(){
        int j = 0;
        for (int i = 0; i < tempLists.size();i++){
            int patientsum = 0;
            ArrayList<String> patientNoDuplicate = new ArrayList<String>();
            boolean flag = true;
            while (flag &&(j<eventlists.size())){
                Event s = eventlists.get(j);
                Patient p = s.getEncounter().getPatient();
                if (tempLists.get(i).equals(s.getsubDate())){    //Check if it is the same month
                        if (!patientNoDuplicate.contains(p.getPerson().getPersonId())){
                            patientsum += 1;
                            patientNoDuplicate.add(p.getPerson().getPersonId());
                        }
                    j++ ;
                } else {
                    flag = false;                                   //if it is not the same month
                    patientTtForThatMonth.add(patientsum);
                }                                     
            }
            if (j == eventlists.size()) patientTtForThatMonth.add(patientsum);
        }
        return patientTtForThatMonth;
    }
    
    //public int getConfirmedTotal(){
        //int sum = 0;
        //for (Event e : eventlists){
           // if (e.getDate()
       // }
    //}

    public ArrayList<String> getTempLists() {
        return tempLists;
    }

    public void printSiteSummary(){
        System.out.println();
        System.out.println("Site name: " + site.getName());
        // System.out.println(tempLists.toString());
        // System.out.println(sickPatientTotalForThatMonth.toString());
        // System.out.println(patientTtForThatMonth.toString());
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("|Month\t");
        System.out.print(" \t\t");
        for(String s : tempLists){
            System.out.print( "|" + s + " \t");
        }
        System.out.print("|");
        for(int i = tempLists.size();i<4; i++){
            System.out.print("\t\t" + "|");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("|Infection Incidents\t");
        for(int i : sickPatientTotalForThatMonth){
            System.out.print( "|" + i + "\t\t");
        }
        System.out.print("|");
        for(int i = tempLists.size();i<4; i++){
            System.out.print("\t\t" + "|");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("|Total Incidents\t");
        for(int n : patientTtForThatMonth ){
            System.out.print( "|" + n + "\t\t");
        }
        System.out.print("|");
        for(int i = tempLists.size();i<4; i++){
            System.out.print("\t\t" + "|");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.print("|Infected Percentage\t");
        for (int i = 0; i < tempLists.size();i++){

            int s = sickPatientTotalForThatMonth.get(i);
            int p = patientTtForThatMonth.get(i);
            double Percentage = (double)s/p;
            //System.out.print( "|" + "%.2f" ,   + "\t\t");
            System.out.print("|");
            Percentage = 100 * Percentage;
            System.out.printf("%.2f", Percentage );
            //System.out.print("%");
            System.out.print("\t\t");
        }
        System.out.print("|");
        for(int i = tempLists.size();i<4; i++){
            System.out.print("\t\t" + "|");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------");


        // System.out.println(patientTtForThatMonth.toString());
        // for (int i = 0; i < tempLists.size();i++){
        //     System.out.println(tempLists.get(i));
        //     System.out.println(sickPatientTotalForThatMonth.get(i));
        // }
    }

    public ArrayList<Integer> getSickPatientTotalForThatMonth() {
        return sickPatientTotalForThatMonth;
    }

    public ArrayList<String> getPercentage() {
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        for (int i = 0; i < tempLists.size();i++){

            int s = sickPatientTotalForThatMonth.get(i);
            int p = patientTtForThatMonth.get(i);
            double percentageValue = (double)s/p;
            //System.out.print( "|" + "%.2f" ,   + "\t\t");
            String percentageString = String.format("%.2f", percentageValue*100);
            Percentage.add(percentageString+"%");
            //System.out.print("%");
        }
        return Percentage;
    }
}
