package org.valentine.goldspoon.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import org.valentine.goldspoon.dto.ReaderDto;
import org.valentine.goldspoon.entity.Reader;
import org.valentine.goldspoon.exception.EmailAlreadyExists;
import org.valentine.goldspoon.exception.ReaderNotFound;
import org.valentine.goldspoon.repository.ReaderRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;

    public Reader getReader(Long readerId){
        return readerRepository
                .findById(readerId)
                .orElseThrow(() -> new ReaderNotFound("Reader with id " + readerId + " not found"));
    }

    public void addReader(Reader reader) {
        if(readerRepository.existsByEmail(reader.getEmail())) {
            throw new EmailAlreadyExists("Reader with email " + reader.getEmail() + " already exists");
        }

        readerRepository.save(reader);
    }

    public void deleteReader(Long readerId) {
        if(!readerRepository.existsById(readerId)) {
            throw new ReaderNotFound("Reader with id " + readerId + " not found");
        }
        readerRepository.deleteById(readerId);
    }

    @Transactional
    public void updateReader(Long readerId, ReaderDto reader) {
        if(!readerRepository.existsById(readerId)) {
            throw new ReaderNotFound("Reader with id " + readerId + " not found");
        }

        Reader foundReader = getReader(readerId);

        if(readerRepository.existsByEmail(reader.getEmail())) {
            throw new EmailAlreadyExists("Reader with email " + reader.getEmail() + " already exists");
        }
        if(reader.getFirstName() != null) {
            foundReader.setFirstName(reader.getFirstName());
        }
        if(reader.getLastName() != null) {
            foundReader.setLastName(reader.getLastName());
        }
        if(reader.getEmail() != null) {
            foundReader.setEmail(reader.getEmail());
        }
        if(reader.getPhoneNumber() != null) {
            foundReader.setPhoneNumber(reader.getPhoneNumber());
        }
        if(reader.getFavouriteGenre() != null) {
            foundReader.setFavouriteGenre(reader.getFavouriteGenre());
        }

        readerRepository.save(foundReader);
    }

    public List<Reader> getAllReaders(){
        List<Reader> foundReaders = (List<Reader>) readerRepository.findAll();

        if(foundReaders.isEmpty()) {
            throw new ReaderNotFound("No readers found");
        }

        return foundReaders;
    }

}
