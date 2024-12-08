package org.valentine.goldspoon.web;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.valentine.goldspoon.dto.BookDto;
import org.valentine.goldspoon.entity.Book;
import org.valentine.goldspoon.service.BookService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;
    private final ModelMapper modelMapper;

    @GetMapping("/{bookId}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long bookId) {
        BookDto bookDto = modelMapper.map(bookService.getBook(bookId), BookDto.class);
        return new ResponseEntity<>(bookDto, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<String> addBook(@RequestBody BookDto bookDto) {
        bookService.addBook(modelMapper.map(bookDto, Book.class));
        return new ResponseEntity<>("Book added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<String> updateBook(@PathVariable Long bookId, @RequestBody BookDto bookDto) {
        bookService.updateBook(bookId, bookDto);
        return new ResponseEntity<>("Book updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{bookId}")
    public ResponseEntity<String> deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
        return new ResponseEntity<>("Book deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks().stream().map(book -> modelMapper.map(book, BookDto.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getBookCount() {
        return new ResponseEntity<>(bookService.countBooks(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<List<BookDto>> findBooksByTitle(@RequestParam String title) {
        return new ResponseEntity<>(bookService.findByTitle(title).stream()
                .map(book -> modelMapper.map(book, BookDto.class)).toList(), HttpStatus.OK);
    }

    @GetMapping("/available")
    public ResponseEntity<List<BookDto>> getAvailableBooks() {
        return new ResponseEntity<>(bookService.getAvailableBooks().stream()
                .map(book -> modelMapper.map(book, BookDto.class)).toList(), HttpStatus.OK);
    }

    @PatchMapping("/{bookId}/borrow")
    public ResponseEntity<String> borrowBook(@PathVariable Long bookId) {
        bookService.borrowBook(bookId);
        return new ResponseEntity<>("Book borrowed successfully", HttpStatus.OK);
    }

    @PatchMapping("/{bookId}/return")
    public ResponseEntity<String> returnBook(@PathVariable Long bookId) {
        bookService.returnBook(bookId);
        return new ResponseEntity<>("Book returned successfully", HttpStatus.OK);
    }
}
