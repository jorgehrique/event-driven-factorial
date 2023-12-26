package com.calcltda.factorialservice.factorial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FactorialService {

    private final Logger log = LoggerFactory.getLogger(FactorialService.class);

    public Mono<FactorialDTO> createFactorialRequest(FactorialDTO factorialDTO){
        log.info("Created: {}", factorialDTO);
        return Mono.just(factorialDTO);
    }

    public Mono<FactorialDTO> getFactorialRequestById(String id){
        return Mono.just(new FactorialDTO(id, 1000));
    }
}
