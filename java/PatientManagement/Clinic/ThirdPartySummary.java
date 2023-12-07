package PatientManagement.Clinic;

public class ThirdPartySummary {

    Location location;
    int numberOfMentalHealthCare;
    int numberOfPharmacy;
    int totalNumberOfThirdParty;

    public ThirdPartySummary(Location location){
        this.location = location;
        numberOfMentalHealthCare = location.getNumberOfMentalHealthCare();
        numberOfPharmacy = location.getNumberOfPharmacy();
        totalNumberOfThirdParty = numberOfMentalHealthCare + numberOfPharmacy;
    }

    public void printReport(){
        System.out.println("-------------------------------------------------------------------------------");
        System.out.println(location.getGPS());
        System.out.println("Number of mental health care: " + numberOfMentalHealthCare);
        System.out.println("Number of pharmacy: " + numberOfPharmacy);
        System.out.println("Total number of third party: " + totalNumberOfThirdParty);
    }
    
}
