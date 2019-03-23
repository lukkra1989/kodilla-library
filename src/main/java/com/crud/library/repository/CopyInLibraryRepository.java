package com.crud.library.repository;

import com.crud.library.domain.CopyInLibrary;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CopyInLibraryRepository extends CrudRepository<CopyInLibrary, Long> {

    List<CopyInLibrary> findAll();

    Optional<CopyInLibrary> findByCopyId(Long id);

    CopyInLibrary save(CopyInLibrary copyInLibrary);

    @Override
    default void delete(CopyInLibrary copyInLibrary) {
        copyInLibrary.setIsDeleted(true);
    }

    List<CopyInLibrary> findAllNotBorrowed(@Param("bookId") Long bookId);

}
