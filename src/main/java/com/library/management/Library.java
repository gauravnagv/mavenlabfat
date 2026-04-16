package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String libraryName;
    private List<Book> books;
    private List<User> users;

    public Library(String libraryName) {
        this.libraryName = libraryName;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public String getLibraryName() {
        return libraryName;
    }

    public List<Book> getBooks() {
        return books;
    }

    public List<User> getUsers() {
        return users;
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("\n✓ User '" + user.getUserName() + "' registered successfully!");
    }

    public void issueBook(String userId, String bookId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("✗ User not found!");
            return;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("✗ Book not found!");
            return;
        }

        if (book.isIssued()) {
            System.out.println("✗ Book is already issued!");
            return;
        }

        user.addIssuedBook(book);
        System.out.println("✓ Book '" + book.getBookTitle() + "' issued to '" + user.getUserName() + "'");
    }

    public void returnBook(String userId, String bookId) {
        User user = findUserById(userId);
        if (user == null) {
            System.out.println("✗ User not found!");
            return;
        }

        Book book = findBookById(bookId);
        if (book == null) {
            System.out.println("✗ Book not found!");
            return;
        }

        user.removeIssuedBook(book);
        System.out.println("✓ Book '" + book.getBookTitle() + "' returned by '" + user.getUserName() + "'");
    }

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    private Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }

    public void displayAvailableBooks() {
        System.out.println("\n========================================");
        System.out.println("          AVAILABLE BOOKS              ");
        System.out.println("========================================");
        boolean found = false;
        for (Book book : books) {
            if (!book.isIssued()) {
                System.out.println("• " + book.getBookTitle() + " by " + book.getAuthor());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books");
        }
        System.out.println("========================================\n");
    }
}
