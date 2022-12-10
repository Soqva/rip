package ru.s0qva.backend.messaging.producer;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.s0qva.backend.configuration.RabbitMQConfiguration;

import static java.util.concurrent.TimeUnit.SECONDS;

@Component
@RequiredArgsConstructor
public class RabbitMQMessageProducer {
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedDelay = 10L, initialDelay = 10L, timeUnit = SECONDS)
    public void produce() {
        System.out.println("Message is being produced...");
        rabbitTemplate.convertAndSend(
                RabbitMQConfiguration.topicExchangeName,
                "rip.default",
                "[ message from a producer c: ]"
        );
    }
}
