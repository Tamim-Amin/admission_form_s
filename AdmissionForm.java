package admissionform;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.JDateChooser;

public class AdmissionForm extends JFrame implements ActionListener {
    private JTextField nameField, fatherNameField, motherNameField, emailField, ageField, sscYearField, hscYearField,
            sscGpaField, hscGpaField, contactNumberField, presentAddressField, permanentAddressField;
    private JDateChooser dobChooser;
    private JSpinner dobSpinner;
    private JButton submitButton, resetButton, saveButton, loadButton;
    private ButtonGroup genderGroup, religionGroup;
    private JCheckBox sameAsPresentAddressCheckBox;
    private JComboBox<Department> departmentComboBox;
    private JTable dataTable;
    private DefaultTableModel tableModel;

    enum Department {
        CSE("Computer Science & Engineering"),
        SE("Software Engineering"),
        EEE("Electrical & Electronic Engineering"),
        BBA("Business Administration"),
        ECO("Economics"),
        ENG("English"),
        LAW("Law & Justice");

        private final String displayName;

        Department(String displayName) {
            this.displayName = displayName;
        }

        @Override
        public String toString() {
            return displayName;
        }
    }

    public AdmissionForm() {
        setTitle("Admission Form");
        setSize(800, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label
        JLabel titleLabel = new JLabel("Metropolitan University");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.blue);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Student Name Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Student Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Father's Name Field
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Father's Name:"), gbc);
        fatherNameField = new JTextField(20);
        gbc.gridx = 1;
        add(fatherNameField, gbc);

        // Mother's Name Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Mother's Name:"), gbc);
        motherNameField = new JTextField(20);
        gbc.gridx = 1;
        add(motherNameField, gbc);

        // Email Field
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Contact Number Field
        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("Contact Number:"), gbc);
        contactNumberField = new JTextField(20);
        gbc.gridx = 1;
        add(contactNumberField, gbc);

        // Date of Birth Field using JDateChooser
dobChooser = new JDateChooser();
dobChooser.setDateFormatString("dd-MM-yyyy");
dobChooser.addPropertyChangeListener("date", evt -> {
    Date dob = dobChooser.getDate();
    if (dob != null) {
        Calendar birthCal = Calendar.getInstance();
        birthCal.setTime(dob);
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthCal.get(Calendar.YEAR);
        if (today.get(Calendar.DAY_OF_YEAR) < birthCal.get(Calendar.DAY_OF_YEAR)) {
            age--;
        }
        ageField.setText(String.valueOf(age));
    }
});

gbc.gridx = 0;
gbc.gridy = 6;
add(new JLabel("Date of Birth:"), gbc);
gbc.gridx = 1;
add(dobChooser, gbc);



