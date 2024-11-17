package org.valentine.goldspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(force = true)
public class ReaderDto {
    private final String firstName;
    private final String lastName;
    private final String email;
    private final String phoneNumber;
    private final String favouriteGenre;
}
