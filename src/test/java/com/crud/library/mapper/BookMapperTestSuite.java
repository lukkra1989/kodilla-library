package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.BookDto;
import com.crud.library.domain.CopyInLibrary;
import com.crud.library.domain.CopyInLibraryDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void mapToBookTest() {
        //Given
        BookDto bookDto = new BookDto(1L, "Test_title", "Test_author", LocalDate.of(2019,1,1), false, new ArrayList<>());

        //When
        Book bookMapped = bookMapper.mapToBook(bookDto);

        //Then
        assertNotNull(bookMapped);
        assertEquals("Test_title", bookMapped.getTitle());
        assertEquals("Test_author", bookMapped.getAuthor());
        assertEquals(LocalDate.of(2019,1,1), bookMapped.getPublicationDate());

    }

    @Test
    public void mapToBookDtoTest() {
        //Given
        Book book = new Book(1L, "Test_title", "Test_author", LocalDate.of(2019,1,1), false, new ArrayList<>());

        //When
        BookDto bookMapped = bookMapper.mapToBookDto(book);

        //Then
        assertNotNull(bookMapped);
        assertEquals("Test_title", bookMapped.getTitle());
        assertEquals("Test_author", bookMapped.getAuthor());
        assertEquals(1L, bookMapped.getBookId().longValue());
        assertEquals(LocalDate.of(2019,1,1), bookMapped.getPublicationDate());
        assertFalse(bookMapped.isDeleted());
        assertFalse(bookMapped.isDeleted());
    }

    @Test
    public void mapToBookDtoListTest() {
        //Given
        List<Book> books = new ArrayList<>();
        books.add(new Book(1L, "Test_title", "Test_author", LocalDate.of(2019,1,1), false, new ArrayList<>()));
        books.add(new Book(2L, "Test_title2", "Test_author2", LocalDate.of(2019,2,2), false, new ArrayList<>()));

        //When
        List<BookDto> booksMapped = bookMapper.mapToBookDtoList(books);

        //Then
        assertEquals("Test_title2", booksMapped.get(1).getTitle());
        assertEquals("Test_author", booksMapped.get(0).getAuthor());
        assertEquals(LocalDate.of(2019,2,2), booksMapped.get(1).getPublicationDate());
    }
}