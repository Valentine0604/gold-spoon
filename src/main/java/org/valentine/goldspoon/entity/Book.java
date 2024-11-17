package org.valentine.goldspoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "book")
@Data
@ToString
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;
    private String title;
    private String author;
    private int pagesNumber;
    private String publisher;
}
