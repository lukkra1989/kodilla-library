package com.crud.library.repository;

import com.crud.library.domain.BorrowedBook;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowedBookRepository extends CrudRepository<BorrowedBook, Long> {

    @Override
    List<BorrowedBook> findAll();

    @Override
    BorrowedBook save(final BorrowedBook borrowedBook);

    @Query
    List<BorrowedBook> getByCopyId(@Param("COPYID") Long copyId);

    @Override
    void delete(final BorrowedBook borrowedBook);

}
