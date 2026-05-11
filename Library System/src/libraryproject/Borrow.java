package libraryproject;

import java.util.Scanner;

public class Borrow {
    private Manager manager = new Manager();
    private Scanner input = new Scanner(System.in);

    public Borrow() {
        manager.loadBooks();
        System.out.print("Enter title of book: ");
        String title = input.nextLine().trim();

        for (Book book : manager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                if (book.checkAvailable()) {
                    book.setCopies(book.getCopies() - 1);
                    System.out.println("You have borrowed: " + book.getTitle());
                    manager.saveBooks();
                } else {
                    System.out.println("Book is not available.");
                }
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
