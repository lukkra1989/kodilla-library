package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BorrowedBookDto {

    private Long borrowId;
    private CopyInLibrary copyInLibrary;
    private Reader reader;
    private LocalDate borrowDate;
    private LocalDate returnDate;
}
