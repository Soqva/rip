package ru.s0qva.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;
import static javax.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.NONE;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;

    @Getter(NONE)
    @Column(name = "banned")
    private Boolean banned;

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    @Builder.Default
    private List<ClientRole> clientRoles = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    @ToString.Exclude
    @Builder.Default
    private List<ClientTask> clientTasks = new ArrayList<>();

    public Boolean isBanned() {
        return banned;
    }
}
