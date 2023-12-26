package com.calcltda.factorialservice.factorial;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/factorial")
public class FactorialEndpoint {

    private final FactorialService factorialService;

    public FactorialEndpoint(FactorialService factorialService) {
        this.factorialService = factorialService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<FactorialDTO> createFactorialRequest(@RequestBody FactorialDTO factorialDTO){
        return factorialService.createFactorialRequest(factorialDTO);
    }

    @GetMapping("/{id}")
    public Mono<FactorialDTO> getFactorialRequestById(@PathVariable String id){
        return factorialService.getFactorialRequestById(id);
    }
}
