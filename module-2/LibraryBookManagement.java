/*
25. Library Book Management GUI
Develop a Swing-based Library Book Management interface using JTextField,
JComboBox, JTable, JButton, JOptionPane, and appropriate layout managers.
Allow the user to add books, delete a selected book, clear the input fields,
and display a message when no table row is selected for deletion.
*/

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class LibraryBookManagement extends JFrame implements ActionListener {
    JTextField idField, titleField, authorField;
    JComboBox<String> categoryBox;
    JTable bookTable;
    DefaultTableModel tableModel;
    JButton addButton, deleteButton, clearButton;

    public LibraryBookManagement() {
        setTitle("Library Book Management");
        setSize(700, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 5, 5));
        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        categoryBox = new JComboBox<String>(
                new String[] {"Fiction", "Science", "History", "Other"});

        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(new JLabel("Category:"));
        inputPanel.add(idField);
        inputPanel.add(titleField);
        inputPanel.add(authorField);
        inputPanel.add(categoryBox);
        add(inputPanel, BorderLayout.NORTH);

        tableModel = new DefaultTableModel(
                new String[] {"Book ID", "Title", "Author", "Category"}, 0);
        bookTable = new JTable(tableModel);
        add(new JScrollPane(bookTable), BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Book");
        deleteButton = new JButton("Delete Selected");
        clearButton = new JButton("Clear Fields");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(this);
        deleteButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent event) {
        if (event.getSource() == addButton) {
            tableModel.addRow(new Object[] {
                idField.getText(),
                titleField.getText(),
                authorField.getText(),
                categoryBox.getSelectedItem()
            });
            clearFields();
        } else if (event.getSource() == deleteButton) {
            int row = bookTable.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(this, "Select a book to delete.");
            } else {
                tableModel.removeRow(row);
            }
        } else {
            clearFields();
        }
    }

    public void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        categoryBox.setSelectedIndex(0);
    }

    public static void main(String[] args) {
        LibraryBookManagement window = new LibraryBookManagement();
        window.setVisible(true);
    }
}
