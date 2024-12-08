package org.valentine.goldspoon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.valentine.goldspoon.entity.Reader;

import java.util.List;

public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Boolean existsByEmail(String email);
    List<Reader> findByLastName(String lastName);
    List<Reader> findByActiveTrue();
}
