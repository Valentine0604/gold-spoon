package org.valentine.goldspoon.repository;

import org.springframework.data.repository.CrudRepository;
import org.valentine.goldspoon.entity.Reader;

public interface ReaderRepository extends CrudRepository<Reader, Long> {
    Boolean existsByEmail(String email);
}
