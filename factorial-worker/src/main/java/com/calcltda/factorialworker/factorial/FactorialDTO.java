package com.calcltda.factorialworker.factorial;

import java.io.Serializable;

public record FactorialDTO(
        long id,
        Integer number,
        FactorialStatus status
) implements Serializable {

}