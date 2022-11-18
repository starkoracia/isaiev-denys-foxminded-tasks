package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionMathProviderImplTest {
    DivisionMathProvider mathProvider = new DivisionMathProviderImpl();

    @Test
    void provideLongDivision_shouldReturnDivisionResult_whenGetPositiveDividendAndDivisor() {
        int dividend = 10030;
        int divisor = 411;
        List<DivisionStep> expectedSteps = List.of(
            new DivisionStep(1003, 822),
            new DivisionStep(1810, 1644));
        DivisionResult expectedDivisionResult = new DivisionResult(expectedSteps, dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        assertEquals(expectedDivisionResult, divisionResult);
    }

    @Test
    void provideLongDivision_shouldReturnDivisionResult_whenGetPositiveDividendAndNegativeDivisor() {
        int dividend = 127891;
        int divisor = -5;
        List<DivisionStep> expectedSteps = List.of(
            new DivisionStep(12, 10),
            new DivisionStep(27, 25),
            new DivisionStep(28, 25),
            new DivisionStep(39, 35),
            new DivisionStep(41, 40));
        DivisionResult expectedDivisionResult = new DivisionResult(expectedSteps, dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        assertEquals(expectedDivisionResult, divisionResult);
    }

    @Test
    void provideLongDivision_shouldReturnDivisionResult_whenGetNegativeDividendAndPositiveDivisor() {
        int dividend = -5325291;
        int divisor = 1212;
        List<DivisionStep> expectedSteps = List.of(
            new DivisionStep(5325, 4848),
            new DivisionStep(4772, 3636),
            new DivisionStep(11369, 10908),
            new DivisionStep(4611, 3636));
        DivisionResult expectedDivisionResult = new DivisionResult(expectedSteps, dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        assertEquals(expectedDivisionResult, divisionResult);
    }

    @Test
    void provideLongDivision_shouldReturnDivisionResult_whenGetNegativeDividendAndDivisor() {
        int dividend = -127891;
        int divisor = -31;
        List<DivisionStep> expectedSteps = List.of(
            new DivisionStep(127, 124),
            new DivisionStep(38, 31),
            new DivisionStep(79, 62),
            new DivisionStep(171, 155));
        DivisionResult expectedDivisionResult = new DivisionResult(expectedSteps, dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        assertEquals(expectedDivisionResult, divisionResult);
    }

    @Test
    void provideLongDivision_shouldReturnDivisionResultWithEmptyListOfDivisionStep_whenDividendLessThanDivisorInAbsolute() {
        int dividend = -111;
        int divisor = -5325291;
        List<DivisionStep> expectedSteps = new ArrayList<>();
        DivisionResult expectedDivisionResult = new DivisionResult(expectedSteps, dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        assertEquals(expectedDivisionResult, divisionResult);
    }

    @Test
    void provideLongDivision_shouldThrowArithmeticExceptionWithByZeroMessage_whenGetPositiveDividendAndZeroDivisor() {
        int dividend = 10030;
        int divisor = 0;
        ArithmeticException exception = assertThrows(ArithmeticException.class, () -> mathProvider.provideLongDivision(dividend, divisor));
        String exceptionExpectedMessage = "/ by zero";
        assertEquals(exceptionExpectedMessage, exception.getMessage());
    }

    @Test
    void provideLongDivision_shouldThrowNullPointerException_whenGetNullDividendAndPositiveDivisor() {
        Integer dividend = null;
        int divisor = 11;
        assertThrows(NullPointerException.class, () -> mathProvider.provideLongDivision(dividend, divisor));
    }

    @Test
    void provideLongDivision_shouldThrowNullPointerException_whenGetPositiveDividendAndNullDivisor() {
        int dividend = 11;
        Integer divisor = null;
        assertThrows(NullPointerException.class, () -> mathProvider.provideLongDivision(dividend, divisor));
    }

    @Test
    void provideLongDivision_shouldThrowNullPointerException_whenGetNullDividendAndNullDivisor() {
        Integer dividend = null;
        Integer divisor = null;
        assertThrows(NullPointerException.class, () -> mathProvider.provideLongDivision(dividend, divisor));
    }

}
