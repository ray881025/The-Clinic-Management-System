package PatientManagement.Clinic;

import java.util.ArrayList;

public class ThirdPartyReport {

    ArrayList<ThirdPartySummary> thirdPartySummaries;

    public ArrayList<ThirdPartySummary> getThirdPartySummaries() {
        return thirdPartySummaries;
    }

    public ThirdPartyReport(){
        thirdPartySummaries = new ArrayList<ThirdPartySummary>();
    }

    public void printReport(){
        System.out.println(" ");
        System.out.println(" ");
        System.out.println("Third Party Report");
        for(ThirdPartySummary tps : thirdPartySummaries){
            tps.printReport();
        }
    }
    
}
