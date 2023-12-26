package com.calcltda.factorialservice.factorial;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/factorial")
public class FactorialEndpoint {

    private final FactorialService factorialService;

    public FactorialEndpoint(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @PostMapping
    public Mono<FactorialDTO> createFactorialRequest(@Valid @RequestBody FactorialDTO factorialDTO){
        return factorialService
                .createFactorialRequest(factorialDTO)
                .doOnSuccess(factorial -> ResponseEntity.created(URI.create(String.valueOf(factorial.id()))))
                .doOnError(Exception.class, ex -> ResponseEntity.status(HttpStatus.BAD_REQUEST));
    }

    @GetMapping("/{id}")
    public Mono<FactorialDTO> getFactorialRequestById(@PathVariable String id){
        return factorialService.getFactorialRequestById(id);
    }
}
