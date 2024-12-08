package org.valentine.goldspoon.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "reader")
@Data
@ToString
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long readerId;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String favouriteGenre;
    private boolean active;
}
