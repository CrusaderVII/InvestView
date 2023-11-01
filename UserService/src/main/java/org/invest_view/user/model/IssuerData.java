package org.invest_view.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity(name = "IssuerData")
@Table(name = "issuers")
@NoArgsConstructor
@Getter
@Setter
@ToString
public class IssuerData {

    @Id
    @SequenceGenerator(
            name = "issuer_data_sequence",
            sequenceName = "issuer_data_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "issuer_data_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    @JsonIgnore
    private Long id;

    @Column(
            name = "sec_id",
            nullable = false
    )
    private String secId;

    @Column(
            name = "full_name",
            nullable = false
    )
    private String fullName;

    @ManyToMany(
            mappedBy = "issuersData"
    )
    @JsonIgnore
    private List<User> users;

    @Column(
            name = "image_id"
    )
    private String imageId;

    public IssuerData(String secId, String fullName) {
        this.secId = secId;
        this.fullName = fullName;
    }

    public void addUser(User user) {
        if (users == null) {
            this.users = new ArrayList<>();
        }

        this.users.add(user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        IssuerData other = (IssuerData) obj;
        return Objects.equals(secId, other.secId);
    }
}