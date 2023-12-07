/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PatientManagement.Clinic;

import java.util.ArrayList;


public class Site {

    String name;
    ArrayList<Event> eventlists;
    ArrayList<Event> eventSicklists;

    public Site(String n) {
        name = n;
        eventlists = new ArrayList<Event>();
    }

    public void addEvent(Event event) {
        eventlists.add(event);
    }

    public String getName() {
        return name;
    }


    public ArrayList<Event> getEventlists() {
        return eventlists;
    }

}
