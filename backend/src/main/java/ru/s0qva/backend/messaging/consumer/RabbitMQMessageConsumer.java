package ru.s0qva.backend.messaging.consumer;

import org.springframework.stereotype.Component;

@Component
public class RabbitMQMessageConsumer {

    public void consume(String message) {
        System.out.println(message + " has been consumed!");
    }
}
