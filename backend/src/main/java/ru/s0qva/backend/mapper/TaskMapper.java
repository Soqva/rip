package ru.s0qva.backend.mapper;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import ru.s0qva.backend.domain.Task;
import ru.s0qva.backend.dto.TaskDto;

@Component
@RequiredArgsConstructor
public class TaskMapper {
    private final ModelMapper mapper;

    public TaskDto mapToDto(Task entity) {
        return map(entity, TaskDto.class);
    }

    public Task mapToEntity(TaskDto dto) {
        return map(dto, Task.class);
    }

    private <F, T> T map(F source, Class<T> destinationClass) {
        if (ObjectUtils.isEmpty(source)) {
            return BeanUtils.instantiateClass(destinationClass);
        }
        return mapper.map(source, destinationClass);
    }
}
