package libraryproject;

import java.util.ArrayList;
import java.util.Scanner;

public class Adjust {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner input = new Scanner(System.in);
    
    public Adjust() {
        loadBooks();
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

            books.add(new Book(title, author, genre, copies));
            System.out.println("Book added.");
        }
        saveBooks();
    }

    private void removeBooks() {
        System.out.print("Enter title: ");
        String title = input.nextLine().trim();

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                books.remove(book);
                System.out.println("Book removed successfully.");
                saveBooks();
                return;
            }
        }
        System.out.println("Book not found.");
    }

    private void loadBooks() {
        java.io.File file = new java.io.File("books.txt");
        if (!file.exists()) {
            file = new java.io.File("Library System/src/libraryproject/books.txt");
        }
        if (!file.exists()) {
            System.err.println("Could not find books.txt.");
            return;
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty() || line.startsWith("//")) continue;
                String[] parts = line.split("\\|");
                if (parts.length < 4) continue;
                String title = parts[0].trim();
                String author = parts[1].trim();
                String genre = parts[2].trim();
                int copies = Integer.parseInt(parts[3].trim());
                books.add(new Book(title, author, genre, copies));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveBooks() {
        java.io.File file = new java.io.File("books.txt");
        if (!file.exists()) {
            file = new java.io.File("Library System/src/libraryproject/books.txt");
        }
        try (java.io.PrintWriter writer = new java.io.PrintWriter(file)) {
            for (Book book : books) {
                writer.println(book.getTitle() + "|" + book.getAuthor() + "|" + book.getGenre() + "|" + book.getCopies());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}