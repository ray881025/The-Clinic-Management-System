package PatientManagement.Clinic;

public class Pharmacy {

    String name;
    PharmacyList pharmacyList;

    public Pharmacy(String name, PharmacyList pl){
        this.name = name;
        pharmacyList = pl;
    }

    public String getName() {
        return name;
    }

    public void printInfo(){
        System.out.println(name);
    }
    
    
}
