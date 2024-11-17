package org.valentine.goldspoon.web;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.valentine.goldspoon.dto.ReaderDto;
import org.valentine.goldspoon.entity.Reader;
import org.valentine.goldspoon.service.ReaderService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/api/reader")
public class ReaderController {

    private final ReaderService readerService;
    private final ModelMapper modelMapper;

    @GetMapping("/{readerId}")
    public ResponseEntity<ReaderDto> getReader(@PathVariable Long readerId) {
        ReaderDto readerDto = modelMapper.map(readerService.getReader(readerId), ReaderDto.class);
        return new ResponseEntity<>(readerDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> addReader(@RequestBody ReaderDto readerDto) {
        readerService.addReader(modelMapper.map(readerDto, Reader.class));
        return new ResponseEntity<>("Reader added successfully", HttpStatus.CREATED);
    }

    @PatchMapping("/{readerId}")
    public ResponseEntity<String> updateReader(@PathVariable Long readerId, @RequestBody ReaderDto readerDto) {
        readerService.updateReader(readerId, readerDto);
        return new ResponseEntity<>("Reader updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/{readerId}")
    public ResponseEntity<String> deleteReader(@PathVariable Long readerId) {
        readerService.deleteReader(readerId);
        return new ResponseEntity<>("Reader deleted successfully", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<ReaderDto>> getAllReaders() {
        return new ResponseEntity<>(readerService.getAllReaders().stream().map(reader -> modelMapper.map(reader, ReaderDto.class)).toList(), HttpStatus.OK);
    }

}