package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.domain.DivisionStep;

import java.util.ArrayList;
import java.util.List;

public class DivisionMathProviderImpl implements DivisionMathProvider {

    @Override
    public DivisionResult provideLongDivision(Integer dividend, Integer divisor) {
        return new DivisionResult(calculateSteps(dividend, divisor), dividend, divisor);
    }

    private List<DivisionStep> calculateSteps(Integer dividend, Integer divisor) {
        int absDividend = Math.abs(dividend);
        int absDivisor = Math.abs(divisor);
        List<DivisionStep> steps = new ArrayList<>();
        String[] dividendDigits = Integer.toString(absDividend).split("");
        int nextMinuend = Integer.parseInt(dividendDigits[0]);
        int nextSubtrahend;
        for (int i = 0; i < dividendDigits.length - 1; ) {
            if (nextMinuend < absDivisor) {
                nextMinuend = Integer.parseInt(nextMinuend + dividendDigits[i + 1]);
                i++;
            }
            if (nextMinuend > absDivisor) {
                nextSubtrahend = nextMinuend - (nextMinuend % divisor);
                steps.add(new DivisionStep(nextMinuend, nextSubtrahend));
                nextMinuend = nextMinuend % absDivisor;
            }
        }
        return steps;
    }

}
