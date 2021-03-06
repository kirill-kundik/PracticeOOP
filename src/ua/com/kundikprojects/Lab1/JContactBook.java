package ua.com.kundikprojects.Lab1;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class JContactBook extends JFrame {

    private static JTable jTable;

    private static int selectedRow;
    private static DefaultTableModel model;

    private static JContactBookController controller;

    private JContactBook() {

        super("Contact Book");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setResizable(false);

        controller = new JContactBookController();
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
        JButton jButton4 = new JButton("Search");
        JButton jButton5 = new JButton("Refresh");

        JPanel jPanel2 = new JPanel(new FlowLayout());

        jPanel2.add(jButton1);
        jPanel2.add(jButton2);
        jPanel2.add(jButton3);
        jPanel2.add(jButton4);
        jPanel2.add(jButton5);

        jButton1.addActionListener(e -> new JAddContact().setVisible(true));
        jButton2.addActionListener(e -> new JUpdateContact(jTable.getValueAt(getSelectedRow(), 0).toString(),
                jTable.getValueAt(getSelectedRow(), 1).toString()).setVisible(true));
        jButton3.addActionListener(e -> removeEntry());
        jButton4.addActionListener(e -> new JSearchContact().setVisible(true));
        jButton5.addActionListener(e -> update());

        jPanel1.add(jPanel2, BorderLayout.SOUTH);
        jPanel1.setPreferredSize(new Dimension(750, 300));

        this.getContentPane().add(jPanel1);
        this.pack();
        this.setVisible(true);
    }

    static void addEntry(Contact contact) {
        controller.addContact(contact);
        model.addRow(new Object[]{contact.getName(), contact.getNumbers().toString()});

        System.out.println(controller.toString());
    }

    static void editEntry(Contact contact) {
        controller.editContactByName((String) model.getValueAt(getSelectedRow(), 0), contact.getName(), contact.getNumbers());
        model.setValueAt(contact.getName(), getSelectedRow(), 0);
        model.setValueAt(contact.getNumbers().toString(), getSelectedRow(), 1);
    }

    private static void removeEntry() {
        controller.deleteContactByName((String) model.getValueAt(getSelectedRow(), 0));
        model.removeRow(getSelectedRow());
    }

    private static int getSelectedRow() {
        jTable.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        ListSelectionModel rowSel = jTable.getSelectionModel();
        rowSel.addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) return;

            ListSelectionModel sel = (ListSelectionModel) e.getSource();
            if (!sel.isSelectionEmpty()) {
                selectedRow = sel.getMinSelectionIndex();
            }
        });

        return selectedRow;
    }

    private static void removeAllRows() {

        System.out.println(model.getRowCount());

        for (int i = model.getRowCount() - 1; i >= 0; --i)
            model.removeRow(i);
    }

    private static void update() {
        removeAllRows();
        for (Contact contact : controller.getContacts())
            model.addRow(new Object[]{contact.getName(), contact.getNumbers().toString()});
    }

    static void searchedEntry(String name, String number) {
        removeAllRows();

        ArrayList<Contact> contacts;
        if (name == null)
            contacts = controller.searchByNumber(number);
        else
            contacts = controller.searchByName(name);

        for (Contact contact : contacts)
            model.addRow(new Object[]{contact.getName(), contact.getNumbers().toString()});
    }

    public static void main(String[] args) {
        new JContactBook();
    }
}
