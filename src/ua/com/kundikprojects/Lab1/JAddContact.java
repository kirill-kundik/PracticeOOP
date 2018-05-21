package ua.com.kundikprojects.Lab1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

class JAddContact extends JFrame {

    private JTextField jTextField1;
    private JTextField jTextField2;

    JAddContact() {

        super("Add Contact");

        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setResizable(false);

        JLabel jLabel1 = new JLabel("Name: ");
        JLabel jLabel2 = new JLabel("Phone: ");

        jTextField1 = new javax.swing.JTextField(15);
        jTextField2 = new javax.swing.JTextField(30);

        JButton jButton1 = new JButton("Add");
        JButton jButton2 = new JButton("Cancel");

        JPanel jPanel1 = new JPanel(new GridLayout(2, 1, 1, 5));
        jPanel1.add(jLabel1);
        jPanel1.add(jLabel2);

        JPanel jPanel2 = new JPanel(new GridLayout(2, 1));
        jPanel2.add(jTextField1);
        jPanel2.add(jTextField2);

        JPanel jPanel3 = new JPanel(new FlowLayout());
        jPanel3.add(jPanel1);
        jPanel3.add(jPanel2);

        JPanel jPanel4 = new JPanel(new FlowLayout());
        jPanel4.add(jButton1);
        jPanel4.add(jButton2);

        jButton2.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
        });

        jButton1.addActionListener(e -> {

            String name = jTextField1.getText();
            String number = jTextField2.getText();

            if (name == null || name.equals("")) {
                JOptionPane.showMessageDialog(null, "Name field is empty");
                return;
            }
            if (number == null || number.equals("")) {
                JOptionPane.showMessageDialog(null, "Number field is empty");
                return;
            }

            ArrayList<String> numbers = new ArrayList<String>(Arrays.asList(number.split(", ")));

            JContactBook.addEntry(new Contact(name, numbers));
            this.setVisible(false);
            this.dispose();
        });

        JPanel jPanel5 = new JPanel(new BorderLayout());
        jPanel5.add(jPanel3, BorderLayout.CENTER);
        jPanel5.add(jPanel4, BorderLayout.SOUTH);

        this.getContentPane().add(jPanel5);
        this.pack();
        this.setVisible(true);
    }

}
