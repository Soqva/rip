package ru.s0qva.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.s0qva.backend.domain.Task;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t " +
            "from Task t " +
            "join t.clientTasks ct " +
            "where ct.client.username like :username")
    List<Task> findAll(@Param("username") String username);
}
