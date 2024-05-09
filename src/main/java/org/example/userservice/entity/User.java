package org.example.userservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.userservice.entity.abstractentity.AbstractClass;
import org.example.userservice.entity.abstractentity.AbstractClassDate;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Entity
@Table(name = "user")
public class User extends AbstractClassDate {
    @Column(name = "user_name")
    String username;
    @Column(name = "password")
    String password;
    @Column(name = "first_name")
    String firstName;
    @Column(name = "date_of_birth")
    LocalDate dob;
    @Column(name = "last_name")
    String lastName;
}
