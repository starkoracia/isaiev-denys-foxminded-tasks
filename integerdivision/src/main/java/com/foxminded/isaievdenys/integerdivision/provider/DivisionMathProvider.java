package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;

public interface DivisionMathProvider {
    DivisionResult provideLongDivision(Integer dividend, Integer divisor);

}
