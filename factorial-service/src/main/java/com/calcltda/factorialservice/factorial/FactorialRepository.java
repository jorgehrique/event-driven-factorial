package com.calcltda.factorialservice.factorial;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorialRepository extends ReactiveCrudRepository<Factorial, String> {

}
