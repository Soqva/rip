package ru.s0qva.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.s0qva.backend.domain.Client;
import ru.s0qva.backend.domain.ClientRole;
import ru.s0qva.backend.domain.Role;
import ru.s0qva.backend.dto.RoleDto;
import ru.s0qva.backend.mapper.RoleMapper;
import ru.s0qva.backend.repository.ClientRoleRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientRoleService {
    private final RoleService roleService;
    private final ClientRoleRepository clientRoleRepository;
    private final RoleMapper roleMapper;

    @Transactional
    public ClientRole create(Client client) {
        var role = Optional.of(roleService.getDefaultRole())
                .map(roleMapper::mapToEntity)
                .orElseThrow(() -> new RuntimeException("Could not have got default role"));
        var clientRole = ClientRole.builder()
                .client(client)
                .role(role)
                .build();
        return clientRoleRepository.save(clientRole);
    }
}
