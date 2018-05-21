package ua.com.kundikprojects.Lab1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class JContactBook extends JFrame {

    private static JTable jTable;

    private static int rowCounter = 0;
    private static int selectedRow;
    private static DefaultTableModel model;

    public JContactBook() {

        super("Contact Book");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        model = new DefaultTableModel();
        jTable = new JTable(model);

        model.addColumn("Name");
        model.addColumn("Numbers");

        JScrollPane jScrollPane = new JScrollPane(jTable);

        JPanel jPanel1 = new JPanel(new BorderLayout());
        jPanel1.add(jScrollPane, BorderLayout.CENTER);

        JButton jButton1 = new JButton("Add");
        JButton jButton2 = new JButton("Edit");
        JButton jButton3 = new JButton("Delete");
        JButton jButton4 = new JButton("Exit");

        JPanel jPanel2 = new JPanel(new FlowLayout());

        jPanel2.add(jButton1);
        jPanel2.add(jButton2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);

        jButton1.addActionListener(e -> new JAddContact().setVisible(true));
        jButton2.addActionListener(e -> new JUpdateContact(jTable.getValueAt(getSelectedRow(), 0).toString(),
                jTable.getValueAt(getSelectedRow(), 1).toString()).setVisible(true));
        jButton3.addActionListener(e -> removeEntry());
        jButton4.addActionListener(e -> {
            setVisible(true);
            this.dispose();
        });

        jPanel1.add(jPanel2, BorderLayout.SOUTH);
        jPanel1.setPreferredSize(new Dimension(750, 300));

        this.getContentPane().add(jPanel1);
        this.pack();
        this.setVisible(true);
    }

    public static void addEntry(Contact contact) {
        model.addRow(new Object[]{contact.getName(), contact.getNumbers().toString()});

        rowCounter++;
    }

    public static void editEntry(Contact contact) {
        model.setValueAt(contact.getName(), getSelectedRow(), 0);
        model.setValueAt(contact.getNumbers().toString(), getSelectedRow(), 1);
    }

    public static void removeEntry() {
        model.removeRow(getSelectedRow());

        rowCounter--;
    }

    public static int getSelectedRow() {
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSel = jTable.getSelectionModel();
        rowSel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;

            ListSelectionModel sel = (ListSelectionModel)e.getSource();
            if (!sel.isSelectionEmpty()) {
                selectedRow = sel.getMinSelectionIndex();
            }
        });

        return selectedRow;
    }

    public static void main(String[] args) {
        new JContactBook();
    }
}
