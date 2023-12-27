package com.calcltda.factorialworker.factorial;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FactorialRepository extends ReactiveCrudRepository<Factorial, Long> {

}
