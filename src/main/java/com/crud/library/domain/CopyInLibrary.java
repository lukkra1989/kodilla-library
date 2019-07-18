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

    public CopyInLibrary(Book book, String status) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CopyInLibrary that = (CopyInLibrary) o;

        if (copyId != null ? !copyId.equals(that.copyId) : that.copyId != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        return book != null ? book.equals(that.book) : that.book == null;
    }

    @Override
    public int hashCode() {
        int result = copyId != null ? copyId.hashCode() : 0;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (book != null ? book.hashCode() : 0);
        return result;
    }
}
