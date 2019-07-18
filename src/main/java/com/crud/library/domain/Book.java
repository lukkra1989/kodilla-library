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
    fetch = FetchType.EAGER
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookId != null ? !bookId.equals(book.bookId) : book.bookId != null) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (author != null ? !author.equals(book.author) : book.author != null) return false;
        return publicationDate != null ? publicationDate.equals(book.publicationDate) : book.publicationDate == null;
    }

    @Override
    public int hashCode() {
        int result = bookId != null ? bookId.hashCode() : 0;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (publicationDate != null ? publicationDate.hashCode() : 0);
        return result;
    }
}
