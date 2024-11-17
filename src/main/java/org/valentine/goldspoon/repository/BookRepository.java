package org.valentine.goldspoon.repository;

import org.springframework.data.repository.CrudRepository;
import org.valentine.goldspoon.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
    Boolean existsByTitle(String title);
}
