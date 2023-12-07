package GUI;

import GUI.ViewPatient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiseaseType extends JFrame {
    JLabel title, name, previousType, diseaseType, location, site;
    JTextField nameInput, previousInput;
    JComboBox<String> diseaseDropdown, locationDropdown, siteDropdown;
    JButton addBtn, backBtn;
    String[] diseaseOption = {"", "HIV", "Abola", "Stomachache", "Fever"}
            ,locationOption = {"", "Malden", "Allston", "Cambridge"}
            ,siteOption = {""};

    public DiseaseType(){
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

        backBtn = new JButton("Go Back");
        backBtn.setForeground(Color.white);
        backBtn.setBackground(Color.black);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);

        jp.add(backBtn);
        jp.add(addBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToViewPatient();
            }
        });

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

    private void switchToViewPatient(){
        ViewPatient view = new ViewPatient();
        view.setVisible(true);
        dispose();
    }
}