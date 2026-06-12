package libraryproject;

import java.util.Scanner;

public class UI {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Welcome to the Library!");

        while (true) {
            System.out.println("\nOptions: \n1. Browse \n2. Borrow or Return Book \n3. Add or Remove Book \n4. Edit Students \n5. Exit");
            System.out.print("\nPlease enter function number (1-6): ");
            int function = input.nextInt();

            if (function < 1 || function > 5) {
                System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
            else if (function == 1) {
                System.out.println("Browse");
                new Browse();
            }
            else if (function == 2) {
                System.out.println("Borrow or Return Book");
                new Return();
            }
            else if (function == 3) {
                System.out.println("Add or Remove Book");
                new Adjust();
            }
            else if (function == 4) {
                System.out.println("Edit Students");
                new Edit();
            }
            else {
                System.out.println("Have a great day!");
                break;
            }
        }
        input.close();
    }
}