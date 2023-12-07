package GUI;

import PatientManagement.Clinic.Clinic;
import PatientManagement.Clinic.PatientDirectory;
import PatientManagement.Patient.Encounters.SickPatientReport;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DiseaseChange extends JFrame {
    JLabel title, nameText;
    JTextField nameInput;
    JButton searchBtn, backBtn;
    JTable table;
    DefaultTableModel tableModel;

    public DiseaseChange(Clinic c){
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


        PatientDirectory pd = c.getPatientDirectory();
        SickPatientReport stpatientReport = pd.generateSickPatientReport();
        stpatientReport.generateSickPatientData();

        DefaultTableModel tableM = getTableModel();
        tableM.setRowCount(0);

        for (int i = 0; i < stpatientReport.getSickName().size(); i++) {
            Object[] newRowL = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
            tableM.addRow(newRowL);
        }
        searchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!getNameInput().equals("")){
                    tableM.setRowCount(0);
                    for (int i = 0; i < stpatientReport.getSickName().size(); i++){
                        if (getNameInput().equals(stpatientReport.getSickName().get(i))){
                            Object[] newRowR = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
                            tableM.addRow(newRowR);
                        }
                    }
                } else {
                    tableM.setRowCount(0);
                    for (int i = 0; i < stpatientReport.getSickName().size(); i++){
                        Object[] newRowR = {stpatientReport.getSickName().get(i),stpatientReport.getSickNumber().get(i),stpatientReport.getSickAddress().get(i)};
                        tableM.addRow(newRowR);
                    }
                }
            }
        });

        backBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Home home = new Home(c);
                dispose();
            }
        });

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

        table.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (c instanceof JLabel) {
                    JLabel label = (JLabel) c;
                    int preferredWidth = label.getPreferredSize().width;
                    TableColumn colum = table.getColumnModel().getColumn(2);
                    colum.setPreferredWidth(Math.max(colum.getPreferredWidth(), preferredWidth));
                }
                return c;
            }
        });

        table.setPreferredScrollableViewportSize(new Dimension(500, 200));
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);


        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        jp.add(scrollPane, BorderLayout.CENTER); // Wrap the table in a JScrollPane to enable scrolling


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


}