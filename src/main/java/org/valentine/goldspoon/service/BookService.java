package org.valentine.goldspoon.service;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.valentine.goldspoon.dto.BookDto;
import org.valentine.goldspoon.entity.Book;
import org.valentine.goldspoon.exception.BookAlreadyExists;
import org.valentine.goldspoon.exception.BookNotFound;
import org.valentine.goldspoon.repository.BookRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public Book getBook(Long bookId) {
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFound("Book not found"));
    }

    public void addBook(Book book) {
        if(bookRepository.existsByTitle(book.getTitle())) {
            throw new BookAlreadyExists("Book with title " + book.getTitle() + " already exists");
        }

        bookRepository.save(book);
    }

    public void deleteBook(Long bookId) {
        if(!bookRepository.existsById(bookId)) {
            throw new BookNotFound("Book not found");
        }

        bookRepository.deleteById(bookId);
    }

    @Transactional
    public void updateBook(Long bookId, BookDto book) {
        if(!bookRepository.existsById(bookId)) {
            throw new BookNotFound("Book not found");
        }

        Book foundBook = getBook(bookId);

        if(book.getTitle() != null) {
            foundBook.setTitle(book.getTitle());
        }

        if(book.getAuthor() != null) {
            foundBook.setAuthor(book.getAuthor());
        }

        if(book.getPagesNumber() != foundBook.getPagesNumber()) {
            foundBook.setPagesNumber(book.getPagesNumber());
        }

        if(book.getPublisher() != null) {
            foundBook.setPublisher(book.getPublisher());
        }

        bookRepository.save(foundBook);
    }

    public List<Book> getAllBooks() {
        List<Book> foundBooks = (List<Book>) bookRepository.findAll();

        if(foundBooks.isEmpty()) {
            throw new BookNotFound("No books found");
        }

        return foundBooks;
    }

    public long countBooks() {
        return bookRepository.count();
    }

    public List<Book> findByTitle(String title) {
        return bookRepository.findByTitleContaining(title);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }

    public void borrowBook(Long id) {
        Book book = getBook(id);
        if (!book.isAvailable()) {
            throw new RuntimeException("Book is not available");
        }
        book.setAvailable(false);
        bookRepository.save(book);
    }

    public void returnBook(Long id) {
        Book book = getBook(id);
        book.setAvailable(true);
        bookRepository.save(book);
    }
}
