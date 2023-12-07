package GUI;

import PatientManagement.Clinic.Clinic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame {
    JLabel title1, title2;
    JButton logoutBtn, patientCreate, patientView, patientHistory, diseaseChange,
            diseaseLocation, serviceLocation;

    public Home(Clinic c){
        setTitle("Home Page");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        setBtnPanel();
        setBasicPanel();
        setBtnPanelOne();
        add(Box.createVerticalStrut(30));
        setAdvancedPanel();
        setBtnPanelTwo();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Logic For Logout Button Here
        logoutBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI lgu = new LoginGUI(c);
            }
        });

        //Logic For PatientCreate
        patientCreate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                CreatePatient crp = new CreatePatient(c);
            }
        });

        //Logic For ViewPatient
        patientView.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ViewPatient vp = new ViewPatient(c);
            }
        });

        //Logic For DiseaseChange
        diseaseChange.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                DiseaseChange dsg = new DiseaseChange(c);
            }
        });

        //Logic For ViewSickPatient
        diseaseLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ViewSickPatient vsp = new ViewSickPatient(c);
            }
        });

        //Logic For LocalService
        serviceLocation.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                ViewLocationService vls = new ViewLocationService(c);
            }
        });



    }

    private void setBasicPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Put the Text on the left
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title1 = new JLabel("Basics");
        title1.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title1);

        getContentPane().add(jp);
    }

    private void setBtnPanelOne(){
        JPanel jp = new JPanel(new GridLayout(1,3,20,0));
        jp.setMaximumSize(new Dimension(500, 80));
        jp.setMinimumSize(new Dimension(500, 80));

        patientCreate = new JButton("<html>Create New <br> Patient</html>"); //Make the texts in two lines
        patientCreate.setBackground(Color.yellow);
        patientCreate.setOpaque(true); //Set transparency for button
        patientCreate.setBorderPainted(false);



        patientView = new JButton("View Patient");
        patientView.setBackground(Color.CYAN);
        patientView.setOpaque(true); //Set transparency for button
        patientView.setBorderPainted(false);



        patientHistory = new JButton("<html>View Medical <br> History</html>"); //Make the texts in two lines
        patientHistory.setBackground(Color.green);
        patientHistory.setOpaque(true); //Set transparency for button
        patientHistory.setBorderPainted(false);

        jp.add(patientCreate);
        jp.add(patientView);


        getContentPane().add(jp);
    }

    private void setAdvancedPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT)); //Put the Text on the left
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title2 = new JLabel("Advanced");
        title2.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title2);

        getContentPane().add(jp);
    }

    private void setBtnPanelTwo(){
        JPanel jp = new JPanel(new GridLayout(1,3,20,0));
        jp.setMaximumSize(new Dimension(500, 80));
        jp.setMinimumSize(new Dimension(500, 80));

        diseaseChange = new JButton("<html>View Sick <br> Patient Change</hrml>"); //Make the texts in two lines
        diseaseChange.setBackground(Color.yellow);
        diseaseChange.setOpaque(true); //Set transparency for button
        diseaseChange.setBorderPainted(false);


        diseaseLocation = new JButton("<html>View Location <br> Trending</html>"); //Make the texts in two lines
        diseaseLocation.setBackground(Color.cyan);
        diseaseLocation.setOpaque(true); //Set transparency for button
        diseaseLocation.setBorderPainted(false);



        serviceLocation = new JButton("<html>View Location <br> Service</html>"); //Make the texts in two lines
        serviceLocation.setBackground(Color.green);
        serviceLocation.setOpaque(true); //Set transparency for button
        serviceLocation.setBorderPainted(false);

        jp.add(diseaseChange);
        jp.add(diseaseLocation);
        jp.add(serviceLocation);

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT)); //Align the button on the right
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        logoutBtn = new JButton("Log Out");
        logoutBtn.setForeground(Color.white);
        logoutBtn.setBackground(Color.black);
        logoutBtn.setOpaque(true); //Set transparency for button
        logoutBtn.setBorderPainted(false);



        jp.add(logoutBtn);

        getContentPane().add(jp);
    }


    public JButton getLogoutBtn() {
        return logoutBtn;
    }

    public JButton getPatientCreate() {
        return patientCreate;
    }

    public JButton getDiseaseChange() {
        return diseaseChange;
    }

    public JButton getPatientView() {
        return patientView;
    }

    public JButton getDiseaseLocation() {
        return diseaseLocation;
    }





    //It will go back to View page when you click View Patient button





}
