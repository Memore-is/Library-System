package libraryproject;

import java.util.Scanner;

public class Adjust {
    Scanner input = new Scanner(System.in);
    
    public Adjust() {
        while (true) {
            System.out.println("Options: \n1. Add Books \n2. Remove Books \n3. Go Back");
            System.out.print("Please enter function number (1-3): ");
            int function = input.nextInt();

            if (function < 1 || function > 3) {
                System.out.println("Invalid choice. Please enter a number between 1 and 3.");
            } 
            else if (function == 1) {
                System.out.println("Add Books");
                addBooks(null);
            } 
            else if (function == 2) {
                System.out.println("Remove Books");
                removeBooks(null);
            } 
            else {
                System.out.println("Going back to main menu...");
                break;
            }
        }

        UI.main(null); // Call the main method of UI to return to the main menu
    }

    // methods for adding and removing books from the library

    public void addBooks(Library library) {
        System.out.print("Enter the number of books to add to the library: ");
        int numBooks = input.nextInt();

        for (int i = 0; i < numBooks; i++) {
            System.out.print("Enter the title of book " + (i + 1) + ": ");
            String title = input.nextLine();

            System.out.print("Enter the author of book " + (i + 1) + ": ");
            String author = input.nextLine();

            System.out.print("Enter the genre of book " + (i + 1) + ": ");
            String genre = input.nextLine();

            System.out.print("Enter the quantity of book " + (i + 1) + ": ");
            int copies = input.nextInt();
  
            library.books.add(new Book(title, author, genre, copies));
        }
    }

    public void removeBooks(Library library) {
        System.out.print("Enter the title of the book to remove: ");
        String title = input.nextLine();

        for (Book book : library.books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                library.books.remove(book);
                System.out.println("Book removed successfully.");
                return;
            }
        }
        System.out.println("Book not found in the library.");
    }
}