package com.crud.library.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "Book_id", unique = true)
    private Long bookId;

    @Column(name = "Titles")
    private String title;

    @Column(name = "Auhors")
    private String author;

    @Column(name = "Publication_dates")
    private LocalDate publicationDate;

}
