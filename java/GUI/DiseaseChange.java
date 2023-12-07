package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DiseaseChange extends JFrame {
    JLabel title, nameText;
    JTextField nameInput;
    JButton searchBtn, backBtn;
    JTable table;
    DefaultTableModel tableModel;

    public DiseaseChange(){
        setTitle("View Local Services by Site");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10));
        setTitlePanel();
        setSearchPanel();
        add(Box.createVerticalStrut(20));
        setTablePanel();
        add(Box.createVerticalStrut(10));
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setTitlePanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title = new JLabel("Sick Patient Change");
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title);

        getContentPane().add(jp);
    }

    private void setSearchPanel(){
        JPanel jp = new JPanel(new GridLayout(1,3));
        jp.setMaximumSize(new Dimension(450, 20));
        jp.setMinimumSize(new Dimension(450, 20));

        nameText = new JLabel("Enter Name:");
        nameText.setHorizontalAlignment(SwingConstants.RIGHT);

        nameInput = new JTextField();

        searchBtn = new JButton("Search");

        jp.add(nameText);
        jp.add(nameInput);
        jp.add(searchBtn);

        getContentPane().add(jp);
    }

    private void setTablePanel(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.setMaximumSize(new Dimension(500, 200));
        jp.setMinimumSize(new Dimension(500, 200));

        tableModel = new DefaultTableModel(new Object[]{"Name", "Change Time", "Address"}, 0);


        table = new JTable(tableModel);
        table.setPreferredScrollableViewportSize(new Dimension(500, 200));

        jp.add(new JScrollPane(table), BorderLayout.CENTER); // Wrap the table in a JScrollPane to enable scrolling

        getContentPane().add(jp);
    }

    private void setBtnPanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        backBtn = new JButton("Go Back");
        backBtn.setForeground(Color.white);
        backBtn.setBackground(Color.black);
        backBtn.setOpaque(true); //Set transparency for button
        backBtn.setBorderPainted(false);

        jp.add(backBtn);

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchToHome();
            }
        });

        getContentPane().add(jp);
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public String getNameInput() {
        return nameInput.getText();
    }

    private void switchToHome() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // Close the current frame
    }
}