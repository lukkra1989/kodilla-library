package com.crud.library.repository;

import com.crud.library.domain.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends CrudRepository<Book, Long> {

    @Override
    List<Book> findAll();

    Optional<Book> findByBookId(Long taskId);

    @Override
    Book save(Book book);

    default void deleteByBoolean(Book book) {
        book.setIsDeleted(true);
    }



    @Override
    void delete(final Book book);
}
