package com.crud.library.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

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

    @Column(name = "Borrow_date")
    private LocalDate borrowDate;

    @Column(name = "Return_date")
    private LocalDate returnDate;


}
