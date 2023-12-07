import java.util.Comparator;
import PatientManagement.Patient.Encounters.Encounter;

public class EncounterComparator implements Comparator<Encounter>{

    @Override
    public int compare(Encounter o1, Encounter o2) {
        return o1.getEvent().getDate().compareTo(o2.getEvent().getDate());
    }
    
}
