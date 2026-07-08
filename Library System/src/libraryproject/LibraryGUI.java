package libraryproject;

// for GUI
import javax.swing.*;
import java.awt.*;

public class LibraryGUI {
    Manager manager = new Manager();
    JFrame frame;
    JTextArea Allbooks;

    public static void main(String[] args) {
        new LibraryGUI();
    }

    public LibraryGUI() {
        manager.loadBooks();

        frame = new JFrame("Library System");   // main window + title
        
        JButton browse = new JButton("Browse");  
        JButton borrow = new JButton("Borrow");
        JButton returnB = new JButton("Return");
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton students = new JButton("Students");

        Allbooks = new JTextArea(40, 120);         // rows show how many objects to display at once, columns for width
        Allbooks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        Allbooks.setEditable(false);        // uneditable books
        frame.setLayout(new FlowLayout());      // to arrange in row
        
        frame.add(browse);
        frame.add(borrow);
        frame.add(returnB);
        frame.add(add);
        frame.add(remove);
        frame.add(students);
        frame.add(new JScrollPane(Allbooks));
        
        browse.addActionListener(e -> reload()); // not working bec

        borrow.addActionListener(e -> {
            int osis;
            String osisStr = JOptionPane.showInputDialog(frame, "Enter OSIS: ");
            try {
                osis = Integer.parseInt(osisStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid OSIS.");
                return;
            }

            Student student = null;
            for (Student s : manager.getStudents()) {
                if (s.getOsis() == osis) {
                    student = s;
                    break;
                }
            }

            if (student == null) {
                JOptionPane.showMessageDialog(frame, "Student not found.");
                return;
            }

            String title = JOptionPane.showInputDialog(frame, "Enter book title:");
            
            for (Book b : manager.getBooks()) {
                if (b.getTitle().equalsIgnoreCase(title)) {
                    if (b.checkAvailable()) {
                        student.borrowBook(b);
                        manager.saveBooks();
                        JOptionPane.showMessageDialog(frame, "Borrow successful.");
                        reload();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Book unavailable.");
                    }
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        });

        returnB.addActionListener(e -> {
            int osis;
            String osisStr = JOptionPane.showInputDialog(frame, "Enter OSIS: ");
            try {
                osis = Integer.parseInt(osisStr);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid OSIS.");
                return;
            }

            Student student = null;
            for (Student s : manager.getStudents()) {
                if (s.getOsis() == osis) {
                    student = s;
                    break;
                }
            }

            if (student == null) {
                JOptionPane.showMessageDialog(frame, "Student not found.");
                return;
            }

            String title = JOptionPane.showInputDialog(frame, "Enter book title:");

            for (Book b : manager.getBooks()) {
                if (b.getTitle().equalsIgnoreCase(title)) {
                    student.returnBook(b);
                    manager.saveBooks();
                    JOptionPane.showMessageDialog(frame, "Book returned.");
                    reload();
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        });

        add.addActionListener(e -> {
            String total = JOptionPane.showInputDialog(frame, "Enter total unique books:");
            for (int i = 0; i < Integer.parseInt(total); i++) {
                new Dialog(frame, manager, Allbooks, "Add Book");
            }
        });

        remove.addActionListener(e ->

            new Dialog(frame, manager, Allbooks, "Remove Book")
        );

        students.addActionListener(e-> 
            new StudentDialog(frame, manager, Allbooks)
        );

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // mwuahaha fullscreen now
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     // make visible
    }

    private void reload() {
        String text = "";
        for (Book b : manager.getBooks()) {
            text += b.toString() + "\n";
        }
        Allbooks.setText(text);
    }
}