package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.CopyInLibrary;
import com.crud.library.domain.CopyInLibraryDto;
import com.crud.library.objectNotFoundExceptions.CopyNotFoundException;
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
public class CopyInLibraryMapperTestSuite {

    @Autowired
    private CopyInLibraryMapper copyInLibraryMapper;

    @Test
    public void mapToCopyInLibraryDto() {
        //Given
        Book book = new Book(1L, "Test_title", "Test_author", LocalDate.of(2019,1,1), false, new ArrayList<>());
        CopyInLibrary copyInLibrary = new CopyInLibrary(book, "test_status");

        try {
            //When
            CopyInLibraryDto copyInLibraryDtoMapped = copyInLibraryMapper.mapToCopyInLibraryDto(copyInLibrary);

            //Then
            assertNotNull(copyInLibraryDtoMapped);
            assertEquals(1L ,copyInLibraryDtoMapped.getBookId().longValue());
            assertEquals("test_status",copyInLibraryDtoMapped.getStatus());
        } catch (CopyNotFoundException e) {
            //Do nothing
        }
    }
}