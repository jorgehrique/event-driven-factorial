package com.calcltda.factorialworker.factorial;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class FactorialService {

    @Value("${factorial-worker.calm-down-ms}")
    private long calmDownMS; // adding some difficult ;)

    public BigInteger calculateFactorial(int number){
        BigInteger result = BigInteger.ONE;

        for (long factor = 2; factor <= number; factor++) {
            result = result.multiply(BigInteger.valueOf(factor));
            try {
                Thread.sleep(calmDownMS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return result;
    }

}
