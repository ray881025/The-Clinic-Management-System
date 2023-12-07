package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewPatient extends JFrame {
    JLabel title, name, age, allergy, vaccination, lastDate, lastLocation;
    JTextField searchInput, nameInput, ageInput, allergyInput, vaccinationInput, dateInput, locationInput;
    JButton searchBtn, contBtn, backBtn;

    public ViewPatient(){
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



        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToHome();
            }
        });

        getContentPane().add(jp);
    }

    private void switchToHome() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // Close the current frame
    }

    public void switchToDiseaseType(){
        DiseaseType type = new DiseaseType();
        type.setVisible(true);
        dispose();
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
