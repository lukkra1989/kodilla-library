package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReaderMapperTestSuite {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    public void mapToReaderDtoListTest() {
        //Given
        List<Reader> readerList = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            readerList.add(new Reader((long) i, "test_name" + i, "test_surname" + i, LocalDate.of(2019, 1, i)));
        }

        //When
        List<ReaderDto> readerDtoListMapped = readerMapper.mapToReaderDtoList(readerList);

        //Then
        assertEquals(10, readerDtoListMapped.size());
        assertEquals(5L, readerDtoListMapped.get(4).getId().longValue());
        assertEquals("test_name7", readerDtoListMapped.get(6).getFirstname());
        assertEquals("test_surname4", readerDtoListMapped.get(3).getLastname());
    }

    @Test
    public void mapToReaderDtoTest() {
        //Given
        ReaderDto readerDto = new ReaderDto("test_firstname", "test_lastname", LocalDate.of(2019, 1, 1));

        //When
        Reader readerMapped = readerMapper.mapToReader(readerDto);


        //Then
        assertNotNull(readerMapped);
        assertEquals("test_firstname", readerMapped.getFirstname());
        assertEquals("test_lastname", readerMapped.getLastname());
        assertEquals(LocalDate.of(2019, 1, 1), readerMapped.getRegisterDate());
    }
}