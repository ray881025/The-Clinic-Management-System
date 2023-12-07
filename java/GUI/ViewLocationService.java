package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ViewLocationService extends JFrame {
    JLabel title;
    JComboBox<String> locationDropdown, siteDropdown;
    String[] locationOption = {"", "Malden", "Allston", "Cambridge"}
            ,siteOption = {""};
    JButton searchBtn, backBtn;
    JTable table;

    DefaultTableModel tableModel;

    public ViewLocationService(){
        setTitle("View Location Service by Site");
        setSize(600, 400);
        setLocationRelativeTo(null); //Center the JFrame on the screen
        setLayout(new BoxLayout(getContentPane(),BoxLayout.Y_AXIS));

        add(Box.createVerticalStrut(10));
        setTitlePanel();
        setSearchPanel();
        add(Box.createVerticalStrut(20));
        setTablePanel();
        add(Box.createVerticalStrut(20));
        setBtnPanel();

        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setTitlePanel(){
        JPanel jp = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jp.setMaximumSize(new Dimension(500, 40));
        jp.setMinimumSize(new Dimension(500, 40));

        title = new JLabel("Location Service");
        title.setFont(new Font("SANS_SERIF", Font.BOLD, 20));

        jp.add(title);

        getContentPane().add(jp);
    }

    private void setSearchPanel(){
        JPanel jp = new JPanel(new GridLayout(1,3));
        jp.setMaximumSize(new Dimension(450, 30));
        jp.setMinimumSize(new Dimension(450, 30));

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

        searchBtn = new JButton("Search");

        jp.add(locationDropdown);
        jp.add(siteDropdown);
        jp.add(searchBtn);

        getContentPane().add(jp);
    }

    private void setTablePanel(){
        JPanel jp = new JPanel(new BorderLayout());
        jp.setMaximumSize(new Dimension(550, 200));
        jp.setMinimumSize(new Dimension(550, 200));


        tableModel = new DefaultTableModel(new Object[]{"Service Name", "Telephone Number", "Address", }, 0);


        table = new JTable(tableModel);


        JScrollPane scrollPane = new JScrollPane(table);

        jp.add(scrollPane, BorderLayout.CENTER);

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

    private void switchToHome() {
        Home home = new Home();
        home.setVisible(true);
        dispose(); // Close the current frame
    }

    public DefaultTableModel getTableModel() {
        return tableModel;
    }

    public JButton getSearchBtn() {
        return searchBtn;
    }

    public String getLocationDropdown() {
        return (String) locationDropdown.getSelectedItem();
    }
}