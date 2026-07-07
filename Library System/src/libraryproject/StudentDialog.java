package libraryproject;

import javax.swing.*;

public class StudentDialog extends JDialog {
    // add, remove, show info

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
            
        });

        remove.addActionListener(e -> {

        });

        showInfo.addActionListener(e -> {

        });

        cancel.addActionListener(e -> {

        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(buttonPanel);
        pack();
        setLocationRelativeTo(frame);
        setVisible(true);


    }


        // preconditions: name cannot be a number and must be longer than 1 letter

        if (!name.isEmpty()) {
            manager.getStudents().add(new Student(name, manager.getStudents().size() + 1));
            System.out.println("Student added.");
        } else {
            System.out.println("Name cannot be empty.");
        }
    }

    private void removeStudent() {
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
    }

    private void showStudentInfo() {
        System.out.print("Enter OSIS: ");
        String osis = input.nextLine().trim();
        for (Student student : manager.getStudents()) {
            if (student.getOsis() == Integer.parseInt(osis)) {
                student.showInfo();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}