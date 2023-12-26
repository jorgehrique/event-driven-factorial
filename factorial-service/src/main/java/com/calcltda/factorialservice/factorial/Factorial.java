package com.calcltda.factorialservice.factorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TB_FACTORIAL")
public record Factorial(
        @Id
        long id,
        long number
) {
    public static Factorial fromDTO(FactorialDTO factorialDTO){
        return new Factorial(
                factorialDTO.id(),
                factorialDTO.number()
        );
    }
}
