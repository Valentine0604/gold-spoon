package org.valentine.goldspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Builder
public class BookDto {
    private final String title;
    private final String author;
    private final int pagesNumber;
    private final String publisher;
    private final boolean available;
}
