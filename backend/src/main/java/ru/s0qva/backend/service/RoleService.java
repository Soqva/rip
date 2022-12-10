package ru.s0qva.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.s0qva.backend.dto.RoleDto;
import ru.s0qva.backend.mapper.RoleMapper;
import ru.s0qva.backend.repository.RoleRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RoleService {
    public static final String DEFAULT_ROLE = "USER";
    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

    public RoleDto getDefaultRole() {
        return roleRepository.findByCode(DEFAULT_ROLE)
                .map(roleMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("Failed to get default role"));
    }
}
