package PatientManagement.Patient.Encounters;

import java.util.Random;

public class VitalSign {

    int heartRate;
    int respiratoryRate ;
    int weight;
    int bloodPressure;

    public VitalSign(int heartRate, int respiratoryRate, int weight, int bloodPressure) {
        this.heartRate = getRandom(70,heartRate);
        this.respiratoryRate = getRandom(20, respiratoryRate);
        //System.out.println("1111null");
        this.weight = getRandom(50, weight);
        this.bloodPressure = getRandom(80, bloodPressure);
    }

    public void printVitalSigns(){
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.println("Doing the routine examination:");
        System.out.println("Respiratory Rate: " + respiratoryRate);
        System.out.println("Hear Rate:" + heartRate);
        System.out.println("Blood Pressure:" + bloodPressure);
        System.out.println("Weight(kg):" + weight);
        System.out.println("-----------------------------------------------------------------------------------------");
    }

    public int getRandom(int lower, int upper) {
        Random r = new Random();
    
        // nextInt(n) will return a number from zero to 'n'. Therefore e.g. if I want
        // numbers from 10 to 15
        // I will have result = 10 + nextInt(5)
        int randomInt = lower + r.nextInt(upper - lower + 1);
        return randomInt;
      }
    

    
}
