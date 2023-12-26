package com.calcltda.factorialservice.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FactorialService {

    private final Logger log = LoggerFactory.getLogger(FactorialService.class);

    private final FactorialRepository factorialRepository;

    public FactorialService(FactorialRepository factorialRepository) {
        this.factorialRepository = factorialRepository;
    }

    public Mono<FactorialDTO> createFactorialRequest(FactorialDTO factorialDTO){
        return factorialRepository
                .save(Factorial.fromDTO(factorialDTO))
                .map(FactorialDTO::fromFactorial)
                .doOnSuccess(factorial -> log.info("Created: {}", factorial))
                .doOnError(ex -> log.error("Error during factorial creation: {}", ex.getMessage()));
    }

    public Mono<FactorialDTO> getFactorialRequestById(String id){
        return factorialRepository
                .findById(id)
                .map(FactorialDTO::fromFactorial);
    }

}
