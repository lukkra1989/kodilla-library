package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedNativeQuery(
        name = "CopyInLibrary.findAllNotBorrowed",
        query = "SELECT * FROM Copies_in_library " +
                "WHERE Book_id = :bookId " +
                "AND Is_borrowed = false",
        resultClass = CopyInLibrary.class
)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Copies_in_library")
public class CopyInLibrary {

    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Database_Copy_id", unique = true)
    private Long databaseCopyId;

    @Column(name = "Copy_id")
    private Long copyId;

    @Column(name = "Status_of_book")
    private String status;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "Book_id")
    private Book book;

    @Column(name = "Is_borrowed")
    private boolean borrowed;

    @Column(name = "Is_copy_deleted")
    private boolean deleted;

    public CopyInLibrary(Long copyId, Book book, String status) {
        this.copyId = copyId;
        this.book = book;
        this.status = status;
    }

    private void setBook(Book book) {
        this.book = book;
    }
    public void setIsBorrowed(boolean isBorrowed) {
        this.borrowed = isBorrowed;
    }
    public void setIsDeleted(boolean isDeleted) {
        this.deleted = isDeleted;
    }
}
