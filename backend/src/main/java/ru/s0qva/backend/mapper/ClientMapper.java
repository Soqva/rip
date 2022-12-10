package ru.s0qva.backend.mapper;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.s0qva.backend.domain.Client;
import ru.s0qva.backend.dto.ClientDto;

@Component
public class ClientMapper {
    private final ModelMapper mapper;

    public ClientMapper(ModelMapper mapper) {
        prepareMapper(mapper);

        this.mapper = mapper;
    }

    public ClientDto mapToDto(Client entity) {
        return map(entity, ClientDto.class);
    }

    public Client mapToEntity(ClientDto dto) {
        return map(dto, Client.class);
    }

    private <F, T> T map(F source, Class<T> destinationClass) {
        if (ObjectUtils.isEmpty(source)) {
            return BeanUtils.instantiateClass(destinationClass);
        }
        return mapper.map(source, destinationClass);
    }

    private void prepareMapper(ModelMapper mapper) {
        mapper.addMappings(getPropertyMap());
    }

    private PropertyMap<Client, ClientDto> getPropertyMap() {
        return new PropertyMap<>() {
            @Override
            protected void configure() {
                map().setBanned(source.isBanned());
                map().setPassword(null);
            }
        };
    }
}
