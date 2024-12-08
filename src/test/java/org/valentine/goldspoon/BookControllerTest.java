package org.valentine.goldspoon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.valentine.goldspoon.dto.BookDto;
import org.valentine.goldspoon.entity.Book;
import org.valentine.goldspoon.service.BookService;
import org.valentine.goldspoon.web.BookController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @InjectMocks
    private BookController bookController;

    @Mock
    private BookService bookService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testGetBook() {
        Book book = new Book();
        book.setBookId(1L);
        book.setTitle("Java Basics");

        when(bookService.getBook(1L)).thenReturn(book);
        when(modelMapper.map(book, BookDto.class)).thenReturn(new BookDto());

        ResponseEntity<BookDto> response = bookController.getBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).getBook(1L);
    }

    @Test
    void testAddBook() {
        BookDto bookDto = BookDto.builder().title("Java Basics").build();

        ResponseEntity<String> response = bookController.addBook(bookDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Book added successfully", response.getBody());
    }

    @Test
    void testUpdateBook() {
        BookDto bookDto = BookDto.builder().title("Updated Title").build();

        doNothing().when(bookService).updateBook(eq(1L), any(BookDto.class));

        ResponseEntity<String> response = bookController.updateBook(1L, bookDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book updated successfully", response.getBody());
        verify(bookService, times(1)).updateBook(eq(1L), any(BookDto.class));
    }

    @Test
    void testDeleteBook() {
        doNothing().when(bookService).deleteBook(1L);

        ResponseEntity<String> response = bookController.deleteBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book deleted successfully", response.getBody());
        verify(bookService, times(1)).deleteBook(1L);
    }

    @Test
    void testGetAllBooks() {
        when(bookService.getAllBooks()).thenReturn(List.of(new Book()));

        ResponseEntity<List<BookDto>> response = bookController.getAllBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).getAllBooks();
    }

    @Test
    void testGetBookCount() {
        when(bookService.countBooks()).thenReturn(5L);

        ResponseEntity<Long> response = bookController.getBookCount();

        assertEquals(5L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).countBooks();
    }

    @Test
    void testFindBooksByTitle() {
        when(bookService.findByTitle("Java")).thenReturn(List.of(new Book()));

        ResponseEntity<List<BookDto>> response = bookController.findBooksByTitle("Java");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).findByTitle("Java");
    }

    @Test
    void testGetAvailableBooks() {
        when(bookService.getAvailableBooks()).thenReturn(List.of(new Book()));

        ResponseEntity<List<BookDto>> response = bookController.getAvailableBooks();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(bookService, times(1)).getAvailableBooks();
    }

    @Test
    void testBorrowBook() {
        doNothing().when(bookService).borrowBook(1L);

        ResponseEntity<String> response = bookController.borrowBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book borrowed successfully", response.getBody());
        verify(bookService, times(1)).borrowBook(1L);
    }

    @Test
    void testReturnBook() {
        doNothing().when(bookService).returnBook(1L);

        ResponseEntity<String> response = bookController.returnBook(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Book returned successfully", response.getBody());
        verify(bookService, times(1)).returnBook(1L);
    }
}
