package GUI;

import GUI.ViewPatient;
import PatientManagement.Clinic.*;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Patient.Patient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DiseaseType extends JFrame {
    JLabel title, name, previousType, diseaseType, location, site;
    JTextField nameInput, previousInput;
    JComboBox<String> diseaseDropdown, locationDropdown, siteDropdown;
    JButton addBtn, backBtn;
    String[] diseaseOption = {"", "HIV", "Abola", "Stomachache", "Fever"}
            ,locationOption = {"", "Malden", "Allston", "Cambridge"}
            ,siteOption = {""};

    public DiseaseType(Patient vp_per, Clinic c){
        setTitle("Disease Type");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10)); //add 10 pixels gap
        setTitlePanel();
        add(Box.createVerticalStrut(20)); //add 30 pixels gap
        setInputPanel();
        add(Box.createVerticalStrut(20)); //add 20 pixels gap
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Logic For set the intial text
        getNameInput().setText(vp_per.getPerson().getPersonId());
        ArrayList<String> diaList = vp_per.getConfirmedDiseaseType();

        //Combine the disease
        StringBuilder combinedString = new StringBuilder();
        for (String str : diaList) {
            combinedString.append(str+" ");
        }
        String result = combinedString.toString();
        getPreviousInput().setText(result);

        getAddBtn().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //Set the current time
                SimpleDateFormat simpleDateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
                Date date1 = new Date(System.currentTimeMillis());
                String currentdate1 = simpleDateFormat1.format(date1);

                //get people last event
                int ind = vp_per.getEncounters().size();
                PatientManagement.Clinic.Event lastseen = vp_per.getEncounters().get(ind-1).getEvent();

                if (lastseen.getDate().equals(currentdate1)) {
                    //if the patient is first seen then get last encounter and then get its diseasetype
                    String diseaseType = getDiseaseDropdown();
                    if (diseaseType.equals("HIV")||diseaseType.equals("Abola")) {
                        vp_per.getEncounters().get(0).newDiagnosis(diseaseType,true);
                        JOptionPane.showMessageDialog(null,"Warning! This Patient should be doing following medical tests","Success",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        vp_per.getEncounters().get(0).newDiagnosis(diseaseType,false);
                        JOptionPane.showMessageDialog(null,"This Patient is healthy","Success",JOptionPane.INFORMATION_MESSAGE);
                    }

                } else {
                    //Input location
                    String diseaseType = getDiseaseDropdown();
                    Location loc2 = new Location(null);
                    Site sit2 = new Site(null);
                    LocationList locationList2 = c.getLocationList();
                    loc2 = locationList2.findLocation(getLocationDropdown());
                    SiteCatalog stc2 = loc2.getSiteCatalog();
                    sit2 = stc2.findSite(getSiteDropdown());


                    EncounterHistory encounterHistory = c.getEncounterHistory();
                    Encounter fixedEncounter1 = encounterHistory.newEncounter(vp_per);

                    if (diseaseType.equals("HIV")||diseaseType.equals("Abola")) {
                        fixedEncounter1.newDiagnosis(diseaseType,true);
                        JOptionPane.showMessageDialog(null,"Warning! This Patient should be doing following medical tests!","Success",JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        fixedEncounter1.newDiagnosis(diseaseType,false);
                        JOptionPane.showMessageDialog(null,"This Patient is healthy!","Success",JOptionPane.INFORMATION_MESSAGE);
                    }
                    Event ev = fixedEncounter1.newEvent(sit2,currentdate1);
                }

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

    private void setTitlePanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title = new JLabel("Disease Type");
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title);

        getContentPane().add(jp);
    }

    private void setInputPanel(){
        JPanel jp = new JPanel(new GridLayout(5,2,0,10));
        jp.setMaximumSize(new Dimension(450, 200));
        jp.setMinimumSize(new Dimension(450, 200));

        name = new JLabel("Full Name");
        previousType = new JLabel("Previous Type");
        diseaseType = new JLabel("Disease Type");
        location = new JLabel("Location");
        site = new JLabel("Site");

        nameInput = new JTextField();
        nameInput.setEnabled(false);
        previousInput = new JTextField();
        previousInput.setEnabled(false);
        diseaseDropdown = new JComboBox<>(diseaseOption);
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

        jp.add(name);
        jp.add(nameInput);
        jp.add(previousType);
        jp.add(previousInput);
        jp.add(diseaseType);
        jp.add(diseaseDropdown);
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

        backBtn = new JButton("Go Home");
        backBtn.setForeground(Color.white);
        backBtn.setBackground(Color.black);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);

        jp.add(backBtn);
        jp.add(addBtn);



        getContentPane().add(jp);
    }

    public JTextField getNameInput() {
        return nameInput;
    }

    public JButton getAddBtn() {
        return addBtn;
    }

    public String getDiseaseDropdown() {
        return (String)diseaseDropdown.getSelectedItem();
    }

    public JTextField getPreviousInput() {
        return previousInput;
    }

    public String getLocationDropdown() {
        return (String)locationDropdown.getSelectedItem();
    }

    public String getSiteDropdown() {
        return (String)siteDropdown.getSelectedItem();
    }


}