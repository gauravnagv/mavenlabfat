package com.library.management;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LibraryManagementSystemTest {
    
    private Library library;
    private User user;
    private Book book;

    @Before
    public void setUp() {
        library = new Library("Test Library");
        user = new User("U001", "John Doe", 3, 20);
        book = new Book("B001", "Java Book", "Author");
    }

    @Test
    public void testUserCreation() {
        assertEquals("U001", user.getUserId());
        assertEquals("John Doe", user.getUserName());
        assertEquals(3, user.getBooksIssued());
        assertEquals(20, user.getBorrowingDays());
    }

    @Test
    public void testBookCreation() {
        assertEquals("B001", book.getBookId());
        assertEquals("Java Book", book.getBookName());
        assertEquals("Author", book.getAuthor());
        assertFalse(book.isIssued());
    }

    @Test
    public void testFineCalculation() {
        user.calculateFine();
        double expectedFine = (20 - 14) * 5.0;
        assertEquals(expectedFine, user.getTotalFine(), 0.01);
    }

    @Test
    public void testNoFineWithinLimit() {
        User user2 = new User("U002", "Jane", 2, 10);
        user2.calculateFine();
        assertEquals(0.0, user2.getTotalFine(), 0.01);
    }

    @Test
    public void testAddBook() {
        library.addBook(book);
        assertTrue(library.getBooks().contains(book));
    }

    @Test
    public void testRegisterUser() {
        library.registerUser(user);
        assertTrue(library.getUsers().contains(user));
    }

    @Test
    public void testIssueBook() {
        library.addBook(book);
        library.registerUser(user);
        assertTrue(library.issueBook("U001", "B001"));
    }

    @Test
    public void testBorrowingStatusOverdue() {
        String status = user.getBorrowingStatus();
        assertTrue(status.contains("Overdue"));
    }

    @Test
    public void testBorrowingStatusWithinLimit() {
        User user2 = new User("U002", "Jane", 2, 10);
        assertEquals("Within allowed borrowing period", user2.getBorrowingStatus());
    }
}
