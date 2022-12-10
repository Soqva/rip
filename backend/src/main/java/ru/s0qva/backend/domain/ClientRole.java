package ru.s0qva.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "client_role")
public class ClientRole {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "role_code")
    private Role role;

    public ClientRole(Long id, Client client, Role role) {
        this.id = id;

        setClient(client);
        setRole(role);
    }

    public void setClient(Client client) {
        this.client = client;

        client.getClientRoles().add(this);
    }

    public void setRole(Role role) {
        this.role = role;

        role.getClientRoles().add(this);
    }
}
