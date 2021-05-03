package com.crud.library.controller;

import com.crud.library.domain.*;
import com.crud.library.mapper.BookMapper;
import com.crud.library.mapper.BorrowBookMapper;
import com.crud.library.mapper.CopyInLibraryMapper;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.objectNotFoundExceptions.BookNotFoundException;
import com.crud.library.objectNotFoundExceptions.BorrowDateNotFoundException;
import com.crud.library.objectNotFoundExceptions.CopyNotFoundException;
import com.crud.library.objectNotFoundExceptions.ReaderNotFoundException;
import com.crud.library.service.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/v1/library")
public class LibraryController {

    @Autowired
    private DbService dbService;
    @Autowired
    private BookMapper bookMapper;
    @Autowired
    private ReaderMapper readerMapper;
    @Autowired
    private CopyInLibraryMapper copyInLibraryMapper;
    @Autowired
    private BorrowBookMapper borrowedBookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "getBooks")
    public List<BookDto> getBooks() {
        return bookMapper.mapToBookDtoList(dbService.getAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getBook")
    public BookDto getBook(@RequestParam Long bookId) throws BookNotFoundException {
        return bookMapper.mapToBookDto(dbService.getBookById(bookId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "getReaders")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(dbService.getAllReaders());
    }

    @RequestMapping(method = RequestMethod.GET, value = "getQuantityOfBookCopies")
    public Integer getQuantityOfBookCopies(@RequestParam Long bookId) throws BookNotFoundException {
        return dbService.getBookById(bookId).getCopiesInLibrary().size();
    }

    @RequestMapping(method = RequestMethod.GET, value = "getNotBorrowedCopies", consumes = APPLICATION_JSON_VALUE)
    public Integer getNotBorrowedCopies(@RequestParam Long bookId) {
        return dbService.findAllNotBorrowed(bookId).size();
    }

    @RequestMapping(method = RequestMethod.POST, value = "addReader", consumes = APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto reader) {
        dbService.createReader(readerMapper.mapToReader(reader));
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBookCopy", consumes = APPLICATION_JSON_VALUE)
    public void addBookCopy(@RequestBody CopyInLibraryDto copyInLibraryDto) throws BookNotFoundException {
        dbService.saveBookCopy(copyInLibraryDto.getBookId(), copyInLibraryDto.getStatus());
    }

    @RequestMapping(method = RequestMethod.POST, value = "addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) {
        dbService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "borrowBook", consumes = APPLICATION_JSON_VALUE)
    public void borrowBook(@RequestBody BorrowBookDto borrowBookDto) throws BookNotFoundException, ReaderNotFoundException, CopyNotFoundException {
        dbService.borrowBook(borrowBookDto.getBookId(), borrowBookDto.getReaderId(), borrowBookDto.getBorrowDate());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "updateCopyStatus", consumes = APPLICATION_JSON_VALUE)
    public CopyInLibraryDto updateCopyStatus(@RequestBody CopyInLibraryDto copyInLibraryDto) throws BookNotFoundException, CopyNotFoundException {
        return copyInLibraryMapper.mapToCopyInLibraryDto(dbService.saveBookCopy(copyInLibraryDto.getBookId(), copyInLibraryDto.getStatus()));
    }

    @RequestMapping(method = RequestMethod.PUT, value = "returnBook", consumes = APPLICATION_JSON_VALUE)
    public BorrowedBookDto returnBook(@RequestBody ReturnBookDto returnBookDto) throws CopyNotFoundException, ReaderNotFoundException, BorrowDateNotFoundException {
        return borrowedBookMapper.mapToBorrowBookDto(dbService.returnBook(returnBookDto.getCopyId(), returnBookDto.getReturnDate()));
    }


    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBook")
    public void deleteBook(@RequestParam Long bookId) throws BookNotFoundException {
        dbService.deleteBook(bookId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "deleteBookCopy")
    public void deleteBookCopy(@RequestParam Long bookCopyId) throws CopyNotFoundException {
        dbService.deleteBookCopy(bookCopyId);
    }
}
