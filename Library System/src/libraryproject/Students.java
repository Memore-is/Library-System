package libraryproject;

import java.util.Scanner;

public class Students {
    private Manager manager;
    private Scanner input;

    public Students(Manager manager, Scanner input) {
        this.manager = manager;
        this.input = input;
        while (true) {
            System.out.println("Options: \n1. Add Student \n2. Remove Student \n3. Show Student Info \n4. Go Back");
            System.out.print("Please enter function number (1-4): ");
            int function = input.nextInt();
            input.nextLine();

            if (function < 1 || function > 4) {
                System.out.println("Invalid choice. Please enter a number between 1 and 4.");
            } 
            else if (function == 1) {
                System.out.println("Add Student");
                addStudent();
            } 
            else if (function == 2) {
                System.out.println("Remove Student");
                removeStudent();
            }
            else if (function == 3) {
                System.out.println("Show Student Info");
                showStudentInfo();
            }
            else {
                System.out.println("Going back to main menu...");
                break;
            }
        }
    }

    private void addStudent() {
        System.out.print("Enter student first name: ");
        String fname = input.nextLine().trim();

        System.out.print("Enter student last name: ");
        String lname = input.nextLine().trim();

        System.out.print("Enter student ID: ");
        int osis = input.nextInt();

        for (i = 0; i > name.length(); i++) {
            if (char.At[i] >) {

            }
        }

        // preconditions: name cannot be a number and must be longer than 1 letter

        if (!name.isEmpty()) {
            manager.getStudents().add(new Student(name, manager.getStudents().size() + 1));
            System.out.println("Student added.");
        } else {
            System.out.println("Name cannot be empty.");
        }
    }

    private void removeStudent() {
        System.out.print("Enter student name to remove: ");
        String name = input.nextLine().trim();
        for (Student student : manager.getStudents()) {
            if (student.getName().equalsIgnoreCase(name)) {
                manager.getStudents().remove(student);
                System.out.println("Student removed.");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    private void showStudentInfo() {
        System.out.print("Enter OSIS: ");
        String osis = input.nextLine().trim();
        for (Student student : manager.getStudents()) {
            if (student.getOsis() == Integer.parseInt(osis)) {
                student.showInfo();
                return;
            }
        }
        System.out.println("Student not found.");
    }
}