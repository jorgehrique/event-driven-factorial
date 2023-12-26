package com.calcltda.factorialservice.factorial;

import java.io.Serializable;

public record FactorialDTO(
        long id,
        long number
) implements Serializable {
    public static FactorialDTO fromFactorial(Factorial factorial){
        return new FactorialDTO(
                factorial.id(),
                factorial.number()
        );
    }
}
