/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.lang.reflect.Array;
import java.util.ArrayList;


public class LocationList {
    ArrayList<Location> locations;

    ArrayList<String> locationName;

    ArrayList<String> localServiceName;
    ArrayList<String> localServiceAddress;
    ArrayList<String> localServiceTelphone;



    public LocationList() {
        locations = new ArrayList<Location>();
    }

    public Location newLocation(String name){
        Location location = new Location(name);
        locations.add(location);
        return location;
    }

    
    public Location findLocation(String locname) {
        // search for a location object by locname;
        for (Location loc:locations){
            if(loc.getGPS().equals(locname)) return loc;
        }
        return null;
    }

    public ThirdPartyReport generateThirdPartyReport(){
        ThirdPartyReport tpr = new ThirdPartyReport();
        for(Location l : locations){
            ThirdPartySummary tps = new ThirdPartySummary(l);
            tpr.getThirdPartySummaries().add(tps);
        }
        return tpr;
    }

    public void generatelocationServiceReport() {
        locationName = new ArrayList<String>();
        localServiceName = new ArrayList<String>();
        localServiceTelphone = new ArrayList<String>();
        localServiceAddress = new ArrayList<String>();
        for (Location l : locations) {
            for (MentalHealthCare mhc : l.getMentalHealthCares()) {
                locationName.add(l.getGps());
                localServiceName.add(mhc.getName());
                localServiceTelphone.add(mhc.getTelphone());
                localServiceAddress.add(mhc.getAddress());
            }
        }
    }

    public ArrayList<Location> getLocations() {
        return locations;
    }

    public ArrayList<String> getLocationName() {
        return locationName;
    }

    public ArrayList<String> getLocalServiceName() {
        return localServiceName;
    }

    public ArrayList<String> getLocalServiceTelphone() {
        return localServiceTelphone;
    }

    public ArrayList<String> getLocalServiceAddress() {
        return localServiceAddress;
    }
}
