package ru.s0qva.backend.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import static javax.persistence.FetchType.LAZY;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Builder
@NoArgsConstructor
@Entity
@Table(name = "client_task")
public class ClientTask {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "task_id")
    private Task task;

    public ClientTask(Long id, Client client, Task task) {
        this.id = id;

        setClient(client);
        setTask(task);
    }

    public void setClient(Client client) {
        this.client = client;

        client.getClientTasks().add(this);
    }

    public void setTask(Task task) {
        this.task = task;

        task.getClientTasks().add(this);
    }
}
