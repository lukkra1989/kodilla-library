package com.crud.library.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.time.LocalDate;

@NamedNativeQuery(
        name = "BorrowedBook.getByCopyId",
        query = "SELECT * FROM Borrowed_books WHERE Copy_Id = :COPYID",
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
}
