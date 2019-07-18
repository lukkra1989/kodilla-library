package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.domain.ReaderDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ReaderMapper {

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readers) {
        return readers.stream()
                .map(reader -> new ReaderDto(reader.getId(), reader.getFirstname(), reader.getLastname(), reader.getRegisterDate()))
                .collect(Collectors.toList());
    }

    public Reader mapToReader(ReaderDto readerDto) {
        return new Reader(readerDto.getFirstname(), readerDto.getLastname(), readerDto.getRegisterDate());
    }
}
