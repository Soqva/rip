package ru.s0qva.backend.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.s0qva.backend.domain.Client;
import ru.s0qva.backend.domain.ClientTask;
import ru.s0qva.backend.domain.Task;
import ru.s0qva.backend.repository.ClientTaskRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ClientTaskService {
    private final ClientTaskRepository clientTaskRepository;

    @Transactional
    public ClientTask create(Client client, Task task) {
        var clientTask = ClientTask.builder()
                .client(client)
                .task(task)
                .build();
        return clientTaskRepository.save(clientTask);
    }
}
