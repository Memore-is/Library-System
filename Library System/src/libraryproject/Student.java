package libraryproject; 

import java.util.ArrayList; 

public class Student {
    private String name;
    private ArrayList<Book> borrowed;
    private ArrayList<Book> readingHistory;

    public Student(String name) {
        this. name = name;
        borrowed = new ArrayList<Book>();
        readingHistory = new ArrayList<Book>();
    }
    public String getName() {
        return name;
    }
    public ArrayList<Book> getReadingHistory() {
        return readingHistory;
    }
    public void borrowBook(Book book) { 
        if (book.checkAvailable()) {
            borrowed.add(book);
            readingHistory.add(book);
        }
    }
}