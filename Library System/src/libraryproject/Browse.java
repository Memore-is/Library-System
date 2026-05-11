package libraryproject;

public class Browse {
    private Manager manager = new Manager();

    public Browse() {
        manager.loadBooks();
        
        System.out.println("Available Books:");
        for (Book book : manager.getBooks()) {
            System.out.println(book);
        }
    }
}