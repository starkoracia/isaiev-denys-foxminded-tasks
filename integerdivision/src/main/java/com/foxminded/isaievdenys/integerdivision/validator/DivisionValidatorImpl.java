package com.foxminded.isaievdenys.integerdivision.validator;

public class DivisionValidatorImpl implements DivisionValidator {

    @Override
    public void validate(Integer dividend, Integer divisor) {
        validateDividend(dividend);
        validateDivisor(divisor);
    }

    private void validateDividend(Integer dividend) {
        if (dividend == null) {
            throw new IllegalArgumentException("dividend is null");
        }
    }

    private void validateDivisor(Integer divisor) {
        if (divisor == null) {
            throw new IllegalArgumentException("divisor is null");
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("divisor can not be 0");
        }
    }

}
