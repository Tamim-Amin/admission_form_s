package admissionform;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.table.DefaultTableModel;

public class AdmissionForm extends JFrame implements ActionListener {
    private JTextField nameField, fatherNameField, motherNameField, emailField, sscYearField, hscYearField, sscGpaField, hscGpaField, contactNumberField;
    private JTextField presentAddressField, permanentAddressField;
    private JSpinner dobSpinner;
    private JButton submitButton, resetButton;
    private ButtonGroup genderGroup, religionGroup;
    private JCheckBox sameAsPresentAddressCheckBox;
    private JComboBox<String> departmentComboBox;
    private JTable table;
    private DefaultTableModel tableModel;

    public AdmissionForm() {
        setTitle("Admission Form");
        setSize(600, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.CENTER;

        // Title Label at the top
        JLabel titleLabel = new JLabel("Metropolitan University");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 25));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setForeground(Color.blue);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Student Name Field
        gbc.gridx = 0; gbc.gridy = 1;
        add(new JLabel("Student Name:"), gbc);
        nameField = new JTextField(20);
        gbc.gridx = 1;
        add(nameField, gbc);

        // Father's Name Field
        gbc.gridx = 0; gbc.gridy = 2;
        add(new JLabel("Father's Name:"), gbc);
        fatherNameField = new JTextField(20);
        gbc.gridx = 1;
        add(fatherNameField, gbc);

        // Mother's Name Field
        gbc.gridx = 0; gbc.gridy = 3;
        add(new JLabel("Mother's Name:"), gbc);
        motherNameField = new JTextField(20);
        gbc.gridx = 1;
        add(motherNameField, gbc);

        // Email Field
        gbc.gridx = 0; gbc.gridy = 4;
        add(new JLabel("Email:"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1;
        add(emailField, gbc);

        // Contact Number Field
        gbc.gridx = 0; gbc.gridy = 5;
        add(new JLabel("Contact Number:"), gbc);
        contactNumberField = new JTextField(20);
        gbc.gridx = 1;
        add(contactNumberField, gbc);

        // Date of Birth Field
        gbc.gridx = 0; gbc.gridy = 6;
        add(new JLabel("Date of Birth:"), gbc);
        dobSpinner = new JSpinner(new SpinnerDateModel());
        dobSpinner.setEditor(new JSpinner.DateEditor(dobSpinner, "dd-MM-yyyy"));
        gbc.gridx = 1;
        add(dobSpinner, gbc);

        // Gender Panel
        JPanel genderPanel = new JPanel(new GridLayout(1, 3));
        gbc.gridx = 0; gbc.gridy = 7;
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
        gbc.gridx = 0; gbc.gridy = 8;
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
        gbc.gridx = 0; gbc.gridy = 9;
        add(new JLabel("SSC Year:"), gbc);
        sscYearField = new JTextField(6);
        gbc.gridx = 1;
        add(sscYearField, gbc);

        gbc.gridx = 0; gbc.gridy = 10;
        add(new JLabel("SSC GPA:"), gbc);
        sscGpaField = new JTextField(6);
        gbc.gridx = 1;
        add(sscGpaField, gbc);

        // HSC Year and GPA
        gbc.gridx = 0; gbc.gridy = 11;
        add(new JLabel("HSC Year:"), gbc);
        hscYearField = new JTextField(6);
        hscYearField.setEditable(false); // HSC Year will be calculated based on SSC Year
        gbc.gridx = 1;
        add(hscYearField, gbc);

        gbc.gridx = 0; gbc.gridy = 12;
        add(new JLabel("HSC GPA:"), gbc);
        hscGpaField = new JTextField(6);
        gbc.gridx = 1;
        add(hscGpaField, gbc);

        // Department Dropdown
        gbc.gridx = 0; gbc.gridy = 13;
        add(new JLabel("Department:"), gbc);
        String[] departments = {
                "Computer Science & Engineering",
                "Software Engineering",
                "Electrical & Electronic Engineering",
                "Business Administration",
                "Economics",
                "English",
                "Law & Justice"
        };
        departmentComboBox = new JComboBox<>(departments);
        gbc.gridx = 1;
        add(departmentComboBox, gbc);

        // Present Address
        gbc.gridx = 0; gbc.gridy = 14;
        add(new JLabel("Present Address:"), gbc);
        presentAddressField = new JTextField(20);
        gbc.gridx = 1;
        add(presentAddressField, gbc);

        // Permanent Address
        gbc.gridx = 0; gbc.gridy = 15;
        add(new JLabel("Permanent Address:"), gbc);
        permanentAddressField = new JTextField(20);
        gbc.gridx = 1;
        add(permanentAddressField, gbc);

        // Same as Present Address checkbox
        gbc.gridx = 0; gbc.gridy = 16;
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
        gbc.gridx = 0; gbc.gridy = 17; gbc.gridwidth = 1;
        add(submitButton, gbc);

        // Reset Button
        resetButton = new JButton("Reset");
        resetButton.addActionListener(e -> resetForm());
        gbc.gridx = 1;
        add(resetButton, gbc);

        // JTable for displaying submitted data
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Student Name");
        tableModel.addColumn("Father's Name");
        tableModel.addColumn("Mother's Name");
        tableModel.addColumn("Email");
        tableModel.addColumn("Date of Birth");
        tableModel.addColumn("Gender");
        tableModel.addColumn("Religion");
        tableModel.addColumn("SSC Year");
        tableModel.addColumn("HSC Year");
        tableModel.addColumn("Department");
        tableModel.addColumn("Present Address");
        tableModel.addColumn("Permanent Address");

        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(800, 200));
        gbc.gridx = 0; gbc.gridy = 18; gbc.gridwidth = 2;
        add(scrollPane, gbc);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitButton) {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String motherName = motherNameField.getText();
            String email = emailField.getText();
            String contactNumber = contactNumberField.getText();
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            String dateOfBirth = dateFormat.format(dobSpinner.getValue());
            String gender = genderGroup.getSelection().getActionCommand();
            String religion = religionGroup.getSelection().getActionCommand();
            String sscYear = sscYearField.getText();
            try {
                int sscYearInt = Integer.parseInt(sscYear);
                int hscYear = sscYearInt + 2;
                hscYearField.setText(String.valueOf(hscYear)); // Automatically calculate HSC year
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid SSC year input", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String department = (String) departmentComboBox.getSelectedItem();
            String presentAddress = presentAddressField.getText();
            String permanentAddress = permanentAddressField.getText();

            if (validateEmail(email)) {
                tableModel.addRow(new Object[]{
                        name, fatherName, motherName, email, dateOfBirth, gender, religion, sscYear,
                        hscYearField.getText(), department, presentAddress, permanentAddress
                });
                resetForm();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private boolean validateEmail(String email) {
        return email.matches("^[A-Za-z0-9+_.-]+@(.+)$");
    }

    private void resetForm() {
        nameField.setText("");
        fatherNameField.setText("");
        motherNameField.setText("");
        emailField.setText("");
        contactNumberField.setText("");
        presentAddressField.setText("");
        permanentAddressField.setText("");
        dobSpinner.setValue(new Date());
        genderGroup.clearSelection();
        religionGroup.clearSelection();
        sscYearField.setText("");
        sscGpaField.setText("");
        hscGpaField.setText("");
        departmentComboBox.setSelectedIndex(0);
        sameAsPresentAddressCheckBox.setSelected(false);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AdmissionForm form = new AdmissionForm();
            form.setVisible(true);
        });
    }
}
