package org.example;

import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.GridLayout;
import java.awt.event.*;


public class RegGUI extends JFrame implements ActionListener {


    public RegGUI() {

        setSize(800, 400);
        setLayout(null);

        idLabel = new JLabel("ID");
        idLabel.setBounds(30, 50, 80, 40);
        nameLabel = new JLabel("Name");
        nameLabel.setBounds(30, 105, 80, 40);
        genderLabel = new JLabel("Gender");
        genderLabel.setBounds(30, 160, 80, 40);
        addressLabel = new JLabel("Address");
        addressLabel.setBounds(30, 205, 80, 40);
        contactLabel = new JLabel("Contact");
        contactLabel.setBounds(30, 250, 80, 40);

        fieldID = new JTextField();
        fieldID.setBounds(95, 60, 130, 30);
        fieldName = new JTextField();
        fieldName.setBounds(95, 110, 130, 30);

        fieldID.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char n = e.getKeyChar();
                 if (!((n >= '0') && (n <= '9') || (n == KeyEvent.VK_BACK_SPACE) ||
                         (n == KeyEvent.VK_DELETE))) {
                          e.consume();

                }
                }
           });
        male = new JRadioButton("Male");
        male.setBounds(95, 165, 60, 30);
        male.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gender = "Male";
            }

        });

        female = new JRadioButton("Female");
        female.setBounds(155, 165, 70, 30);
        female.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gender = "Female";
            }

        });

        btnG = new ButtonGroup();
        btnG.add(male);
        btnG.add(female);

        fieldAddress = new JTextField();
        fieldAddress.setBounds(95, 210, 130, 30);
        fieldContact = new JTextField();
        fieldContact.setBounds(95, 255, 130, 30);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(35, 320, 100, 30);
        exitBtn.addActionListener(this);

        registerBtn = new JButton("Register");
        registerBtn.setBounds(140, 320, 100, 30);
        registerBtn.addActionListener(this);

        add(idLabel);
        add(nameLabel);
        add(genderLabel);
        add(addressLabel);
        add(contactLabel);
        add(fieldID);
        add(fieldName);
        add(male);
        add(female);
        add(fieldAddress);
        add(fieldContact);
        add(exitBtn);
        add(registerBtn);

        panel = new JPanel();
        panel.setLayout(new GridLayout());
        panel.setBounds(250, 10, 500, 342);
        add(panel);

        model = new DefaultTableModel();

        JTable table = new JTable(model);
        table.setEnabled(true);

        model.addColumn("ID");
        model.addColumn("Name");
        model.addColumn("Gender");
        model.addColumn("Address");
        model.addColumn("Contact");

        table.getTableHeader().setReorderingAllowed(false);
        scroll = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        panel.add(scroll);
        panel.setEnabled(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == exitBtn) {
            System.exit(0);
        }

        if (ae.getSource() == registerBtn) {

            list.add(new account(fieldID.getText(), fieldName.getText(), gender, fieldAddress.getText(), fieldContact.getText()));
            addRows();
            JOptionPane.showMessageDialog(this, "Successfully created account");
            fieldID.setText("");
            fieldName.setText("");
            gender = "";
            fieldAddress.setText("");
            fieldContact.setText("");
        }
    }

    public void addRows() {

        Object[] row = null;
        account str = list.get(list.size() - 1);
        String string = str.id + "," + str.name + "," + str.gender + "," + str.address + "," + str.contact;

        row = string.split(",");
        model.addRow(row);
        panel.revalidate();

    }

    public static void main(String[] args) {
        new RegGUI();

    }

    private JLabel idLabel, nameLabel, genderLabel, addressLabel, contactLabel;
    private JTextField fieldID, fieldAddress, fieldContact, fieldName;
    private JButton registerBtn, exitBtn;
    private JRadioButton male, female;
    private String gender = "";
    private ButtonGroup btnG;
    private JPanel panel;
    private List<account> list = new ArrayList<account>();
    private JScrollPane scroll;
    private DefaultTableModel model;

    //New class account
    public class account
    {
        String id;
        String name;
        String gender;
        String address;
        String contact;

        account(String id,String name,String gender,String address,String contact) {
            this.id = id;
            this.name = name;
            this.gender = gender;
            this.address = address;
            this.contact = contact;
        }
    }

}