package PatientManagement.Clinic;

public class MentalHealthCare {

    String name;
    String telphone;
    String address;
    MentalHealthCareList mentalHealthCareList;


    public MentalHealthCare(String name, String telphone,String address,MentalHealthCareList mhcl){
        //this.location = location;
        this.name = name;
        this.telphone = telphone;
        this.address = address;
        mentalHealthCareList = mhcl;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getTelphone() {
        return telphone;
    }

    public void printInfo(){
        System.out.println(name);
    }
    
}
