package ru.s0qva.backend.service;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ru.s0qva.backend.domain.Client;
import ru.s0qva.backend.domain.ClientRole;
import ru.s0qva.backend.dto.ClientDto;
import ru.s0qva.backend.mapper.ClientMapper;
import ru.s0qva.backend.repository.ClientRepository;

import java.util.Collection;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientService implements UserDetailsService {
    private final ClientRoleService clientRoleService;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    public ClientDto getById(Long id) {
        return clientRepository.findById(id)
                .map(clientMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("There is no user with id " + id));
    }

    @Transactional
    public ClientDto create(ClientDto clientDto) {
        var clientId = clientDto.getId();

        if (!ObjectUtils.isEmpty(clientId)) {
            throw new RuntimeException("The client with id " + clientId + " already exists");
        }
        var client = clientMapper.mapToEntity(clientDto);
        var createdClient = clientRepository.save(client);

        clientRoleService.create(client);
        return clientMapper.mapToDto(createdClient);
    }

    @Override
    @SneakyThrows
    public UserDetails loadUserByUsername(String username) {
        return clientRepository.findByUsername(username)
                .map(client -> new User(
                        username,
                        client.getPassword(),
                        getRoles(client)
                ))
                .orElseThrow(() ->
                        new RuntimeException("There is no user with username " + username)
                );
    }

    private Collection<? extends GrantedAuthority> getRoles(Client client) {
        return client.getClientRoles()
                .stream()
                .map(ClientRole::getRole)
                .collect(toList());
    }
}
