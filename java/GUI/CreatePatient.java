package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreatePatient extends JFrame{
    JTextField fullnameInput, ageInput;
    JComboBox<String> allergyDropdown, vaccinationDropdown, locationDropdown, siteDropdown ;
    JButton addBtn, backBtn;
    JLabel title, fullname, allergy, vaccination, age, location, site;
    String[] allergyOption = {"", "HIV", "Abola", "Stomachache", "Fever"}
            ,vaccinationOption = {"", "BNT", "AZ", "Moderna"}
            ,locationOption = {"", "Malden", "Allston", "Cambridge"}
            ,siteOption = {""};

    public CreatePatient() {
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
                    siteOption = new String[]{"160 Pleasant", "150 Exchange", "240 Kyle", "360 Ops1", "420 Quil"};
                }else if("Allston".equals(selected)){
                    siteOption = new String[]{"122 kkILLl", "33322 sdwds", "2211 dws"};
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

    private void switchToHome() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // Close the current frame
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