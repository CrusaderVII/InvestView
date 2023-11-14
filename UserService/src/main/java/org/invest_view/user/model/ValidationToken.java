package org.invest_view.user.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

@Entity
@Table(name = "email_validation_tokens")
@Setter
@Getter
@NoArgsConstructor
public class ValidationToken {

    @Id
    @SequenceGenerator(
            name = "token_sequence",
            sequenceName = "token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "token",
            nullable = false,
            unique = true
    )
    private String token;

    @Column(
            name = "date",
            nullable = false
    )
    private String generatedAt;
    @Column(
            name = "is_email_validated",
            nullable = false
    )
    private boolean isEmailValidated;

    @OneToOne
    @JoinColumn(name = "email")
    private User user;

    public ValidationToken(User user) {
        this.token = UUID.randomUUID().toString();
        this.generatedAt = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
        this.isEmailValidated = false;
    }
}
