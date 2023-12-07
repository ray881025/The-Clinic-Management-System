package GUI;

import GUI.Home;
import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.LoginDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JFrame {
    JLabel title, username, password;
    JTextField usernameInput, passwordInput;
    JButton loginBtn;

    public  LoginGUI(Clinic c){
        setTitle("Login In");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(20)); //add 30 pixels gap
        setTitlePanel();
        add(Box.createVerticalStrut(20)); //add 30 pixels gap
        setInputPanel();


        add(Box.createVerticalStrut(40)); //add 40 pixels gap
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Logic For Login Button Here
        LoginDirectory lgd = c.getLgd();
        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = getUsername();
                String passWord = getPassword();
                if(lgd.findLoginInformation( userName, passWord)){
                    dispose();
                    Home h = new Home(c);
                } else {
                    JOptionPane.showMessageDialog(null,"Wrong Information Please input correct username and password","Error",JOptionPane.INFORMATION_MESSAGE);
                };
            }
        });

    }

    private  void  setTitlePanel(){
        JPanel jp = new JPanel();
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title = new JLabel("Log In");
        title.setFont(new Font("Serif", Font.BOLD, 32));

        jp.add(title);

        getContentPane().add(jp);
    }

    private void setInputPanel(){
        JPanel jp = new JPanel(new GridLayout(4,1));
        jp.setMaximumSize(new Dimension(300, 120));
        jp.setMinimumSize(new Dimension(300, 120));


        username = new JLabel("Enter your UserName");
        password = new JLabel("Enter your PassWord");

        usernameInput = new JTextField();
        passwordInput = new JTextField();

        jp.add(username);
        jp.add(usernameInput);
        jp.add(password);
        jp.add(passwordInput);

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel();
        jp.setMaximumSize(new Dimension(500, 80));
        jp.setMinimumSize(new Dimension(500, 80));


        loginBtn = new JButton("Log in");
        loginBtn.setPreferredSize(new Dimension(300,30));
        loginBtn.setForeground(Color.white);
        loginBtn.setBackground(Color.black);
        loginBtn.setOpaque(true); //Set transparency for button
        loginBtn.setBorderPainted(false);


        jp.add(loginBtn);

        getContentPane().add(jp);
    }

    //It will go to Home page when you enter the correct username and password

    public JButton getLoginBtn() {
        return loginBtn;
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    public String getPassword() {
        return passwordInput.getText();
    }

}