package com.foxminded.isaievdenys.integerdivision;

import com.foxminded.isaievdenys.integerdivision.provider.DivisionMathProviderImpl;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionViewProviderImpl;
import com.foxminded.isaievdenys.integerdivision.validator.DivisionValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class IntegerDivisionCalculatorTest {

    private IntegerDivisionCalculator calculator;

    @BeforeEach
    void init() {
        calculator = new IntegerDivisionCalculator(
            new DivisionValidatorImpl(),
            new DivisionViewProviderImpl(),
            new DivisionMathProviderImpl());
    }

    @Test
    void makeDivision_shouldPrintResultOfLongDivision_whenGetPositiveDividendAndDivisor() {
        String expected = """
            _5325291|1212
             4848   |----
             ----   |4393
             _4772
              3636
              ----
             _11369
              10908
              -----
               _4611
                3636
                ----
                 975

            """;
        String result = calculator.makeDivision(5325291, 1212);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void makeDivision_shouldPrintResultOfLongDivision_whenGetPositiveDividendAndNegativeDivisor() {
        String expected = """
            _127891|-5
             10    |------
             --    |-25578
             _27
              25
              --
              _28
               25
               --
               _39
                35
                --
                _41
                 40
                 --
                  1

            """;
        String result = calculator.makeDivision(127891, -5);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void makeDivision_shouldPrintResultOfLongDivision_whenGetNegativeDividendAndPositiveDivisor() {
        String expected = """
            _-5325291|1212
              4848   |-----
              ----   |-4393
              _4772
               3636
               ----
              _11369
               10908
               -----
                _4611
                 3636
                 ----
                  975

            """;
        String result = calculator.makeDivision(-5325291, 1212);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void makeDivision_shouldPrintResultOfLongDivision_whenGetNegativeDividendAndDivisor() {
        String expected = """
            _-127891|-31
              124   |----
              ---   |4125
               _38
                31
                --
                _79
                 62
                 --
                _171
                 155
                 ---
                  16

            """;
        String result = calculator.makeDivision(-127891, -31);
        assertEquals(expected.trim(), result.trim());
    }

    @Test
    void makeDivision_shouldThrowIllegalArgumentExceptionWithDividendIsNullMessage_whenDividendIsNull() {
        String expectedErrorMessage = "dividend is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(null, -31));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldThrowIllegalArgumentExceptionWithDivisorIsNullMessage_whenDivisorIsNull() {
        String expectedErrorMessage = "divisor is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(-31, null));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldThrowIllegalArgumentExceptionWithDivisorCanNotBeZeroMessage_whenDivisorEqualsZero() {
        String expectedErrorMessage = "divisor can not be 0";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(31, 0));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldPrintResultEqualToZero_whenDividendLessThanDivisorInAbsolute() {
        String expected = "-111 / -5325291 = 0";
        String result = calculator.makeDivision(-111, -5325291);
        assertEquals(expected.trim(), result.trim());
    }

}
