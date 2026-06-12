package libraryproject; 

import java.util.ArrayList; 

public class Student {
    private String name;
    private int id;
    private ArrayList<Book> borrowed, readingHistory;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
        borrowed = new ArrayList<Book>();
        readingHistory = new ArrayList<Book>();
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public ArrayList<Book> getBorrowed() {
        return borrowed;
    }

    public ArrayList<Book> getReadingHistory() {
        return readingHistory;
    }

    public void borrowBook(Book book) { 
        if (book.checkAvailable()) {
            borrowed.add(book);
            readingHistory.add(book);
            book.setCopies(book.getCopies() - 1);
        }
        else {
            System.out.println("Book is not available.");
        }
    }

    public void returnBook(Book book) {
        if (borrowed.contains(book)) {
            borrowed.remove(book);
            book.setCopies(book.getCopies() + 1);
        }
        else {
            System.out.println("Book was not borrowed.");
        }
    }
}