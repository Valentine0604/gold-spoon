package org.valentine.goldspoon.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReaderDto {
    public String firstName;
    public String lastName;
    public String email;
    public String phoneNumber;
    public String favouriteGenre;
}
