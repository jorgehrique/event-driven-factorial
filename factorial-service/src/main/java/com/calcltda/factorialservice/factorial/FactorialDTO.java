package com.calcltda.factorialservice.factorial;

public record FactorialDTO(
        long id,
        long number
) {
    public static FactorialDTO fromFactorial(Factorial factorial){
        return new FactorialDTO(
                factorial.id(),
                factorial.number()
        );
    }
}
