package libraryproject;

import java.util.Scanner;

public class BRB {
    private Manager manager = new Manager();
    private Scanner input = new Scanner(System.in);

    public BRB() {
        // Manager constructor already loads books and students
        while (true) {
            System.out.println("Options: \n1. Borrow Books \n2. Return Books \n3. Go Back");
            System.out.print("Please enter function number (1-3): ");
            int function = input.nextInt();
            input.nextLine(); 

            if (function < 1 || function > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            } 
            else if (function == 1) {
                System.out.println("Borrow Books");
                Borrow();
            } 
            else if (function == 2) {
                System.out.println("Return Books");
                Return();
            }
            else {
                System.out.println("Going back to main menu...");
                break;
            }
        }
    }

    private void Borrow() {
        System.out.print("Enter student OSIS: ");
        String osisStr = input.nextLine().trim();
        int osis;
        try {
            osis = Integer.parseInt(osisStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid OSIS.");
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
            System.out.println("Student not found.");
            return;
        }

        System.out.print("Enter title of book: ");
        String title = input.nextLine().trim();

        for (Book book : manager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.checkAvailable()) {
                    student.borrowBook(book);
                    manager.saveBooks();
                    System.out.println("Borrow successful.");
                } else {
                    System.out.println("Book is not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void Return() {
        System.out.print("Enter the title of the book to return: ");
        String title = input.nextLine().trim();

        // Ask which student is returning
        System.out.print("Enter student OSIS: ");
        String osisStr = input.nextLine().trim();
        int osis;
        try {
            osis = Integer.parseInt(osisStr);
        } catch (NumberFormatException e) {
            System.out.println("Invalid OSIS.");
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
            System.out.println("Student not found.");
            return;
        }

        for (Book book : manager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                student.returnBook(book);
                manager.saveBooks();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
