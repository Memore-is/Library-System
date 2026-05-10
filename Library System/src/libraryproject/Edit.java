package libraryproject;

import java.util.Scanner;

public class Edit {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Options: \n1. Add Books \n2. Remove Books \n3. Go Back");
            System.out.print("Please enter function number (1-3): ");
            int function = input.nextInt();

        if (function < 1 || function > 3) {
            System.out.println("Invalid choice. Please enter a number between 1 and 3.");
        } 
        else if (function == 1) {
            System.out.println("Add Books");
        } 
        else if (function == 2) {
            System.out.println("Remove Books");
        } 
        else {
            System.out.println("Going back to main menu...");
            break;
        }
    }

    UI.main(null); // Call the main method of UI to return to the main menu
}
