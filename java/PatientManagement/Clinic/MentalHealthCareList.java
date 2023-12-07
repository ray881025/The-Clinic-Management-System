package PatientManagement.Clinic;

import java.util.ArrayList;

public class MentalHealthCareList {

    ArrayList<MentalHealthCare> mentalHealthCares;
    
    public ArrayList<MentalHealthCare> getMentalHealthCares() {
        return mentalHealthCares;
    }

    public MentalHealthCareList(){
        mentalHealthCares = new ArrayList<MentalHealthCare>();
    }

    
}
