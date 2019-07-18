package com.crud.library.mapper;

import com.crud.library.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BorrowBookMapperTestSuite {

    @Autowired
    private BorrowBookMapper borrowBookMapper;

    @Test
    public void mapToBorrowBookDtoTest() {
        //Given
        Reader reader = new Reader(1L, "test_name", "test_surname", LocalDate.of(2019,1,1));
        Book book = new Book(1L, "Test_title", "Test_author", LocalDate.of(2019,1,1), false, new ArrayList<>());
        CopyInLibrary copyInLibrary = new CopyInLibrary(book, "test_status");
        BorrowedBook borrowBook = new BorrowedBook(1L,copyInLibrary, reader, LocalDate.of(2019,6,1), LocalDate.of(2019,7,1));

        //When
        BorrowedBookDto borrowBookDtoMapped = borrowBookMapper.mapToBorrowBookDto(borrowBook);

        //Then
        assertNotNull(borrowBookDtoMapped);
        assertEquals(1L, borrowBookDtoMapped.getBorrowId().longValue());
        assertEquals(reader, borrowBookDtoMapped.getReader());
        assertEquals(copyInLibrary, borrowBookDtoMapped.getCopyInLibrary());
        assertEquals(LocalDate.of(2019,6,1), borrowBookDtoMapped.getBorrowDate());
        assertEquals(LocalDate.of(2019,7,1), borrowBookDtoMapped.getReturnDate());
    }
}