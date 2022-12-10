package ru.s0qva.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.s0qva.backend.domain.ClientRole;

@Repository
public interface ClientRoleRepository extends JpaRepository<ClientRole, Long> {
}
