package com.calcltda.factorialworker.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.math.BigInteger;

@Component
public class FactorialConsumer {

    private final Logger log = LoggerFactory.getLogger(FactorialConsumer.class);
    private final FactorialService factorialService;

    public FactorialConsumer(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @RabbitListener(queues = "factorial-queue")
    void consumer(FactorialDTO factorialDTO){
        log.info("Initiating processing: {}", factorialDTO);

        BigInteger result = factorialService.calculateFactorial(factorialDTO.number());

        Factorial factorial = new Factorial(
                factorialDTO.id(),
                factorialDTO.number(),
                result.toString(),
                FactorialStatus.DONE.name());

        Factorial inserted = factorialService.updateFactorial(factorial).block();

        log.info("Finalized processing: {}", inserted);
    }

}
