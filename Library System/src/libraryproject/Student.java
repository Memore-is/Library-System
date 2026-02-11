package libraryproject; 
import java.util.ArrayList; 

public class Student {

    private String name;
    private ArrayList<Book> readingHistory;

public Student(String name) {
    this. name = name;
    readingHistory = new ArrayList<Book>();
}
public String getName() {
    return name;
}
public ArrayList<Book> getReadingHistory() {
    return readingHistory;
}
public void borrowBook(Book book) { 
    if (book. isAvailable()) {
        readingHistory. add(book);
        book.setAvailable(false);
    }
}
}    