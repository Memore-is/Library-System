package libraryproject;

import javax.swing.*;

public class StudentDialog extends JDialog {
    private static JButton add = new JButton( "Add");
    private static JButton remove = new JButton("Remove");
    private static JButton showInfo = new JButton("Show Information");
    private static JButton cancel = new JButton("Cancel");
    private static JPanel buttonPanel = new JPanel();

    public StudentDialog(JFrame frame, Manager manager, JTextArea Allbooks) {
        super(frame, "Student Information", true);

        buttonPanel.add(add);
        buttonPanel.add(remove);
        buttonPanel.add(showInfo);
        buttonPanel.add(cancel);

        add.addActionListener(e -> {
            JTextField FnameField = new JTextField(20);
            JTextField LnameField = new JTextField(20);
            JTextField osisField = new JTextField(20);

            String Fname = FnameField.getText().trim();
            String Lname = LnameField.getText().trim();
            String osis = osisField.getText().trim();

            if (Fname.isEmpty() || Lname.isEmpty() || osis.isEmpty()) {
                JOptionPane.showMessageDialog(dialog,"Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            manager.getStudents().add(new Student(Fname, manager.getStudents().size() + 1));
            JOptionPane.showMessageDialog(dialog, "Student added.", "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        remove.addActionListener(e -> {
        System.out.print("Enter student name to remove: ");
        String name = input.nextLine().trim();
        for (Student student : manager.getStudents()) {
            if (student.getName().equalsIgnoreCase(name)) {
                manager.getStudents().remove(student);
                System.out.println("Student removed.");
                return;
            }
        }
        System.out.println("Student not found.");
        });

        showInfo.addActionListener(e -> {
            System.out.print("Enter OSIS: ");
            String osis = input.nextLine().trim();
            
            for (Student student : manager.getStudents()) {
                if (student.getOsis() == Integer.parseInt(osis)) {
                    return;
                }
            }
            System.out.println("Student not found.");
        });

        cancel.addActionListener(e -> {

        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(buttonPanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);
    }
}