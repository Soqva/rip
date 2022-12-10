package ru.s0qva.backend.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.s0qva.backend.dto.TaskDto;
import ru.s0qva.backend.service.TaskService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/tasks")
@CrossOrigin
@SecurityRequirement(name = "api-access")
public class TaskController {
    private final TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDto>> getAll(Authentication authentication) {
        var username = authentication.getName();
        var tasks = taskService.getAll(username);

        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskDto> getById(@PathVariable(name = "id") Long id) {
        var task = taskService.getById(id);

        return ResponseEntity.ok(task);
    }

    @PostMapping
    public ResponseEntity<Void> create(Authentication authentication, @RequestBody TaskDto taskDto) {
        var username = authentication.getName();
        var createdTask = taskService.create(username, taskDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdTask.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PatchMapping
    public ResponseEntity<TaskDto> edit(@RequestBody TaskDto taskDto) {
        var editedTask = taskService.edit(taskDto);

        return ResponseEntity.ok(editedTask);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable(name = "id") Long id) {
        taskService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}
