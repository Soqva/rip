package ru.s0qva.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.s0qva.backend.domain.Role;
import ru.s0qva.backend.dto.RoleDto;

@Component
@RequiredArgsConstructor
public class RoleMapper {
    private final ModelMapper mapper;

    public RoleDto mapToDto(Role dto) {
        return map(dto, RoleDto.class);
    }

    public Role mapToEntity(RoleDto entity) {
        return map(entity, Role.class);
    }

    private <F, T> T map(F source, Class<T> destinationClass) {
        if (ObjectUtils.isEmpty(source)) {
            return BeanUtils.instantiateClass(destinationClass);
        }
        return mapper.map(source, destinationClass);
    }
}
