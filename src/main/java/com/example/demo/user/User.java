package com.example.demo.user;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Email(message = "Not a valid email.")
    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    @Nullable
    private String description;

    @Nullable
    private String countryOfOrigin;

    private String profilePhotoURL;
}
