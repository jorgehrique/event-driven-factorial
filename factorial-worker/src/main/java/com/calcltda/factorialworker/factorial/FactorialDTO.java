package com.calcltda.factorialworker.factorial;

import java.io.Serializable;

public record FactorialDTO(
        long id,
        long number
) implements Serializable {

}