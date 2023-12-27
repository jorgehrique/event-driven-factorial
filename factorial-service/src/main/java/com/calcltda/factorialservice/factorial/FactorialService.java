package com.calcltda.factorialservice.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FactorialService {

    private final Logger log = LoggerFactory.getLogger(FactorialService.class);

    private final FactorialRepository factorialRepository;
    private final RabbitTemplate rabbitTemplate;

    public FactorialService(FactorialRepository factorialRepository, RabbitTemplate rabbitTemplate) {
        this.factorialRepository = factorialRepository;
        this.rabbitTemplate = rabbitTemplate;
    }

    public Mono<FactorialDTO> createFactorialRequest(FactorialDTO factorialDTO){
        return factorialRepository
                .save(Factorial.newFactorial(factorialDTO))
                .map(FactorialDTO::fromFactorial)
                .doOnNext(f -> rabbitTemplate.convertAndSend("amq.direct", "factorial", f))
                .doOnSuccess(factorial -> log.info("Created: {}", factorial))
                .doOnError(ex -> log.error("Error during factorial creation: {}", ex.getMessage()));
    }

    public Mono<FactorialDTO> getFactorialRequestById(Long id){
        return factorialRepository
                .findById(id)
                .map(FactorialDTO::fromFactorial);
    }

}
