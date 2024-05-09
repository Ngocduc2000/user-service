package org.example.userservice.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDTO extends GeneralDTO{
    String username;
    String password;
    String firstName;
    LocalDate dob;
    String lastName;
}
