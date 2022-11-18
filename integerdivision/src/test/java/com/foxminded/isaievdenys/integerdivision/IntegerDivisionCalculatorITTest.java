package com.foxminded.isaievdenys.integerdivision;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.domain.DivisionStep;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionMathProviderImpl;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionViewProviderImpl;
import com.foxminded.isaievdenys.integerdivision.validator.DivisionValidatorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class IntegerDivisionCalculatorITTest {

    private IntegerDivisionCalculator calculator;
    @Mock
    private DivisionMathProviderImpl mathProvider;
    @Mock
    private DivisionViewProviderImpl viewProvider;
    @Mock
    private DivisionValidatorImpl validator;

    @BeforeEach
    void init() {
        calculator = new IntegerDivisionCalculator(validator, viewProvider, mathProvider);
    }

    @Test
    void makeDivision_shouldCalledValidatorAndViewProviderAndMathProviderMethodsAndReturnResultString_whenGetPositiveDividendAndDivisor() {
        int dividend = 5325291;
        int divisor = 1212;
        List<DivisionStep> steps = List.of(
            new DivisionStep(5325, 4848),
            new DivisionStep(4772, 3636),
            new DivisionStep(11369, 10908),
            new DivisionStep(4611, 3636));
        String viewProviderString = """
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
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);

        when(mathProvider.provideLongDivision(dividend, divisor))
            .thenReturn(divisionResult);
        when(viewProvider.provideView(divisionResult))
            .thenReturn(viewProviderString);

        String result = calculator.makeDivision(dividend, divisor);
        verify(validator).validate(dividend, divisor);
        verify(mathProvider).provideLongDivision(dividend, divisor);
        verify(viewProvider).provideView(divisionResult);
        assertEquals(viewProviderString, result);
    }

    @Test
    void makeDivision_shouldCalledValidatorAndViewProviderAndMathProviderMethodsAndReturnResultString_whenGetPositiveDividendAndNegativeDivisor() {
        int dividend = 127891;
        int divisor = -5;
        List<DivisionStep> steps = List.of(
            new DivisionStep(12, 10),
            new DivisionStep(27, 25),
            new DivisionStep(28, 25),
            new DivisionStep(39, 35),
            new DivisionStep(41, 40));
        String viewProviderString = """
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
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);

        when(mathProvider.provideLongDivision(dividend, divisor))
            .thenReturn(divisionResult);
        when(viewProvider.provideView(divisionResult))
            .thenReturn(viewProviderString);

        String result = calculator.makeDivision(dividend, divisor);
        verify(validator).validate(dividend, divisor);
        verify(mathProvider).provideLongDivision(dividend, divisor);
        verify(viewProvider).provideView(divisionResult);
        assertEquals(viewProviderString, result);
    }

    @Test
    void makeDivision_shouldCalledValidatorAndViewProviderAndMathProviderMethodsAndReturnResultString_whenGetNegativeDividendAndPositiveDivisor() {
        int dividend = -5325291;
        int divisor = 1212;
        List<DivisionStep> steps = List.of(
            new DivisionStep(5325, 4848),
            new DivisionStep(4772, 3636),
            new DivisionStep(11369, 10908),
            new DivisionStep(4611, 3636));
        String viewProviderString = """
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
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);

        when(mathProvider.provideLongDivision(dividend, divisor))
            .thenReturn(divisionResult);
        when(viewProvider.provideView(divisionResult))
            .thenReturn(viewProviderString);

        String result = calculator.makeDivision(dividend, divisor);
        verify(validator).validate(dividend, divisor);
        verify(mathProvider).provideLongDivision(dividend, divisor);
        verify(viewProvider).provideView(divisionResult);
        assertEquals(viewProviderString, result);
    }

    @Test
    void makeDivision_shouldCalledValidatorAndViewProviderAndMathProviderMethodsAndReturnResultString_whenGetNegativeDividendAndDivisor() {
        int dividend = -127891;
        int divisor = -31;
        List<DivisionStep> steps = List.of(
            new DivisionStep(127, 124),
            new DivisionStep(38, 31),
            new DivisionStep(79, 62),
            new DivisionStep(171, 155));
        String viewProviderString = """
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
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);

        when(mathProvider.provideLongDivision(dividend, divisor))
            .thenReturn(divisionResult);
        when(viewProvider.provideView(divisionResult))
            .thenReturn(viewProviderString);

        String result = calculator.makeDivision(dividend, divisor);
        verify(validator).validate(dividend, divisor);
        verify(mathProvider).provideLongDivision(dividend, divisor);
        verify(viewProvider).provideView(divisionResult);
        assertEquals(viewProviderString, result);
    }

    @Test
    void makeDivision_shouldCalledValidatorMethodAndThrowIllegalArgumentExceptionWithDividendIsNullMessage_whenDividendIsNull() {
        Integer dividend = null;
        int divisor = -31;
        String expectedErrorMessage = "dividend is null";

        doThrow(new IllegalArgumentException(expectedErrorMessage)).when(validator).validate(dividend, divisor);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(dividend, divisor));

        verify(validator).validate(dividend, divisor);
        verify(mathProvider, never()).provideLongDivision(dividend, divisor);
        verify(viewProvider, never()).provideView(any(DivisionResult.class));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldThrowIllegalArgumentExceptionWithDivisorIsNullMessage_whenDivisorIsNull() {
        int dividend = -31;
        Integer divisor = null;
        String expectedErrorMessage = "divisor is null";

        doThrow(new IllegalArgumentException(expectedErrorMessage)).when(validator).validate(dividend, divisor);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(dividend, divisor));

        verify(validator).validate(dividend, divisor);
        verify(mathProvider, never()).provideLongDivision(dividend, divisor);
        verify(viewProvider, never()).provideView(any(DivisionResult.class));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldThrowIllegalArgumentExceptionWithDivisorCanNotBeZeroMessage_whenDivisorEqualsZero() {
        int dividend = 31;
        int divisor = 0;
        String expectedErrorMessage = "divisor can not be 0";

        doThrow(new IllegalArgumentException(expectedErrorMessage)).when(validator).validate(dividend, divisor);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> calculator.makeDivision(dividend, divisor));

        verify(validator).validate(dividend, divisor);
        verify(mathProvider, never()).provideLongDivision(dividend, divisor);
        verify(viewProvider, never()).provideView(any(DivisionResult.class));
        assertEquals(expectedErrorMessage, exception.getMessage());
    }

    @Test
    void makeDivision_shouldCalledValidatorAndViewProviderAndMathProviderMethodsAndReturnResultString_whenDividendLessThanDivisorInAbsolute() {
        int dividend = -111;
        int divisor = -5325291;
        List<DivisionStep> steps = List.of();
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String viewProviderString = "-111 / -5325291 = 0";

        when(mathProvider.provideLongDivision(dividend, divisor))
            .thenReturn(divisionResult);
        when(viewProvider.provideView(divisionResult))
            .thenReturn(viewProviderString);

        String result = calculator.makeDivision(-111, -5325291);
        verify(validator).validate(dividend, divisor);
        verify(mathProvider).provideLongDivision(dividend, divisor);
        verify(viewProvider).provideView(divisionResult);
        assertEquals(viewProviderString, result);
    }

}
