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
        manager.loadStudents();
        
        frame = new JFrame("Library System");
        
        JButton browse = new JButton("Browse");
        JButton borrowed = new JButton("Show Borrowed");
        JButton borrow = new JButton("Borrow");
        JButton returnB = new JButton("Return");
        JButton manage = new JButton("Manage Books");
        JButton students = new JButton("Students");

        Allbooks = new JTextArea(40, 120);         // rows show how many objects to display at once, columns for width
        Allbooks.setFont(new Font("Times New Roman", Font.PLAIN, 16));
        Allbooks.setEditable(false);        // uneditable books
        frame.setLayout(new FlowLayout());      // to arrange in row
        
        frame.add(browse);
        frame.add(borrowed);
        frame.add(borrow);
        frame.add(returnB);
        frame.add(manage);
        frame.add(students);
        frame.add(new JScrollPane(Allbooks));
        
        browse.addActionListener(e -> reload());
        borrowed.addActionListener(e -> borrowed());
        borrow.addActionListener(e -> borrow());
        returnB.addActionListener(e -> returnB());
        manage.addActionListener(e -> manageBooks());
        students.addActionListener(e-> students());

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH); // mwuahaha fullscreen now
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);     // make visible
    }

    private void reload() {  // done
        String text = "";
        for (Book b : manager.getBooks()) {
            text += b.toString() + "\n";
        }
        Allbooks.setText(text);
    }

    private void borrowed() { // done
        Allbooks.setText("");
        for (Student student : manager.getStudents()) {
            if (!student.getBorrowed().isEmpty()) {
                String borrowedBooks = "Borrowed books for " + student.getName() + " (OSIS: " + student.getOsis() + "): ";

                for (Book book : student.getBorrowed()) {
                    borrowedBooks += book.getTitle() + ", ";
                }
                Allbooks.append(borrowedBooks);
            }
        }
    }

    private void borrow() { // done
        Student student = getStudent();

        if (student == null) {
           Message("Student not found.", "Error");
           return;
        }

        String title = JOptionPane.showInputDialog(frame, "Enter book title:");
        
        for (Book b : manager.getBooks()) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                if (b.checkAvailable()) {
                    student.borrowBook(b);
                    manager.saveBooks();
                    Message("Borrow successful.", "Success");
                    reload();
                } else {
                    Message("Book unavailable.", "Error");
                }
                break;
            }
        }
        Message("Book not found.", "Error");
    }

    private void returnB() { // done
        Student student = getStudent();

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
    }

    private void manageBooks() {
        String total = JOptionPane.showInputDialog(frame, "Enter total unique books:");

        if (total.isEmpty() || stringToInt(total) == -1)

        for (int i = 0; i < stringToInt(total); i++) {
            JDialog dialog = new JDialog(frame, "Add Book", true);      // JDialog = a window that "blocks" the main window; true = modal (user must respond to this first)
    
            dialog.setLayout(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(5, 5, 5, 5); // padding
    
            JButton ok = new JButton( "OK");
            JButton cancel = new JButton("Cancel");
            
            JPanel buttonPanel = new JPanel();
            buttonPanel.add(ok);
            buttonPanel.add(cancel);
    
            JTextField titleField = new JTextField(20);    // 20 chars (width)
            JTextField authorField = new JTextField(20);
            JTextField genreField = new JTextField(20);
            JTextField copiesField = new JTextField(5);
    
            gbc.gridx = 0; gbc.gridy = 0; dialog.add(new JLabel("Title:"), gbc);
            gbc.gridx = 1; dialog.add(titleField, gbc);
    
            gbc.gridx = 0; gbc.gridy = 1; dialog.add(new JLabel("Author:"), gbc);
            gbc.gridx = 1; dialog.add(authorField, gbc);
    
            gbc.gridx = 0; gbc.gridy = 2; dialog.add(new JLabel("Genre:"), gbc);
            gbc.gridx = 1; dialog.add(genreField, gbc);
    
            gbc.gridx = 0; gbc.gridy = 3; dialog.add(new JLabel("Copies:"), gbc);
            gbc.gridx = 1; dialog.add(copiesField, gbc);
    
            // Row 4: buttons span both columns (gridwidth = 2)
            gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 2; dialog.add(buttonPanel, gbc);
    
            ok.addActionListener(e -> {
                try {
                    String title = titleField.getText().trim();
                    String author = authorField.getText().trim();
                    String genre = genreField.getText().trim();
                    int copies = stringToInt(copiesField.getText().trim());
    
                    if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                        Message("Fields cannot be empty.", "Error");
                        return;  // rerun action listener
                    }
    
                    manager.getBooks().add(new Book(title, author, genre, copies));
                    manager.saveBooks();
                    Message("Book added.", "Success");
                    
                    dialog.dispose();
    
                } catch (NumberFormatException ex) {
                    Message("Copies must be a valid number.", "Error");
                }
            });
    
            cancel.addActionListener(e -> dialog.dispose());   // close dialog
    
            dialog.pack();  // autosize the dialog size based on its contents
            dialog.setLocationRelativeTo(frame);  // center on parent frame
            dialog.setVisible(true);
        }
    }

    private void students() {
        JDialog dialog = new JDialog(frame, "Student Information", true);
    
        JButton add = new JButton( "Add");
        JButton remove = new JButton("Remove");
        JButton showInfo = new JButton("Show Information");
        JButton cancel = new JButton("Cancel");
        
        JPanel buttonPanel = new JPanel();
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
                Message("Fields cannot be empty.", "Error");
                return;
            }
            
            manager.getStudents().add(new Student(Fname, Lname, manager.getStudents().size() + 1));
            Message("Student added.", "Success");
        });

        remove.addActionListener(e -> {
            JTextField FnameField = new JTextField(20);
            JTextField LnameField = new JTextField(20);
            JTextField osisField = new JTextField(20);

            String Fname = FnameField.getText().trim();
            String Lname = LnameField.getText().trim();
            int osis = stringToInt(osisField.getText().trim());

            if (Fname.isEmpty() || Lname.isEmpty() || osis == -1) {
                Message("Fields cannot be empty.", "Error");
                return;
            }

            for (Student student : manager.getStudents()) {
                if (student.getFname().equalsIgnoreCase(Fname) && student.getLname().equalsIgnoreCase(Lname) && student.getOsis() == osis) {
                    manager.getStudents().remove(student);
                    Message("Student removed.", "Success");
                    return;
                }
            }
            Message("Student not found.", "Error");
        });

        showInfo.addActionListener(e -> {
            Student student = getStudent();
            if (student != null) {
                showStudentInfo(student.getOsis());
            }
        });
        cancel.addActionListener(e -> dialog.dispose());
    }

    private void showStudentInfo(int osis) {
        for (Student student : manager.getStudents()) {
            if (student.getOsis() == osis) {
                String info = "Student Name: " + student.getFname() + " " + student.getLname() + "\n";
                info += "OSIS: " + student.getOsis() + "\n";
                info += "Borrowed Books: \n";
                for (Book book : student.getBorrowed()) {
                    info += "- " + book.getTitle() + "\n";
                }
                Message(info, "Student Information");
                return;
            }
            return;
        }
    }

    private void Message(String msg, String title) {
        JOptionPane.showMessageDialog(frame, msg, title, JOptionPane.INFORMATION_MESSAGE);
    }

    private Student getStudent(){
        Student student = null;
        int osis = getOsis();

        for (Student s : manager.getStudents()) {
            if (s.getOsis() == osis) {
                student = s;
                break;
            }
        }
        return student;
    }

    private int getOsis() {
        int osis;
        while (true) {
            String osisStr = JOptionPane.showInputDialog(frame, "Enter OSIS: ");
             if (osisStr == null) {
                Message("Operation cancelled.", "Cancelled");
                return -1;
             }

            try {
                osis = Integer.parseInt(osisStr);
                break;
            } catch (NumberFormatException ex) {
                Message("Invalid OSIS. Please enter a valid number.", "Error");
            }
        }
        return osis;
    }

    private int stringToInt(String str) {
        if (str.isEmpty()) {
            Message("Input cannot be empty.", "Error");
            return -1;
        }

        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            Message("Invalid number format: " + str, "Error");
            return -1;
        }
    }
}