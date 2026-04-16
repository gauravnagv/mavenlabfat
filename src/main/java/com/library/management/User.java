package com.library.management;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String userId;
    private String userName;
    private int booksCount;
    private int borrowingDays;
    private List<Book> issuedBooks;
    private double totalFine;
    private static final int ALLOWED_DAYS = 14;
    private static final double FINE_PER_DAY = 5.0;

    public User(String userId, String userName, int booksCount, int borrowingDays) {
        this.userId = userId;
        this.userName = userName;
        this.booksCount = booksCount;
        this.borrowingDays = borrowingDays;
        this.issuedBooks = new ArrayList<>();
        this.totalFine = 0.0;
        calculateFine();
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public int getBooksCount() {
        return booksCount;
    }

    public int getBorrowingDays() {
        return borrowingDays;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public double getTotalFine() {
        return totalFine;
    }

    public void addIssuedBook(Book book) {
        if (!book.isIssued()) {
            issuedBooks.add(book);
            book.setIssued(true);
        }
    }

    public void removeIssuedBook(Book book) {
        if (issuedBooks.contains(book)) {
            issuedBooks.remove(book);
            book.setIssued(false);
        }
    }

    private void calculateFine() {
        if (borrowingDays > ALLOWED_DAYS) {
            int extraDays = borrowingDays - ALLOWED_DAYS;
            totalFine = extraDays * FINE_PER_DAY;
        } else {
            totalFine = 0.0;
        }
    }

    public void displayUserDetails() {
        System.out.println("\n========================================");
        System.out.println("             USER DETAILS              ");
        System.out.println("========================================");
        System.out.println("User ID          : " + userId);
        System.out.println("User Name        : " + userName);
        System.out.println("Books Count      : " + booksCount);
        System.out.println("Borrowing Days   : " + borrowingDays);
        System.out.println("Books Issued     : " + issuedBooks.size());
        System.out.println("========================================");
    }

    public void displayIssuedBooks() {
        System.out.println("\n========================================");
        System.out.println("          LIST OF BOOKS ISSUED          ");
        System.out.println("========================================");
        if (issuedBooks.isEmpty()) {
            System.out.println("No books issued to this user");
        } else {
            for (int i = 0; i < issuedBooks.size(); i++) {
                Book book = issuedBooks.get(i);
                System.out.println((i + 1) + ". " + book.getBookTitle() + " by " + book.getAuthor());
            }
        }
        System.out.println("========================================");
    }

    public void displayFineDetails() {
        System.out.println("\n========================================");
        System.out.println("             FINE DETAILS              ");
        System.out.println("========================================");
        System.out.println("Allowed Days     : " + ALLOWED_DAYS);
        System.out.println("Fine Per Day     : Rs. " + FINE_PER_DAY);
        if (borrowingDays > ALLOWED_DAYS) {
            int extraDays = borrowingDays - ALLOWED_DAYS;
            System.out.println("Extra Days       : " + extraDays);
            System.out.println("TOTAL FINE       : Rs. " + String.format("%.2f", totalFine));
        } else {
            System.out.println("TOTAL FINE       : Rs. 0.00 (No Late Charges)");
        }
        System.out.println("========================================\n");
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", booksCount=" + booksCount +
                ", borrowingDays=" + borrowingDays +
                ", issuedBooks=" + issuedBooks.size() +
                ", totalFine=" + totalFine +
                '}';
    }
}
