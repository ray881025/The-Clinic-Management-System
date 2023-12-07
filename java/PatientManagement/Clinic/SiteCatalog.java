/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;
import java.util.Random;


public class SiteCatalog {
    String name;
    Location l;
    ArrayList<Site> sites;

    public SiteCatalog(Location location) {
        l = location;
        sites = new ArrayList<Site>();
        this.name = l.getGPS() + "catalog";
    }

    public ArrayList<Site> newSite(String[] n) {
        for (int i = 0;i <n.length;i++){
            Site s = new Site(n[i]);
            sites.add(s);
        }
        return sites;
    }

    public Site pickRandomSite(){
        if (sites.size() == 0) return null;
        Random r = new Random();
        int randomIndex = r.nextInt(sites.size());
        return sites.get(randomIndex);
    }
    
    public String getName() {
        return name;
    }

    public Location getL() {
        return l;
    }

    public ArrayList<Site> getSites() {
        return sites;
    }

    public Site findSite(String sitename) {
        // search for a site object by sitename;
        for (Site s: sites ){
            if(s.getName().equals(sitename)) return s;
        }
        return null;
    }

    public SiteReport generateSiteSickReport(){
        SiteReport siteReport = new SiteReport();
        
        for (Site s:sites) {
            SiteSummary sps = new SiteSummary(s);
            sps.pullOutEvent(s);
            sps.calculateSitePatientTotalForThatMonth();
            sps.calculatePatientTotalForThatMonth();
            siteReport.addSiteSummary(sps);
        }
        return siteReport;
    }
}
