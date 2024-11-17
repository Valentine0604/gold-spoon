package org.valentine.goldspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDto {
    public String title;
    public String author;
    public int pagesNumber;
    public String publisher;
}
