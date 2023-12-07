package PatientManagement.Clinic;

import java.util.ArrayList;

public class PharmacyList {
    
    ArrayList<Pharmacy> pharmacies;
    
    public ArrayList<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    public PharmacyList(){
        pharmacies = new ArrayList<Pharmacy>();
    }
    
}
