package com.study_flow.modules.user.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NotNull
    @Pattern(regexp = "^\\S{5,20}$",
            message = "The username must have min of 5 and max of 20 characters and no spaces.")
    private String username;
    @NotNull
    @Email
    private String email;
    @NotNull
    @Pattern(regexp = "^(?=>*\\d)(?=.*[@#$^&+=!].{10,25}$)",
    message = "The password should contain at least one number and one uppercase letter. The min of characters is 10 and de max 25.")
    private String password;
    @CreationTimestamp
    private LocalDateTime creation;
}
