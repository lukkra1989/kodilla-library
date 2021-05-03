package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReturnBookDto {

    private Long copyId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
}

