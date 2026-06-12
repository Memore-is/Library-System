package libraryproject; 

import java.util.ArrayList; 

public class Student {
    private String name;
    private int osis;
    private ArrayList<Book> borrowed;

    public Student(String name, int osis) {
        this.name = name;
        this.osis = osis;
        borrowed = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public int getOsis() {
        return osis;
    }

    public ArrayList<Book> getBorrowed() {
        return borrowed;
    }

    public void showInfo() {
        System.out.println("Name: " + name);
        System.out.println("ID: " + osis);
        System.out.println("Borrowed Books:");
        if (borrowed.isEmpty()) {
            System.out.println("None");
        } else {
            for (Book book : borrowed) {
                System.out.println("|" + book.getTitle());
            }
        }
    }

    public void borrowBook(Book book) { 
        if (book.checkAvailable()) {
            borrowed.add(book);
            book.setCopies(book.getCopies() - 1);
            System.out.println("You have borrowed: " + book.getTitle());
        }
        else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowed.contains(book)) {
            borrowed.remove(book);
            book.setCopies(book.getCopies() + 1);
            System.out.println("You have returned: " + book.getTitle());
        }
        else {
            System.out.println("Book was not borrowed.");
        }
    }
}