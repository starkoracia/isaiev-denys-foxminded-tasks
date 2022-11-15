package com.foxminded.isaievdenys.integerdivision.validator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionValidatorImplTest {
    DivisionValidator validator;

    @BeforeEach
    void init() {
        validator = new DivisionValidatorImpl();
    }

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWithDividendIsNullMessage_whenGetNullDividendAndPositiveDivisor() {
        Integer dividend = null;
        int divisor = 11;
        String expectedMessage = "dividend is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dividend, divisor));
        assertEquals(expectedMessage.trim(), exception.getMessage().trim());
    }

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWithDividendIsNullMessage_whenGetNullDividendAndNullDivisor() {
        Integer dividend = null;
        Integer divisor = null;
        String expectedMessage = "dividend is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dividend, divisor));
        assertEquals(expectedMessage.trim(), exception.getMessage().trim());
    }

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWithDivisorIsNullMessage_whenGetPositiveDividendAndNullDivisor() {
        int dividend = 11;
        Integer divisor = null;
        String expectedMessage = "divisor is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dividend, divisor));
        assertEquals(expectedMessage.trim(), exception.getMessage().trim());
    }

    @Test
    void validate_shouldThrowIllegalArgumentExceptionWithDivisorCanNotBeZeroMessage_whenGetPositiveDividendAndZeroDivisor() {
        int dividend = 11;
        int divisor = 0;
        String expectedMessage = "divisor can not be 0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> validator.validate(dividend, divisor));
        assertEquals(expectedMessage.trim(), exception.getMessage().trim());
    }

    @Test
    void validate_shouldDoNothing_whenGetPositiveDividendAndDivisor() {
        int dividend = 11;
        int divisor = 12;
        validator.validate(dividend, divisor);
    }

    @Test
    void validate_shouldDoNothing_whenGetPositiveDividendAndNegativeDivisor() {
        int dividend = 127891;
        int divisor = -5;
        validator.validate(dividend, divisor);
    }

    @Test
    void validate_shouldDoNothing_whenGetNegativeDividendAndPositiveDivisor() {
        int dividend = -5325291;
        int divisor = 1212;
        validator.validate(dividend, divisor);
    }

    @Test
    void validate_shouldDoNothing_whenDividendLessThanDivisorInAbsolute() {
        int dividend = -111;
        int divisor = -5325291;
        validator.validate(dividend, divisor);
    }

}