        // Age Field
        gbc.gridx = 0;
        gbc.gridy = 7;
        add(new JLabel("Age:"), gbc);
        ageField = new JTextField(5);
        ageField.setEditable(false);
        gbc.gridx = 1;
        add(ageField, gbc);
        // Gender Panel
        JPanel genderPanel = new JPanel(new GridLayout(1, 3));
        gbc.gridx = 0;
        gbc.gridy = 8;
        add(new JLabel("Gender:"), gbc);
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setActionCommand("Male");
        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setActionCommand("Female");
        JRadioButton otherButton = new JRadioButton("Other");
        otherButton.setActionCommand("Other");
        genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);
        genderGroup.add(otherButton);
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);
        genderPanel.add(otherButton);
        gbc.gridx = 1;
        add(genderPanel, gbc);

        // Religion Panel
        JPanel religionPanel = new JPanel(new GridLayout(1, 4));
        gbc.gridx = 0;
        gbc.gridy = 9;
        add(new JLabel("Religion:"), gbc);
        JRadioButton christianityButton = new JRadioButton("Christianity");
        christianityButton.setActionCommand("Christianity");
        JRadioButton islamButton = new JRadioButton("Islam");
        islamButton.setActionCommand("Islam");
        JRadioButton hinduismButton = new JRadioButton("Hinduism");
        hinduismButton.setActionCommand("Hinduism");
        JRadioButton otherReligionButton = new JRadioButton("Other");
        otherReligionButton.setActionCommand("Other");
        religionGroup = new ButtonGroup();
        religionGroup.add(christianityButton);
        religionGroup.add(islamButton);
        religionGroup.add(hinduismButton);
        religionGroup.add(otherReligionButton);
        religionPanel.add(christianityButton);
        religionPanel.add(islamButton);
        religionPanel.add(hinduismButton);
        religionPanel.add(otherReligionButton);
        gbc.gridx = 1;
        add(religionPanel, gbc);

        // SSC Year and GPA
        gbc.gridx = 0;
        gbc.gridy = 10;
        add(new JLabel("SSC Year:"), gbc);
        sscYearField = new JTextField(6);
        gbc.gridx = 1;
        add(sscYearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 11;
        add(new JLabel("SSC GPA:"), gbc);
        sscGpaField = new JTextField(6);
        gbc.gridx = 1;
        add(sscGpaField, gbc);

        // HSC Year and GPA
        gbc.gridx = 0;
        gbc.gridy = 12;
        add(new JLabel("HSC Year:"), gbc);
        hscYearField = new JTextField(6);
        gbc.gridx = 1;
        add(hscYearField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 13;
        add(new JLabel("HSC GPA:"), gbc);
        hscGpaField = new JTextField(6);
        gbc.gridx = 1;
        add(hscGpaField, gbc);

        // Department Dropdown
gbc.gridx = 0;
gbc.gridy = 14;
add(new JLabel("Department:"), gbc);
departmentComboBox = new JComboBox<Department>(Department.values());

gbc.gridx = 1;
add(departmentComboBox, gbc);


        // Present Address
        gbc.gridx = 0;
        gbc.gridy = 15;
        add(new JLabel("Present Address:"), gbc);
        presentAddressField = new JTextField(20);
        gbc.gridx = 1;
        add(presentAddressField, gbc);

        // Permanent Address
        gbc.gridx = 0;
        gbc.gridy = 16;
        add(new JLabel("Permanent Address:"), gbc);
        permanentAddressField = new JTextField(20);
        gbc.gridx = 1;
        add(permanentAddressField, gbc);

        // Same as Present Address checkbox
        gbc.gridx = 0;
        gbc.gridy = 17;
        sameAsPresentAddressCheckBox = new JCheckBox("Same as Present Address");
        sameAsPresentAddressCheckBox.addActionListener(e -> {
            if (sameAsPresentAddressCheckBox.isSelected()) {
                permanentAddressField.setText(presentAddressField.getText());
            } else {
                permanentAddressField.setText("");
            }
        });
        gbc.gridx = 1;
        add(sameAsPresentAddressCheckBox, gbc);

        // Submit Button
        submitButton = new JButton("Submit");
        submitButton.addActionListener(this);
        gbc.gridx = 0;
        gbc.gridy = 18;
        gbc.gridwidth = 1;
        add(submitButton, gbc);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetForm());
        gbc.gridx = 1;
        add(resetButton, gbc);

        // Save Button
        saveButton = new JButton("Save Data");
        saveButton.addActionListener(e -> saveStudentData());
        gbc.gridx = 0;
        gbc.gridy = 19;
        add(saveButton, gbc);

        /* Load Button
        loadButton = new JButton("Load Data");
        loadButton.addActionListener(e -> loadStudentData());
        gbc.gridx = 1;
        gbc.gridy = 19;
        add(loadButton, gbc);*/

        // Table for displaying data
        String[] columnNames = {"Student Name", "Father's Name", "Mother's Name", "Email", "Contact Number",
                                "Date of Birth", "Age", "Gender", "Religion", "SSC Year", "SSC GPA",
                                "HSC Year", "HSC GPA", "Department", "Present Address", "Permanent Address"};
        tableModel = new DefaultTableModel(columnNames, 0);
        dataTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(dataTable);
        gbc.gridx = 0;
        gbc.gridy = 20;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weighty = 1.0;
        add(scrollPane, gbc);

        // Load student data on startup
        loadStudentData();
    }

    @Override
