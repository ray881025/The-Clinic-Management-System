/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Patient.ClinicalHistory;

import java.util.ArrayList;


public class AlergyHistory {
    
    ArrayList<Alergy> alergies;

    public ArrayList<Alergy> getAlergies() {
        return alergies;
    }

    public AlergyHistory() {
        alergies = new ArrayList<Alergy>();
    }
}
