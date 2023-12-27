package com.calcltda.factorialworker.factorial;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.math.BigInteger;

@Table("TB_FACTORIAL")
public record Factorial(
        @Id
        Long id,
        Integer number,
        String result,
        String status
) {

}
