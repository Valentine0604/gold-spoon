package org.valentine.goldspoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.valentine.goldspoon.entity.Book;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    Boolean existsByTitle(String title);
    List<Book> findByTitleContaining(String title);
    List<Book> findByAvailableTrue();
}
