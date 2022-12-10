package ru.s0qva.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import ru.s0qva.backend.dto.TaskDto;
import ru.s0qva.backend.mapper.TaskMapper;
import ru.s0qva.backend.repository.ClientRepository;
import ru.s0qva.backend.repository.TaskRepository;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TaskService {
    private final ClientTaskService clientTaskService;
    private final TaskRepository taskRepository;
    private final ClientRepository clientRepository;
    private final TaskMapper taskMapper;

    public List<TaskDto> getAll(String username) {
        return taskRepository.findAll(username)
                .stream()
                .map(taskMapper::mapToDto)
                .collect(toList());
    }

    public TaskDto getById(Long id) {
        return taskRepository.findById(id)
                .map(taskMapper::mapToDto)
                .orElseThrow(() -> new RuntimeException("There is no task with id " + id));
    }

    @Transactional
    public TaskDto create(String username, TaskDto taskDto) {
        var taskId = taskDto.getId();

        if (!ObjectUtils.isEmpty(taskId)) {
            throw new RuntimeException("The task with id " + taskId + " already exists");
        }
        var client = clientRepository.findByUsername(username).orElseThrow(() ->
                new RuntimeException("There is no client with username " + username)
        );
        var task = taskMapper.mapToEntity(taskDto);
        var createdTask = taskRepository.save(task);

        clientTaskService.create(client, createdTask);
        return taskMapper.mapToDto(task);
    }

    @Transactional
    public TaskDto edit(TaskDto taskDto) {
        var taskId = taskDto.getId();

        if (ObjectUtils.isEmpty(taskId)) {
            throw new RuntimeException("There is no task with id " + taskId);
        }
        var task = taskMapper.mapToEntity(taskDto);
        var editedTask = taskRepository.save(task);

        return taskMapper.mapToDto(editedTask);
    }

    @Transactional
    public void deleteById(Long id) {
        var task = taskRepository.findById(id).orElseThrow(() ->
                new RuntimeException("There is no task with id " + id)
        );
        taskRepository.delete(task);
    }
}
