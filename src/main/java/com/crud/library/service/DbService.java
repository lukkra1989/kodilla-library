package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.BorrowedBook;
import com.crud.library.domain.CopyInLibrary;
import com.crud.library.domain.Reader;
import com.crud.library.objectNotFoundExceptions.BookNotFoundException;
import com.crud.library.objectNotFoundExceptions.BorrowDateNotFoundException;
import com.crud.library.objectNotFoundExceptions.CopyNotFoundException;
import com.crud.library.objectNotFoundExceptions.ReaderNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.BorrowedBookRepository;
import com.crud.library.repository.CopyInLibraryRepository;
import com.crud.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Service
public class DbService {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private CopyInLibraryRepository copyInLibraryRepository;
    @Autowired
    private ReaderRepository readerRepository;
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Reader> getAllReaders() {
        return readerRepository.findAll();
    }

    public Book getBookById(final Long bookId) throws BookNotFoundException {
        return bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
    }

    public List<CopyInLibrary> findAllNotBorrowed(Long bookId) {
        return copyInLibraryRepository.findAllNotBorrowed(bookId);
    }

    public Book saveBook(final Book book) {
        return bookRepository.save(book);
    }

    public BorrowedBook borrowBook(final Long bookId, final Long readerId, final LocalDate dateOfBorrow) throws BookNotFoundException, CopyNotFoundException, ReaderNotFoundException {

        CopyInLibrary copyToBorrow = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new).getCopiesInLibrary().stream()
                .filter(copy -> !copy.isBorrowed())
                .findFirst().orElseThrow(CopyNotFoundException::new);
        copyToBorrow.setIsBorrowed(true);

        return borrowedBookRepository.save(new BorrowedBook(copyToBorrow, readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new), dateOfBorrow));
    }

    public BorrowedBook returnBook(final Long copyId, final LocalDate dateOfReturn) throws CopyNotFoundException, ReaderNotFoundException, BorrowDateNotFoundException {

        List<BorrowedBook> borrowedBooks = borrowedBookRepository.getByCopyId(copyId);
        BorrowedBook borrowedBook = borrowedBooks.get(0);
        borrowedBook.getCopyInLibrary().setIsBorrowed(false);
        borrowedBook.setReturnDate(dateOfReturn);
        return borrowedBookRepository.save(borrowedBook);
    }

    public Reader createReader(final Reader reader) {
        return readerRepository.save(reader);
    }

    public CopyInLibrary saveBookCopy(final Long bookId, String status) throws BookNotFoundException {
        return copyInLibraryRepository.save(new CopyInLibrary(bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new), status));
    }

    public void deleteBook(final Long bookId) throws BookNotFoundException {
        bookRepository.deleteByBoolean(bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new));
    }

    public void deleteBookCopy(final Long bookCopyId) throws CopyNotFoundException {
        copyInLibraryRepository.deleteByBoolean(copyInLibraryRepository.findByCopyId(bookCopyId).orElseThrow(CopyNotFoundException::new));
    }

}
