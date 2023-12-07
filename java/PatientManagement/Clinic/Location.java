/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;






public class Location {
    String gps;
    SiteCatalog siteCatalog;
    ArrayList<MentalHealthCare> mentalHealthCares;
    ArrayList<Pharmacy> pharmacies;


    public SiteCatalog getSiteCatalog() {
        return siteCatalog;
    }

    public Location(String g) {
        gps = g;
        siteCatalog= new SiteCatalog(this);
        mentalHealthCares = new ArrayList<MentalHealthCare>();
        pharmacies = new ArrayList<Pharmacy>();
    }

    public MentalHealthCare newMentalHealthCare(String name,String telphone,String address, MentalHealthCareList mhcl){
        MentalHealthCare mhc = new MentalHealthCare(name,telphone,address,mhcl);
        mentalHealthCares.add(mhc);
        mhcl.getMentalHealthCares().add(mhc);
        return mhc;
    }

    public Pharmacy newPharmacy(String name, PharmacyList pl){
        Pharmacy p = new Pharmacy(name, pl);
        pharmacies.add(p);
        pl.getPharmacies().add(p);
        return p;
    }

    public Object getGPS() {
        return gps;
    }

    public String getGps() {
        return gps;
    }

    public int getNumberOfMentalHealthCare(){
        return mentalHealthCares.size();
    }

    public int getNumberOfPharmacy(){
        return pharmacies.size();
    }

    public ArrayList<MentalHealthCare> getMentalHealthCares() {
        return mentalHealthCares;
    }
    
    public ArrayList<Pharmacy> getPharmacies() {
        return pharmacies;
    }

    
}
