package PatientManagement.Patient.Encounters;

import java.util.ArrayList;

public class SickPatientReport {
    ArrayList<SickPatientSummary> sickPatientSummaryList;

    ArrayList<String> sickName;
    ArrayList<Integer> sickNumber;
    ArrayList<String> sickAddress;
    public SickPatientReport(){
        sickPatientSummaryList = new ArrayList<SickPatientSummary>();
        sickName = new ArrayList<String>();
        sickNumber =  new ArrayList<Integer>();
        sickAddress = new ArrayList<String>();
    }

    public void addSickPatientSummary(SickPatientSummary sps){
        sickPatientSummaryList.add(sps);
    }

    public void printSickPatientReport(){
        System.out.println();
        for (SickPatientSummary sps: sickPatientSummaryList){
            sps.sickPatientLoc();
            sps.printSickPatientLoc();
        }
    }

    public void generateSickPatientData(){

        for (SickPatientSummary sps: sickPatientSummaryList){
            sps.sickPatientLoc();
            if (sps.getLocChange().size() > 0){
                sickName.add(sps.getP().getPerson().getPersonId());
                sickNumber.add(sps.getLocChange().size());
                sickAddress.add(sps.getLocChange().toString());
            }

        }
    }

    public ArrayList<Integer> getSickNumber() {
        return sickNumber;
    }

    public ArrayList<String> getSickAddress() {
        return sickAddress;
    }

    public ArrayList<String> getSickName() {
        return sickName;
    }

}
