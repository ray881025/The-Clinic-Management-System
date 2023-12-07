package GUI;


import PatientManagement.Clinic.*;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.Person;
import PatientManagement.Persona.PersonDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CreatePatient extends JFrame{
    JTextField fullnameInput, ageInput;
    JComboBox<String> allergyDropdown, vaccinationDropdown, locationDropdown, siteDropdown ;
    JButton addBtn, backBtn;
    JLabel title, fullname, allergy, vaccination, age, location, site;
    String[] allergyOption = {"", "Food allergies","Seasonal allergies","Drug allergies","N/A"}
            ,vaccinationOption = {"", "BNT", "AZ", "Moderna"}
            ,locationOption = {"", "Malden", "Allston", "Cambridge"}
            ,siteOption = {""};

    public CreatePatient(Clinic c) {
        setTitle("Create New Patient");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10));
        setTitlePanel();
        add(Box.createVerticalStrut(20)); //add 30 pixels gap
        setInputPanel();
        add(Box.createVerticalStrut(20));
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Logic For PatientCreate
        addBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newpatientName = getFullnameInput();
                int newpatientAge = getAgeInput();
                PatientDirectory patDirectory = c.getPatientDirectory();
                PersonDirectory perDirectory = c.getPersonDirectory();

                //Input Persons Name and Age
                Person newPers = perDirectory.newPerson(newpatientName,newpatientAge);
                Patient crp_per = patDirectory.newPatient(newPers);

                //Input vaccination and allergy
                crp_per.newAlergy(getAllergyDropdown());
                crp_per.newVaccination(getVaccinationDropdown());

                //Input Date automatically
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date date = new Date(System.currentTimeMillis());
                String currentdate = simpleDateFormat.format(date);



                //Input Location and site
                boolean findSite = false;
                Location loc = new Location(null);
                Site sit = new Site(null);
                LocationList locationList = c.getLocationList();
                loc = locationList.findLocation(getLocationDropdown());
                SiteCatalog stc = loc.getSiteCatalog();
                sit = stc.findSite(getSiteDropdown());




                //Input event
                EncounterHistory encounterHistory = c.getEncounterHistory();
                Encounter fixedEncounter1 = encounterHistory.newEncounter(crp_per);
                Event ev = fixedEncounter1.newEvent(sit,currentdate);

                JOptionPane.showMessageDialog(null,"Congratulations! Add Successfully!","Success",JOptionPane.INFORMATION_MESSAGE);
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home h = new Home(c);
            }
        });
    }

    private  void  setTitlePanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title = new JLabel("Patient Details");
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title);

        getContentPane().add(jp);
    }

    private void setInputPanel(){
        JPanel jp = new JPanel(new GridLayout(6,2,0,10));
        jp.setMaximumSize(new Dimension(450, 200));
        jp.setMinimumSize(new Dimension(450, 200));

        fullname = new JLabel("Full Name");
        allergy = new JLabel("Allergy");
        age = new JLabel("Age");
        vaccination = new JLabel("Vaccination");
        location = new JLabel("Location");
        site = new JLabel("Site");

        fullnameInput = new JTextField();
        ageInput = new JTextField();
        allergyDropdown =  new JComboBox<>(allergyOption);
        vaccinationDropdown = new JComboBox<>(vaccinationOption);
        locationDropdown = new JComboBox<>(locationOption);
        siteDropdown = new JComboBox<>(siteOption);

        locationDropdown.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selected = (String)locationDropdown.getSelectedItem();
                if("Malden".equals(selected)){
                    siteOption = new String[]{"160 Pleasant", "150 Exchange", "240 Kyle", "360 Opsl", "420 Quil"};
                }else if("Allston".equals(selected)){
                    siteOption = new String[]{"122 kkllll", "33322 sdwds", "2211 dws"};
                }else if("Cambridge".equals(selected)){
                    siteOption = new String[]{"133 Oxford St.", "288 Cambridge St.", "888 Oxford St."};
                }else {
                    siteOption = new String[]{};
                }

                // Clear existing items in siteDropdown
                siteDropdown.removeAllItems();

                // Add new items to siteDropdown
                for (String site : siteOption) {
                    siteDropdown.addItem(site);
                }
            }
        });

        jp.add(fullname);
        jp.add(fullnameInput);
        jp.add(age);
        jp.add(ageInput);
        jp.add(allergy);
        jp.add(allergyDropdown);
        jp.add(vaccination);
        jp.add(vaccinationDropdown);
        jp.add(location);
        jp.add(locationDropdown);
        jp.add(site);
        jp.add(siteDropdown);

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        addBtn = new JButton("Add");
        addBtn.setForeground(Color.white);
        addBtn.setBackground(Color.blue);
        addBtn.setOpaque(true); //Set transparency for button
        addBtn.setBorderPainted(false);

        backBtn = new JButton("Go Back");
        backBtn.setForeground(Color.white);
        backBtn.setBackground(Color.black);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);

        jp.add(backBtn);
        jp.add(addBtn);



        getContentPane().add(jp);
    }



    public JButton getAddBtn() {
        return addBtn;
    }

    public JButton getBackBtn() {
        return backBtn;
    }

    public String getFullnameInput() {
        return fullnameInput.getText();
    }

    public int getAgeInput() {
        return Integer.valueOf(ageInput.getText());
    }

    public String getAllergyDropdown() {
        return (String)allergyDropdown.getSelectedItem();
    }

    public String getVaccinationDropdown() {
        return (String)vaccinationDropdown.getSelectedItem();
    }

    public String getLocationDropdown() {
        return (String)locationDropdown.getSelectedItem();
    }

    public String getSiteDropdown() {
        return (String)siteDropdown.getSelectedItem();
    }
}