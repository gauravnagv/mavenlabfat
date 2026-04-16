package com.library.management;

import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library = new Library("City Library");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║   LIBRARY MANAGEMENT SYSTEM            ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        initializeBooks();
        menu();
        scanner.close();
    }

    private static void initializeBooks() {
        library.addBook(new Book("B001", "Java Programming", "Herbert Schildt"));
        library.addBook(new Book("B002", "Design Patterns", "Gang of Four"));
        library.addBook(new Book("B003", "Clean Code", "Robert C. Martin"));
        library.addBook(new Book("B004", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("B005", "Spring in Action", "Craig Walls"));
    }

    private static void menu() {
        while (true) {
            System.out.println("\n┌─────────────────────────────────────────┐");
            System.out.println("│         MAIN MENU                       │");
            System.out.println("├─────────────────────────────────────────┤");
            System.out.println("│ 1. Register User                        │");
            System.out.println("│ 2. Issue Book to User                   │");
            System.out.println("│ 3. Return Book                          │");
            System.out.println("│ 4. View User Details                    │");
            System.out.println("│ 5. View Available Books                 │");
            System.out.println("│ 6. Exit                                 │");
            System.out.println("└─────────────────────────────────────────┘");
            System.out.print("Enter your choice (1-6): ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        registerUser();
                        break;
                    case 2:
                        issueBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        viewUserDetails();
                        break;
                    case 5:
                        library.displayAvailableBooks();
                        break;
                    case 6:
                        System.out.println("\n✓ Thank you for using Library Management System!");
                        System.out.println("  Exiting...\n");
                        return;
                    default:
                        System.out.println("✗ Invalid choice! Please try again.");
                }
            } catch (Exception e) {
                System.out.println("✗ Invalid input! Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void registerUser() {
        System.out.println("\n--- Register New User ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine().trim();

        System.out.print("Enter Number of Books to Issue: ");
        int booksCount = scanner.nextInt();

        System.out.print("Enter Borrowing Duration (in days): ");
        int borrowingDays = scanner.nextInt();
        scanner.nextLine();

        User user = new User(userId, userName, booksCount, borrowingDays);
        library.registerUser(user);

        user.displayUserDetails();
        user.displayFineDetails();
    }

    private static void issueBook() {
        System.out.println("\n--- Issue Book ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();

        library.issueBook(userId, bookId);
    }

    private static void returnBook() {
        System.out.println("\n--- Return Book ---");
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();

        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine().trim();

        library.returnBook(userId, bookId);
    }

    private static void viewUserDetails() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine().trim();

        boolean found = false;
        for (User user : library.getUsers()) {
            if (user.getUserId().equals(userId)) {
                user.displayUserDetails();
                user.displayIssuedBooks();
                user.displayFineDetails();
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("\n✗ User not found!");
        }
    }
}
