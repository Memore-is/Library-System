package libraryproject;

// for GUI
import javax.swing.*;
import java.awt.*;

public class LibraryGUI {
    Manager manager = new Manager();
    JFrame frame;
    JTextArea Allbooks;

    public LibraryGUI() {
        manager.loadBooks();

        frame = new JFrame("Library System");   // main window + title
        
        JButton browse = new JButton("Browse");  
        JButton borrow = new JButton("Borrow");
        JButton returnB = new JButton("Return");
        JButton add = new JButton("Add");
        JButton remove = new JButton("Remove");
        JButton students = new JButton("Students");

        Allbooks = new JTextArea(20, 40);         // rows show how many objects to display at once, columns for width
        Allbooks.setEditable(false);        // uneditable books
        frame.setLayout(new FlowLayout());      // to arrange in row

        frame.add(browse);
        frame.add(borrow);
        frame.add(returnB);
        frame.add(add);
        frame.add(remove);
        frame.add(students);

        // browse button functions (completed)
        frame.add(new JScrollPane(Allbooks));
        browse.addActionListener(e -> {
            String text = "";

            for (Book b : manager.getBooks()) {
                text += b.toString() + "\n";
            }
            Allbooks.setText(text);
        });

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
                        refreshAllBooks();
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
                    refreshAllBooks();
                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        });

        add.addActionListener(e -> 
            Dialog.showAddBookDialog(frame, manager, Allbooks)  // different class (too long)
        );

        remove.addActionListener(e -> 
            Dialog.showRemoveBookDialog(frame, "Remove Book", manager, Allbooks)
        );

        students.addActionListener(e-> 
            Dialog.showStudentDialog(frame, manager)
        );

        frame.setSize(500, 400);        // in pixels for sm reason
        frame.setLocationRelativeTo(null);          // center?

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);     // make visible
    }

    private void refreshAllBooks() {
        String text = "";
        for (Book b : manager.getBooks()) {
            text += b.toString() + "\n";
        }
        Allbooks.setText(text);
    }
}