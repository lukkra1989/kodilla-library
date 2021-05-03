package com.crud.library.mapper;

import com.crud.library.domain.BorrowedBook;
import com.crud.library.domain.BorrowedBookDto;
import org.springframework.stereotype.Component;

@Component
public class
BorrowBookMapper {

    public BorrowedBookDto mapToBorrowBookDto(BorrowedBook borrowedBook) {
        return new BorrowedBookDto(borrowedBook.getBorrowId(),
                borrowedBook.getCopyInLibrary(),
                borrowedBook.getReader(),
                borrowedBook.getBorrowDate(),
                borrowedBook.getReturnDate());
    }
}
