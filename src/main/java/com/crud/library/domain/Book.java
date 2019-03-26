package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "Is_deleted")
    private boolean deleted;

    @JsonManagedReference
    @OneToMany(
    targetEntity = CopyInLibrary.class,
    mappedBy = "book",
    cascade = CascadeType.ALL,
    fetch = FetchType.LAZY
    )
    private List<CopyInLibrary> copiesInLibrary = new ArrayList<>();

    public Book(String title, String author, LocalDate publicationDate) {
        this.title = title;
        this.author = author;
        this.publicationDate = publicationDate;
    }

    private void setCopiesInLibrary(List<CopyInLibrary> copiesInLibrary) {
        this.copiesInLibrary = copiesInLibrary;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
    }
}
