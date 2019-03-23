package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReaderDto {

    private Long id;
    private String firstname;
    private String lastname;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

    public ReaderDto(String firstname, String lastname, LocalDate registerDate) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.registerDate = registerDate;
    }
}
