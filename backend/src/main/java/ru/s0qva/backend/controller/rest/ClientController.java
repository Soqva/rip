package ru.s0qva.backend.controller.rest;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.s0qva.backend.dto.ClientDto;
import ru.s0qva.backend.service.ClientService;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/clients")
@CrossOrigin
public class ClientController {
    private final ClientService clientService;

    @GetMapping(path = "/{id}")
    @SecurityRequirement(name = "api-access")
    public ResponseEntity<ClientDto> getById(@PathVariable(name = "id") Long id) {
        var client = clientService.getById(id);

        return ResponseEntity.ok(client);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody ClientDto clientDto) {
        var createdClient = clientService.create(clientDto);
        var location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdClient.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }
}
