package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NamedNativeQuery(
        name = "BorrowedBook.getByCopyId",
        query = "SELECT * FROM Borrowed_books WHERE Copy_Id = :COPYID " +
                "ORDER BY borrow_id DESC",
        resultClass = BorrowedBook.class

)

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity(name = "Borrowed_books")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull
    @Column(name = "Borrow_id")
    private Long borrowId;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Copy_id")
    private CopyInLibrary copyInLibrary;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "Reader_id")
    private Reader reader;

    @Column(name = "Borrow_date")
    private LocalDate borrowDate;

    @Column(name = "Return_date")
    private LocalDate returnDate;

    public BorrowedBook(CopyInLibrary copyInLibrary, Reader reader, LocalDate borrowDate) {
        this.copyInLibrary = copyInLibrary;
        this.reader = reader;
        this.borrowDate = borrowDate;
    }

    public BorrowedBook(CopyInLibrary copyInLibrary, Reader reader, LocalDate borrowDate, LocalDate returnDate) {
        this.copyInLibrary = copyInLibrary;
        this.reader = reader;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BorrowedBook that = (BorrowedBook) o;

        if (!borrowId.equals(that.borrowId)) return false;
        if (!copyInLibrary.equals(that.copyInLibrary)) return false;
        if (!reader.equals(that.reader)) return false;
        if (borrowDate != null ? !borrowDate.equals(that.borrowDate) : that.borrowDate != null) return false;
        return returnDate != null ? returnDate.equals(that.returnDate) : that.returnDate == null;
    }

    @Override
    public int hashCode() {
        int result = borrowId.hashCode();
        result = 31 * result + copyInLibrary.hashCode();
        result = 31 * result + reader.hashCode();
        result = 31 * result + (borrowDate != null ? borrowDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }
}
