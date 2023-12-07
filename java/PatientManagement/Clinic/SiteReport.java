package PatientManagement.Clinic;

import java.util.ArrayList;

public class SiteReport {
    ArrayList<SiteSummary> siteSummaries;
    ArrayList<String> longestTempList;
    
    public ArrayList<SiteSummary> getSiteSummaries() {
        return siteSummaries;
    }

    public void setSiteSummaries(ArrayList<SiteSummary> siteSummaries) {
        this.siteSummaries = siteSummaries;
    }

    public SiteReport(){
        siteSummaries = new ArrayList<SiteSummary>();
        longestTempList = new ArrayList<String>();

    }

    public void addSiteSummary(SiteSummary ss){
        siteSummaries.add(ss);
    }

    public void printSiteReport(){
        
        System.out.print("-----------------------------------------------------------------------------------------");
        System.out.println("--------------------");
        //System.out.println("Site Summary Report");
    

        for (SiteSummary ss: siteSummaries){
            ss.printSiteSummary();
            //System.out.println();
        }
    }

    public SiteSummary returnReportBySite(String siteName) {
        for (SiteSummary ss: siteSummaries) {
            if (siteName.equals(ss.site.getName())) {
                return ss;
            }
        }
        return null;
    }
}
