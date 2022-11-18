package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.domain.DivisionStep;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DivisionViewProviderImplTest {
    DivisionViewProvider viewProvider = new DivisionViewProviderImpl();

    @Test
    void provideView_shouldReturnDivisionResultString_whenGetPositiveDividendAndDivisor() {
        int dividend = 5325291;
        int divisor = 1212;
        String expectedString = """
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
        List<DivisionStep> steps = List.of(
            new DivisionStep(5325, 4848),
            new DivisionStep(4772, 3636),
            new DivisionStep(11369, 10908),
            new DivisionStep(4611, 3636));
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultString_whenGetPositiveDividendAndEqualDivisor() {
        int dividend = 5325291;
        int divisor = 5325291;
        String expectedString = """
            5325291 / 5325291 = 1
            """;
        List<DivisionStep> steps = List.of(
            new DivisionStep(5325291, 5325291));
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultString_whenGetPositiveDividendAndNegativeDivisor() {
        int dividend = 127891;
        int divisor = -5;
        String expectedString = """
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
        List<DivisionStep> steps = List.of(
            new DivisionStep(12, 10),
            new DivisionStep(27, 25),
            new DivisionStep(28, 25),
            new DivisionStep(39, 35),
            new DivisionStep(41, 40));
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultString_whenGetNegativeDividendAndPositiveDivisor() {
        int dividend = -5325291;
        int divisor = 1212;
        String expectedString = """
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
        List<DivisionStep> steps = List.of(
            new DivisionStep(5325, 4848),
            new DivisionStep(4772, 3636),
            new DivisionStep(11369, 10908),
            new DivisionStep(4611, 3636));
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultString_whenGetNegativeDividendAndDivisor() {
        int dividend = -127891;
        int divisor = -31;
        String expectedString = """
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
        List<DivisionStep> steps = List.of(
            new DivisionStep(127, 124),
            new DivisionStep(38, 31),
            new DivisionStep(79, 62),
            new DivisionStep(171, 155));
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultStringEqualToZero_whenDividendLessThanDivisorInAbsolute() {
        int dividend = -111;
        int divisor = -5325291;
        String expectedString = "-111 / -5325291 = 0";
        List<DivisionStep> steps = List.of();
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultStringEqualToZero_whenPositiveDividendLessThanPositiveDivisor() {
        int dividend = 111;
        int divisor = 5325291;
        String expectedString = "111 / 5325291 = 0";
        List<DivisionStep> steps = List.of();
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultStringEqualToZero_whenDividendEqualsZeroAndLessThanDivisorInAbsolute() {
        int dividend = 0;
        int divisor = 5325291;
        String expectedString = "0 / 5325291 = 0";
        List<DivisionStep> steps = List.of();
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

    @Test
    void provideView_shouldReturnDivisionResultStringEqualToZero_whenDividendEqualsZeroAndMoreThanDivisorInAbsolute() {
        int dividend = 0;
        int divisor = -5325291;
        String expectedString = "0 / -5325291 = 0";
        List<DivisionStep> steps = List.of();
        DivisionResult divisionResult = new DivisionResult(steps, dividend, divisor);
        String resultString = viewProvider.provideView(divisionResult);
        assertEquals(expectedString.trim(), resultString.trim());
    }

}
