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

        borrowed.addActionListener(e -> {
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
        });

        borrow.addActionListener(e -> {
            Student student = null;
            int osis = getOsis();

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
            int osis = getOsis();

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

        manage.addActionListener(e -> {
            String total = JOptionPane.showInputDialog(frame, "Enter total unique books:");
            for (int i = 0; i < Integer.parseInt(total); i++) {
                JDialog dialog = new JDialog(frame, title, true);      // JDialog = a window that "blocks" the main window; true = modal (user must respond to this first)
        
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
                        int copies = Integer.parseInt(copiesField.getText().trim());
        
                        if (title.isEmpty() || author.isEmpty() || genre.isEmpty()) {
                            JOptionPane.showMessageDialog(dialog, "Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                            return;  // rerun action listener
                        }
        
                        manager.getBooks().add(new Book(title, author, genre, copies));
                        manager.saveBooks();
                        JOptionPane.showMessageDialog(frame, "Book added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        
                        dialog.dispose();
        
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(dialog, "Copies must be a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                });
        
                cancel.addActionListener(e -> dialog.dispose());   // close dialog
        
                dialog.pack();  // autosize the dialog size based on its contents
                dialog.setLocationRelativeTo(frame);  // center on parent frame
                dialog.setVisible(true);
            }
        });

        students.addActionListener(e-> {
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
                    JOptionPane.showMessageDialog(dialog,"Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                manager.getStudents().add(new Student(Fname, Lname, manager.getStudents().size() + 1));
                JOptionPane.showMessageDialog(dialog, "Student added.", "Success", JOptionPane.INFORMATION_MESSAGE);
            });

            remove.addActionListener(e -> {
                JTextField FnameField = new JTextField(20);
                JTextField LnameField = new JTextField(20);
                JTextField osisField = new JTextField(20);

                String Fname = FnameField.getText().trim();
                String Lname = LnameField.getText().trim();
                int osis = Integer.parseInt(osisField.getText().trim());

                if (Fname.isEmpty() || Lname.isEmpty() || osis == 0) {
                    JOptionPane.showMessageDialog(dialog,"Fields cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                for (Student student : manager.getStudents()) {
                    if (student.getFname().equalsIgnoreCase(Fname) && student.getLname().equalsIgnoreCase(Lname) && student.getOsis() == osis) {
                        manager.getStudents().remove(student);
                        JOptionPane.showMessageDialog(dialog, "Student added.", "Success", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(dialog, "Student not found.", "Error", JOptionPane.ERROR_MESSAGE);
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
         });

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

    private int getOsis() {
        int osis;
        while (true) {
            String osisStr = JOptionPane.showInputDialog(frame, "Enter OSIS: ");
             if (osisStr == null) {
                JOptionPane.showMessageDialog(Allbooks, osisStr);
             }

            try {
                osis = Integer.parseInt(osisStr);
                break;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid OSIS. Please enter a valid number.");
            }
        }
        return osis;
    }
}