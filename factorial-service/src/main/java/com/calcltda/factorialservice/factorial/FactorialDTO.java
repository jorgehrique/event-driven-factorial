package com.calcltda.factorialservice.factorial;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Range;

import java.io.Serializable;

public record FactorialDTO(
        long id,
        @NotNull
        @Range(min = 1, max = 99)
        Integer number,
        String result,
        FactorialStatus status

) implements Serializable {
    public static FactorialDTO fromFactorial(Factorial factorial){
        return new FactorialDTO(
                factorial.id(),
                factorial.number(),
                factorial.result(),
                FactorialStatus.valueOf(factorial.status())
        );
    }
}
