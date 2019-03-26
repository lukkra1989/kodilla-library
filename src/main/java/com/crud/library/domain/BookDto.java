package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {

    private Long bookId;
    private String title;
    private String author;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;
    private boolean deleted;
    private List<CopyInLibrary> copiesInLibrary;

    public BookDto(String title, String author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }
}
