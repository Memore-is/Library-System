package libraryproject;

import java.util.Scanner;

public class Adjust {
    private Manager manager = new Manager();
    private Scanner input = new Scanner(System.in);
    
    public Adjust() {
        manager.loadBooks();
        while (true) {
            System.out.println("Options: \n1. Add Books \n2. Remove Books \n3. Go Back");
            System.out.print("Please enter function number (1-3): ");
            int function = input.nextInt();
            input.nextLine(); 

            if (function < 1 || function > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            } 
            else if (function == 1) {
                System.out.println("Add Books");
                addBooks();
            } 
            else if (function == 2) {
                System.out.println("Remove Books");
                removeBooks();
            }
            else {
                System.out.println("Going back to main menu...");
                break;
            }
        }
    }

    private void addBooks() {
        System.out.print("Enter total number of [unique] books: ");
        int addnum = input.nextInt();
        input.nextLine();

        for (int i = 0; i < addnum; i++) {
            System.out.print("Enter title of book " + (i + 1) + ": ");
            String title = input.nextLine().trim();

            System.out.print("Enter author name of book " + (i + 1) + ": ");
            String author = input.nextLine().trim();

            System.out.print("Enter genre of book " + (i + 1) + ": ");
            String genre = input.nextLine().trim();

            System.out.print("Enter quantity of book " + (i + 1) + ": ");
            int copies = input.nextInt();
            input.nextLine(); 

            manager.getBooks().add(new Book(title, author, genre, copies));
            System.out.println("Book added.");
        }
        manager.saveBooks();
    }

    private void removeBooks() {
        System.out.print("Enter title: ");
        String title = input.nextLine().trim();

        for (Book book : manager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                manager.getBooks().remove(book);
                System.out.println("Book removed successfully.");
                manager.saveBooks();
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
