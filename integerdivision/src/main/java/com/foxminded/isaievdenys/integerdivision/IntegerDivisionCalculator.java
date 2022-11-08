package com.foxminded.isaievdenys.integerdivision;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionMathProvider;
import com.foxminded.isaievdenys.integerdivision.provider.DivisionViewProvider;
import com.foxminded.isaievdenys.integerdivision.validator.DivisionValidator;

public class IntegerDivisionCalculator {
    DivisionValidator validator;
    DivisionViewProvider viewProvider;
    DivisionMathProvider mathProvider;

    public IntegerDivisionCalculator(DivisionValidator validator, DivisionViewProvider viewProvider, DivisionMathProvider mathProvider) {
        this.validator = validator;
        this.viewProvider = viewProvider;
        this.mathProvider = mathProvider;
    }

    public String makeDivision(Integer dividend, Integer divisor) {
        validator.validate(dividend, divisor);
        DivisionResult divisionResult = mathProvider.provideLongDivision(dividend, divisor);
        return viewProvider.provideView(divisionResult);
    }

}
