/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;


public class VaccinationHistory {

    ArrayList<Vaccination> vaccinations;

    public ArrayList<Vaccination> getVaccinations() {
        return vaccinations;
    }

    public VaccinationHistory(){
        vaccinations = new ArrayList<Vaccination>();
    }

}