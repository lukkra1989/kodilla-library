package com.crud.library.service;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowBookMapper;
import com.crud.library.mapper.CopyInLibraryMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.objectNotFoundExceptions.BookNotFoundException;
import com.crud.library.objectNotFoundExceptions.BorrowDateNotFoundException;
import com.crud.library.objectNotFoundExceptions.CopyNotFoundException;
import com.crud.library.objectNotFoundExceptions.ReaderNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DbServiceTestSuite {

    @Autowired
    private DbService dbService;

    @Test
    public void saveGetBookTest() {
//        Given
        Book book = new Book("Czerwone gardło", "Jo Nesbo", LocalDate.of(2019, 1, 3));

//        When
        dbService.saveBook(book);
        Long id = book.getBookId();

//        Then
        try {
            assertEquals(book.getBookId(), dbService.getBookById(id).getBookId());
        } catch (BookNotFoundException e) {
//            Do nothing
        }
    }

    @Test
    public void deleteBookTest() {

        Book book = new Book("Czerwone gardło", "Jo Nesbo", LocalDate.of(2019, 1, 3));
//        When
        dbService.saveBook(book);
        Long id = book.getBookId();

        try {
            dbService.deleteBook(id);

            assertTrue(dbService.getBookById(id).isDeleted());
        }catch(BookNotFoundException e) {
//            Do nothing
        }
    }

    @Test
    public void saveGetBookCopyTest() {
//        Given
        Book book = new Book("Stary człowiek i morze", "Ernest Hemingway", LocalDate.of(1988,5,3));
        dbService.saveBook(book);
        CopyInLibraryDto copyInLibrary = new CopyInLibraryDto(book.getBookId(), "zagubiona");

//        When/Then
        try {
            dbService.saveBookCopy(copyInLibrary.getBookId(), copyInLibrary.getStatus());
            CopyInLibrary copyCreated = dbService.getBookById(book.getBookId()).getCopiesInLibrary().get(0);

            assertEquals(book.getBookId(), copyCreated.getBook().getBookId());
        }catch(BookNotFoundException e) {
//            Do nothing
        }

    }

    @Test
    public void deleteCopyTest() {
//        Given
        Book book = new Book("Stary człowiek i morze", "Ernest Hemingway", LocalDate.of(1988,5,3));
        dbService.saveBook(book);
        CopyInLibraryDto copyInLibrary = new CopyInLibraryDto(book.getBookId(), "zagubiona");

//        When/then
        try {
            dbService.saveBookCopy(copyInLibrary.getBookId(), copyInLibrary.getStatus());
            Long copyId = dbService.getBookById(book.getBookId()).getCopiesInLibrary().get(0).getCopyId();
            dbService.deleteBookCopy(copyId);

            assertTrue(dbService.getBookById(book.getBookId()).getCopiesInLibrary().get(0).isDeleted());
        }catch(Exception e) {
//            Do nothing
        }

    }

    @Test
    public void addReaderTest() {
//        Given
        Reader reader = new Reader("Daniel", "Koplenski", LocalDate.now());
        int readerEntityStartSize = dbService.getAllReaders().size();

//        When
        dbService.createReader(reader);
        Long readerId = reader.getId();

//        Then
        assertEquals(readerId, dbService.getAllReaders().get(readerEntityStartSize).getId());
    }

    @Test
    public void borrowBookTest() {
//        Given
        Book book = new Book("Stary człowiek i morze", "Ernest Hemingway", LocalDate.of(1988,5,3));
        Reader reader = new Reader("Daniel", "Koplenski", LocalDate.now());

//        When/then
        try {
            dbService.saveBook(book);
            dbService.createReader(reader);
            dbService.saveBookCopy(book.getBookId(), "nowa");
            dbService.saveBookCopy(book.getBookId(), "uzywana");
            assertEquals(2, dbService.findAllNotBorrowed(book.getBookId()).size());
            dbService.borrowBook(book.getBookId(), reader.getId(), LocalDate.now());
            assertEquals(1, dbService.findAllNotBorrowed(book.getBookId()).size());
            }catch(Exception e) {}
    }

    @Test
    public void returnBookTest() {
//        Given
        Book book = new Book("Test_book", "Test_author", LocalDate.of(2019,1,1));
        Reader reader = new Reader("Daniel", "Koplenski", LocalDate.now());

        try {
            dbService.saveBook(book);
            CopyInLibrary copyInLibrary = dbService.saveBookCopy(book.getBookId(), "ok");
            dbService.createReader(reader);
            dbService.borrowBook(book.getBookId(), reader.getId(), LocalDate.of(2019,1,1));
//            When
            BorrowedBook borrowedBook = dbService.returnBook(copyInLibrary.getCopyId(), LocalDate.of(2019,2,2));
//            Then
            assertEquals(LocalDate.of(2019,1,1) ,borrowedBook.getBorrowDate());
            assertEquals(LocalDate.of(2019,2,2) ,borrowedBook.getReturnDate());
        } catch (CopyNotFoundException | BookNotFoundException | ReaderNotFoundException | BorrowDateNotFoundException e) {
//            Do nothing
        }
    }
}