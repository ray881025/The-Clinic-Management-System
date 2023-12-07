package GUI;

import PatientManagement.Clinic.*;
import PatientManagement.Clinic.Event;
import PatientManagement.Patient.ClinicalHistory.Alergy;
import PatientManagement.Patient.ClinicalHistory.Vaccination;
import PatientManagement.Patient.Encounters.Encounter;
import PatientManagement.Patient.Encounters.EncounterHistory;
import PatientManagement.Patient.Patient;
import PatientManagement.Persona.PersonDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ViewPatient extends JFrame {
    JLabel title, name, age, allergy, vaccination, lastDate, lastLocation;
    JTextField searchInput, nameInput, ageInput, allergyInput, vaccinationInput, dateInput, locationInput;
    JButton searchBtn, contBtn, backBtn;

    public ViewPatient(Clinic c){
        setTitle("View Patient");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10));
        setTitlePanel();
        setSearchPanel();
        add(Box.createVerticalStrut(10));
        setInputPanel();
        add(Box.createVerticalStrut(30));
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Logic For Search Button here
        //Only if patient was found then we can continue
        PatientDirectory patDirectory = c.getPatientDirectory();
        PersonDirectory perDirectory = c.getPersonDirectory();
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vp_patientName = getSearchInput();
                Patient vp_per = patDirectory.findPatient(vp_patientName);


                if (vp_per != null) {
                    int ind = vp_per.getEncounters().size();

                    PatientManagement.Clinic.Event lastseen = vp_per.getEncounters().get(ind-1).getEvent();


                    ArrayList<Alergy> al = vp_per.getAlergyhistory().getAlergies();
                    ArrayList<Vaccination> v = vp_per.getVachistory().getVaccinations();
                    String name = vp_per.getPerson().getPersonId();
                    int age = vp_per.getPerson().getAge();

                    getNameInput().setText(name);
                    getAgeInput().setText(String.valueOf(age));

                    if (al.size() != 0) {
                        getAllergyInput().setText(al.get(0).getName());
                    } else {
                        getAllergyInput().setText("N / A");
                    }

                    if (v.size() != 0) {
                        getVaccinationInput().setText(v.get(0).getName());
                    } else {
                        getVaccinationInput().setText("N / A");
                    }

                    getDateInput().setText(lastseen.getDate());
                    getLocationInput().setText(lastseen.getSite().getName());

                    System.out.println(vp_per.getPerson().getPersonId());


                    //if not find person then set N / A
                } else {
                    getNameInput().setText("N / A");
                    getAgeInput().setText("N / A");
                    getAllergyInput().setText("N / A");
                    getVaccinationInput().setText("N / A");
                    getDateInput().setText("N / A");
                    getLocationInput().setText("N / A");
                }
            }
        });

        contBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String vp_patientName = getSearchInput();
                Patient vp_per = patDirectory.findPatient(vp_patientName);
                //Logic For continue Button here
                if (vp_per != null){
                    dispose();
                    DiseaseType dst = new DiseaseType(vp_per, c);
                } else {
                    JOptionPane.showMessageDialog(null,"Please use an existing patient and then continue","Error",JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        //Logic For Back Button Here
        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                Home home = new Home(c);
            }
        });
    }

    private void setSearchPanel(){
        JPanel jp = new JPanel(new GridLayout(1,2));
        jp.setMaximumSize(new Dimension(300, 20));
        jp.setMinimumSize(new Dimension(300, 20));

        searchInput = new JTextField();
        searchBtn = new JButton("Search");

        jp.add(searchInput);
        jp.add(searchBtn);

        getContentPane().add(jp);
    }
    private void setTitlePanel(){
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

        name = new JLabel("Full Name");
        age = new JLabel("Age");
        allergy = new JLabel("Allergy");
        vaccination = new JLabel("Vaccination");
        lastDate = new JLabel("Last Seen Date");
        lastLocation = new JLabel("Last Seen Location");

        nameInput = new JTextField();
        nameInput.setEnabled(false);
        ageInput = new JTextField();
        ageInput.setEnabled(false);
        allergyInput = new JTextField();
        allergyInput.setEnabled(false);
        vaccinationInput = new JTextField();
        vaccinationInput.setEnabled(false);
        dateInput = new JTextField();
        dateInput.setEnabled(false);
        locationInput = new JTextField();
        locationInput.setEnabled(false);

        jp.add(name);
        jp.add(nameInput);
        jp.add(age);
        jp.add(ageInput);
        jp.add(allergy);
        jp.add(allergyInput);
        jp.add(vaccination);
        jp.add(vaccinationInput);
        jp.add(lastDate);
        jp.add(dateInput);
        jp.add(lastLocation);
        jp.add(locationInput);

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        contBtn = new JButton("continue");
        contBtn.setForeground(Color.white);
        contBtn.setBackground(Color.blue);
        contBtn.setOpaque(true); //Set transparency for button
        contBtn.setBorderPainted(false);

        backBtn = new JButton("Go Back");
        backBtn.setForeground(Color.white);
        backBtn.setBackground(Color.black);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);

        jp.add(backBtn);
        jp.add(contBtn);





        getContentPane().add(jp);
    }




    public String getSearchInput() {
        return searchInput.getText();
    }

    public JTextField getAgeInput() {
        return ageInput;
    }

    public JTextField getAllergyInput() {
        return allergyInput;
    }

    public JTextField getDateInput() {
        return dateInput;
    }

    public JTextField getLocationInput() {
        return locationInput;
    }

    public JTextField getNameInput() {
        return nameInput;
    }

    public JTextField getVaccinationInput() {
        return vaccinationInput;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public JButton getContBtn() {
        return contBtn;
    }
}