public void actionPerformed(ActionEvent e) {
    if (nameField.getText().isEmpty() || fatherNameField.getText().isEmpty() || motherNameField.getText().isEmpty()
            || emailField.getText().isEmpty() || contactNumberField.getText().isEmpty() || ageField.getText().isEmpty()
            || sscYearField.getText().isEmpty() || sscGpaField.getText().isEmpty() ||
            hscYearField.getText().isEmpty() || hscGpaField.getText().isEmpty() ||
            presentAddressField.getText().isEmpty() || permanentAddressField.getText().isEmpty() ||
            genderGroup.getSelection() == null || religionGroup.getSelection() == null) {

        JOptionPane.showMessageDialog(this, "Please fill in all required fields.");
        return;
    }

    String email = emailField.getText();
    if (!email.matches("^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.");
        return;
    }

    String contactNumber = contactNumberField.getText();
    if (!contactNumber.matches("^\\+?[0-9]{11,13}$")) {
        JOptionPane.showMessageDialog(this, "Please enter a valid contact number (11-13 digits).");
        return;
    }

    try {
        int sscYear = Integer.parseInt(sscYearField.getText());
        int hscYear = Integer.parseInt(hscYearField.getText());
        double sscGpa = Double.parseDouble(sscGpaField.getText());
        double hscGpa = Double.parseDouble(hscGpaField.getText());

        if (hscYear - sscYear < 2) {
            JOptionPane.showMessageDialog(this, "The gap between SSC and HSC years should be at least 2 years.");
            return;
        }

        if (sscGpa < 0 || sscGpa > 5) {
            JOptionPane.showMessageDialog(this, "SSC GPA must be between 0 and 5.");
            return;
        }

        if (hscGpa < 0 || hscGpa > 5) {
            JOptionPane.showMessageDialog(this, "HSC GPA must be between 0 and 5.");
            return;
        }
    } catch (NumberFormatException ex) {
        JOptionPane.showMessageDialog(this, "Please enter valid numeric values for years and GPA.");
        return;
    }

    Department selectedDepartment = (Department) departmentComboBox.getSelectedItem();

    // Create a new Student object
    Student student = new Student(
            nameField.getText(),
            fatherNameField.getText(),
            motherNameField.getText(),
            emailField.getText(),
            contactNumberField.getText(),
            new SimpleDateFormat("dd-MM-yyyy").format(dobChooser.getDate()),
            Integer.parseInt(ageField.getText()),
            genderGroup.getSelection().getActionCommand(),
            religionGroup.getSelection().getActionCommand(),
            Integer.parseInt(sscYearField.getText()),
            Double.parseDouble(sscGpaField.getText()),
            Integer.parseInt(hscYearField.getText()),
            Double.parseDouble(hscGpaField.getText()),
            selectedDepartment.toString(),
            presentAddressField.getText(),
            permanentAddressField.getText()
    );

    // Add student data to table
    String[] rowData = {
            student.getStudentName(),
            student.getFatherName(),
            student.getMotherName(),
            student.getEmail(),
            student.getContactNumber(),
            student.getDob(),
            String.valueOf(student.getAge()),
            student.getGender(),
            student.getReligion(),
            String.valueOf(student.getSscYear()),
            String.valueOf(student.getSscGpa()),
            String.valueOf(student.getHscYear()),
            String.valueOf(student.getHscGpa()),
            student.getDepartment(),
            student.getPresentAddress(),
            student.getPermanentAddress()
    };
    tableModel.addRow(rowData);

    // Serialize the student object to a file
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students_data.ser", true))) {
        oos.writeObject(student);
    } catch (Exception ex) {
        JOptionPane.showMessageDialog(this, "Error saving data: " + ex.getMessage());
    }

    JOptionPane.showMessageDialog(this, "Form submitted successfully!");
    resetForm();
    
    
    
    
    
}




    private void resetForm() {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        emailField.setText("");
        contactNumberField.setText("");
        ageField.setText("");
        sscYearField.setText("");
        hscYearField.setText("");
        sscGpaField.setText("");
        hscGpaField.setText("");
        presentAddressField.setText("");
        permanentAddressField.setText("");
        dobChooser.setDate(null);
        genderGroup.clearSelection();
        religionGroup.clearSelection();
        departmentComboBox.setSelectedIndex(0);
        sameAsPresentAddressCheckBox.setSelected(false);
    }
    
     private void saveStudentData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("students_data.ser"))) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                Student student = new Student(
                        tableModel.getValueAt(i, 0).toString(),
                        tableModel.getValueAt(i, 1).toString(),
                        tableModel.getValueAt(i, 2).toString(),
                        tableModel.getValueAt(i, 3).toString(),
                        tableModel.getValueAt(i, 4).toString(),
                        tableModel.getValueAt(i, 5).toString(),
                        Integer.parseInt(tableModel.getValueAt(i, 6).toString()),
                        tableModel.getValueAt(i, 7).toString(),
                        tableModel.getValueAt(i, 8).toString(),
                        Integer.parseInt(tableModel.getValueAt(i, 9).toString()),
                        Double.parseDouble(tableModel.getValueAt(i, 10).toString()),
                        Integer.parseInt(tableModel.getValueAt(i, 11).toString()),
                        Double.parseDouble(tableModel.getValueAt(i, 12).toString()),
                        tableModel.getValueAt(i, 13).toString(),
                        tableModel.getValueAt(i, 14).toString(),
                        tableModel.getValueAt(i, 15).toString()
                );
                oos.writeObject(student);
            }
            JOptionPane.showMessageDialog(this, "Data saved successfully!");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error saving data: " + e.getMessage());
        }
    }

    public void loadStudentData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("students_data.ser"))) {
            while (true) {
                try {
                    Student student = (Student) ois.readObject();
                    String[] rowData = {
                            student.getStudentName(),
                            student.getFatherName(),
                            student.getMotherName(),
                            student.getEmail(),
                            student.getContactNumber(),
                            student.getDob(),
                            String.valueOf(student.getAge()),
                            student.getGender(),
                            student.getReligion(),
                            String.valueOf(student.getSscYear()),
                            String.valueOf(student.getSscGpa()),
                            String.valueOf(student.getHscYear()),
                            String.valueOf(student.getHscGpa()),
                            student.getDepartment(),
                            student.getPresentAddress(),
                            student.getPermanentAddress()
                    };
                    tableModel.addRow(rowData);
                } catch (EOFException e) {
                    break;
                }
            }
           // JOptionPane.showMessageDialog(this, "Data loaded successfully!");
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(this, "No saved data found.");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading data: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdmissionForm().setVisible(true));
    }
}
