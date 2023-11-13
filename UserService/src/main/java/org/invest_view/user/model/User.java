package org.invest_view.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Entity(name = "User")
@Table(name = "users")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User {

    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "name",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    @Size(min = 2, message = "Name should contain at least 2 characters")
    private String name;

    @Column(
            name = "email",
            nullable = false,
            columnDefinition = "TEXT",
            unique = true
    )
    @Email(message = "Email is invalid")
    private String email;

    @Column(
            name = "email_confirm_code"
    )
    private String emailConfirmCode;

    @Column(
            name = "is_email_confirmed"
    )
    private boolean isEmailConfirmed;
    @Column(
            name = "password",
            nullable = false,
            columnDefinition = "TEXT"
    )

    @Size(min = 8, message = "Password should contain at least 8 symbols")
    private String password;

    @ManyToMany
    @JoinTable(
            name = "user_issuer",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "issuer_id")
    )

    @JsonIgnore
    private List<IssuerData> issuersData;

    public User (String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public void addIssuer(IssuerData issuerData) {
        if (issuersData == null) {
            this.issuersData = new ArrayList<>();
        }

        this.issuersData.add(issuerData);
    }

    public void deleteIssuer(IssuerData issuerData) {
        if (issuerData == null) return;

        issuersData.remove(issuerData);
    }

    private String generateEmailConfirmCode() {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();

        IntStream.rangeClosed(1, 5).forEach(x->{
            builder.append((char) (random.nextInt(25)+97));
        });

        return builder.toString();
    }
}