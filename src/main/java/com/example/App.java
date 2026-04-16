package com.library.management;

import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library = new Library("City Library");
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("=== LIBRARY MANAGEMENT SYSTEM ===\n");
        addBooks();
        menu();
    }

    private static void addBooks() {
        library.addBook(new Book("B001", "Java Programming", "Herbert Schildt"));
        library.addBook(new Book("B002", "Design Patterns", "Gang of Four"));
        library.addBook(new Book("B003", "Clean Code", "Robert C. Martin"));
    }

    private static void menu() {
        while (true) {
            System.out.println("\n1. Register User\n2. Issue Book\n3. View User Details\n4. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                registerUser();
            } else if (choice == 2) {
                issueBook();
            } else if (choice == 3) {
                viewUserDetails();
            } else if (choice == 4) {
                System.out.println("Exiting...");
                break;
            } else {
                System.out.println("Invalid choice!");
            }
        }
    }

    private static void registerUser() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter User Name: ");
        String userName = scanner.nextLine();
        System.out.print("Enter Books Count: ");
        int books = scanner.nextInt();
        System.out.print("Enter Borrowing Days: ");
        int days = scanner.nextInt();
        scanner.nextLine();
        
        User user = new User(userId, userName, books, days);
        library.registerUser(user);
        System.out.println("User registered!");
    }

    private static void issueBook() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();
        library.issueBook(userId, bookId);
    }

    private static void viewUserDetails() {
        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();
        for (User user : library.getUsers()) {
            if (user.getUserId().equals(userId)) {
                user.displayUserDetails();
                user.displayFineDetails();
            }
        }
    }
}
