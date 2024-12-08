package org.valentine.goldspoon;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.valentine.goldspoon.dto.ReaderDto;
import org.valentine.goldspoon.entity.Reader;
import org.valentine.goldspoon.service.ReaderService;
import org.valentine.goldspoon.web.ReaderController;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReaderControllerTest {

    @InjectMocks
    private ReaderController readerController;

    @Mock
    private ReaderService readerService;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void testGetReader() {
        Reader reader = new Reader();
        reader.setReaderId(1L);
        reader.setFirstName("John");
        reader.setLastName("Doe");
        reader.setEmail("x5DyS@example.com");
        reader.setPhoneNumber("1234567890");
        reader.setFavouriteGenre("Action");

        when(readerService.getReader(1L)).thenReturn(reader);
        when(modelMapper.map(reader, ReaderDto.class)).thenReturn(new ReaderDto());

        ResponseEntity<ReaderDto> response = readerController.getReader(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(readerService, times(1)).getReader(1L);
    }

    @Test
    void testAddReader() {
        ReaderDto readerDto = ReaderDto.builder()
                .firstName("John")
                .lastName("Doe")
                .email("x5DyS@example.com")
                .build();

        ResponseEntity<String> response = readerController.addReader(readerDto);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Reader added successfully", response.getBody());
    }

    @Test
    void testUpdateReader() {
        ReaderDto readerDto = ReaderDto.builder()
                .firstName("Jane")
                .lastName("Doe")
                .build();

        doNothing().when(readerService).updateReader(eq(1L), any(ReaderDto.class));

        ResponseEntity<String> response = readerController.updateReader(1L, readerDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reader updated successfully", response.getBody());
        verify(readerService, times(1)).updateReader(eq(1L), any(ReaderDto.class));
    }

    @Test
    void testDeleteReader() {
        doNothing().when(readerService).deleteReader(1L);

        ResponseEntity<String> response = readerController.deleteReader(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reader deleted successfully", response.getBody());
        verify(readerService, times(1)).deleteReader(1L);
    }

    @Test
    void testGetAllReaders() {
        when(readerService.getAllReaders()).thenReturn(List.of(new Reader()));

        ResponseEntity<List<ReaderDto>> response = readerController.getAllReaders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(readerService, times(1)).getAllReaders();
    }

    @Test
    void testGetReaderCount() {
        when(readerService.countReaders()).thenReturn(10L);

        ResponseEntity<Long> response = readerController.getReaderCount();

        assertEquals(10L, response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(readerService, times(1)).countReaders();
    }

    @Test
    void testFindReadersByLastName() {
        when(readerService.findByLastName("Smith")).thenReturn(List.of(new Reader()));

        ResponseEntity<List<ReaderDto>> response = readerController.findReadersByLastName("Smith");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(readerService, times(1)).findByLastName("Smith");
    }

    @Test
    void testGetActiveReaders() {
        when(readerService.getActiveReaders()).thenReturn(List.of(new Reader()));

        ResponseEntity<List<ReaderDto>> response = readerController.getActiveReaders();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        verify(readerService, times(1)).getActiveReaders();
    }

    @Test
    void testActivateReader() {
        doNothing().when(readerService).activateReader(1L);

        ResponseEntity<String> response = readerController.activateReader(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reader activated successfully", response.getBody());
        verify(readerService, times(1)).activateReader(1L);
    }

    @Test
    void testBlockReader() {
        doNothing().when(readerService).blockReader(1L);

        ResponseEntity<String> response = readerController.blockReader(1L);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Reader blocked successfully", response.getBody());
        verify(readerService, times(1)).blockReader(1L);
    }
}
