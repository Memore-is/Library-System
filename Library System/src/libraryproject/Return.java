package libraryproject;

import java.util.Scanner;

public class Return {
    private Manager manager = new Manager();
    private Scanner input = new Scanner(System.in);

    public Return() {
        manager.loadBooks();
        System.out.print("Enter the title of the book to return: ");
        String title = input.nextLine().trim();

        for (Book book : manager.getBooks()) {
            if (book.getTitle().equalsIgnoreCase(title)) {

                book.setCopies(book.getCopies() + 1);
                System.out.println("You have returned: " + book.getTitle());
                manager.saveBooks();
                
                return;
            }
        }
        System.out.println("Book not found.");
    }
}
