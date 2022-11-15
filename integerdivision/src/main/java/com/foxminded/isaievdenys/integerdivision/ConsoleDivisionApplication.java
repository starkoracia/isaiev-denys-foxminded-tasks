package com.foxminded.isaievdenys.integerdivision;

import com.foxminded.isaievdenys.integerdivision.provider.DivisionMathProviderImpl;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionViewProviderImpl;
import com.foxminded.isaievdenys.integerdivision.validator.DivisionValidatorImpl;

public class ConsoleDivisionApplication {

    public static void main(String[] args) {
        IntegerDivisionCalculator calculator  = new IntegerDivisionCalculator(
            new DivisionValidatorImpl(),
            new DivisionViewProviderImpl(),
            new DivisionMathProviderImpl());

        System.out.println(calculator.makeDivision(-127891, -31));
        System.out.println(calculator.makeDivision(127891, -5));
        System.out.println(calculator.makeDivision(-5325291, 1212));
        System.out.println(calculator.makeDivision(5325291, 111));
        System.out.println(calculator.makeDivision(111, 5325291));
    }

}
