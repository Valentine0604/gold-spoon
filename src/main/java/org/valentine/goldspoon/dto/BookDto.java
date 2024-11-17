package org.valentine.goldspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class BookDto {
    private final String title;
    private final String author;
    private final int pagesNumber;
    private final String publisher;
}
