import java.util.Comparator;

import PatientManagement.Clinic.Event;
import PatientManagement.Clinic.Site;
import PatientManagement.Patient.Encounters.Encounter;

public class EventComparator implements Comparator<Event>{

    @Override
    public int compare(Event o1, Event o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
    
}
