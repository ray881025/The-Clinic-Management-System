package GUI;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.LoginDirectory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JFrame{
    JLabel title, username, password, confirmpassword;
    JTextField usernameInput, passwordInput, confirmpasswordInput;
    JButton registerBtn, backBtn;

    public  RegisterGUI(Clinic c){
        setTitle("Register");
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
        registerBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = getUsername();
                String passWord = getPassword();
                String getconfirmPassword = getConfirmpassword();
                System.out.println(getconfirmPassword);
                System.out.println(passWord);
                if (passWord.equals(getconfirmPassword)) {
                    lgd.newLoginUser(userName,passWord);
                    JOptionPane.showMessageDialog(null,"You have registered successfully.","Success",JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(null,"Check your password. Your password and confirmpassword are not same.","Error",JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                LoginGUI lgu = new LoginGUI(c);
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
        JPanel jp = new JPanel(new GridLayout(6,1));
        jp.setMaximumSize(new Dimension(300, 180));
        jp.setMinimumSize(new Dimension(300, 180));


        username = new JLabel("Enter your UserName");
        password = new JLabel("Enter your PassWord");
        confirmpassword = new JLabel("Enter your Confirm Password");

        usernameInput = new JTextField();
        passwordInput = new JTextField();
        confirmpasswordInput = new JTextField();

        jp.add(username);
        jp.add(usernameInput);
        jp.add(password);
        jp.add(passwordInput);
        jp.add(confirmpassword);
        jp.add(confirmpasswordInput);

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel(new GridLayout(1,2,40,0));
        jp.setMaximumSize(new Dimension(300, 40));
        jp.setMinimumSize(new Dimension(500, 40));


        registerBtn = new JButton("Register");
        registerBtn.setPreferredSize(new Dimension(300,30));
        registerBtn.setForeground(Color.white);
        registerBtn.setBackground(Color.black);
        registerBtn.setOpaque(true); //Set transparency for button
        registerBtn.setBorderPainted(false);

        backBtn = new JButton("Back");
        backBtn.setPreferredSize(new Dimension(300,30));
        backBtn.setForeground(Color.black);
        backBtn.setBackground(Color.white);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);


        jp.add(backBtn);
        jp.add(registerBtn);


        getContentPane().add(jp);
    }

    //It will go to Home page when you enter the correct username and password

    public JButton getLoginBtn() {
        return registerBtn;
    }

    public String getUsername() {
        return usernameInput.getText();
    }

    public String getPassword() {
        return passwordInput.getText();
    }

    public String getConfirmpassword() {
        return confirmpasswordInput.getText();
    }
}
