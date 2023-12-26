package com.calcltda.factorialworker.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class FactorialConsumer {

    private final Logger log = LoggerFactory.getLogger(FactorialConsumer.class);

    @RabbitListener(queues = "factorial-queue")
    void consumer(FactorialDTO factorialDTO){
        log.info("Consumed: {}", factorialDTO);
    }
}
