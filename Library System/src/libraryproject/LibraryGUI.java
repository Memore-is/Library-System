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
        
        JButton browse = new JButton("Browse Books");    
        JButton borrow = new JButton("Borrow Book");
        JButton returnB = new JButton("Return Book");
        JButton add = new JButton("Add Book"); 

        Allbooks = new JTextArea(20, 40);         // rows show how many objects to display at once, columns for width
        Allbooks.setEditable(false);        // uneditable books
        frame.setLayout(new FlowLayout());      // to arrange in row

        frame.add(browse);
        frame.add(borrow);
        frame.add(returnB);
        frame.add(add);

        frame.add(new JScrollPane(Allbooks));

        browse.addActionListener(e -> {
            String text = "";

                for (Book b : manager.getBooks()) {
                text += b.toString() + "\n";
            }

            Allbooks.setText(text);
        });

        borrow.addActionListener(e -> {
            String title = JOptionPane.showInputDialog(frame, "Enter book title:");
            for (Book b : manager.getBooks()) {

                        if (b.getTitle().equalsIgnoreCase(title)) {
                    if (b.checkAvailable()) {
                        b.setCopies(b.getCopies() - 1);
                        manager.saveBooks();

                        JOptionPane.showMessageDialog(frame, "You borrowed " + b.getTitle());
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
            String title = JOptionPane.showInputDialog(frame, "Enter book title:");

            for (Book b : manager.getBooks()) {
                if (b.getTitle().equalsIgnoreCase(title)) {
                    b.setCopies(b.getCopies() + 1);
                    manager.saveBooks();

                    JOptionPane.showMessageDialog(frame, "Book returned.");
                    refreshAllBooks();

                    return;
                }
            }
            JOptionPane.showMessageDialog(frame, "Book not found.");
        });


        add.addActionListener(e -> showAddBookDialog());

        frame.setSize(500, 400);        // in pixels for sm reason
        frame.setLocationRelativeTo(null);          // center?

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);     // make visible
    }

    private void showAddBookDialog() {
        BookDialog.showAddBookDialog(frame, manager, Allbooks);
    }

    private void refreshAllBooks() {
        String text = "";
        for (Book b : manager.getBooks()) {
            text += b.toString() + "\n";
        }
        Allbooks.setText(text);
    }
}