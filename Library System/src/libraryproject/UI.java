package libraryproject;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Manager manager = new Manager();

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("\nOptions: \n1. Browse \n2. Borrow or Return Book \n3. Add or Remove Book \n4. Students \n5. Exit");
            System.out.print("\nPlease enter function number (1-5): ");
            int function = input.nextInt();

            if (function < 1 || function > 5) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            else if (function == 1) {
                System.out.println("\nAvailable Books: \n");
                
                for (Book book : manager.getBooks()) {
                    System.out.println(book);
                }
            }
            else if (function == 2) {
                System.out.println("Borrow or Return Book");
                new BRB(manager, input);
            }
            else if (function == 3) {
                System.out.println("Add or Remove Book");
                new Adjust(manager, input);
            }
            else if (function == 4) {
                System.out.println("Students");
                new Students(manager, input);
            }
            else {
                System.out.println("Have a great day!");
                break;
            }
        }
        input.close();
    }
}