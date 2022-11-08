package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;
import com.foxminded.isaievdenys.integerdivision.domain.DivisionStep;

import java.util.List;

public class DivisionViewProviderImpl implements DivisionViewProvider {

    @Override
    public String provideView(DivisionResult divisionResult) {
        return makeViewString(divisionResult.getDividend(), divisionResult.getDivisor(), divisionResult.getSteps());
    }

    private String makeViewString(Integer dividend, Integer divisor, List<DivisionStep> steps) {
        if (Math.abs(dividend) < Math.abs(divisor)) {
            return String.format("%d / %d = 0", dividend, divisor);
        }
        Integer quotient = dividend / divisor;
        Integer remainderOfDivision = Math.abs(dividend % divisor);
        StringBuilder resultString = new StringBuilder();
        int nextLineSpaces = dividend < 0 ? 1 : 0;

        resultString
            .append(String.format("_%d|%d\n", dividend, divisor))
            .append(String.format(" %s%d%s|%s\n",
                " ".repeat(nextLineSpaces),
                steps.get(0).getSubtrahend(),
                " ".repeat(dividend.toString().length() - steps.get(0).getSubtrahend().toString().length() - nextLineSpaces),
                "-".repeat(quotient.toString().length())))
            .append(String.format(" %s%s%s|%d\n",
                " ".repeat(nextLineSpaces),
                "-".repeat(steps.get(0).getSubtrahend().toString().length()),
                " ".repeat(dividend.toString().length() - steps.get(0).getSubtrahend().toString().length() - nextLineSpaces),
                quotient));
        for (int i = 1; i < steps.size(); i++) {
            Integer nextReminderOfDivision = steps.get(i - 1).getMinuend() % steps.get(i - 1).getSubtrahend();
            Integer nextSpacesCorrection = steps.get(i - 1).getMinuend().toString().length() - nextReminderOfDivision.toString().length();
            nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
            resultString
                .append(String.format("%s_%d\n",
                    " ".repeat(nextLineSpaces),
                    steps.get(i).getMinuend()))
                .append(String.format("%s %d\n",
                    " ".repeat(nextLineSpaces),
                    steps.get(i).getSubtrahend()))
                .append(String.format("%s%s\n",
                    " ".repeat(nextLineSpaces + 1),
                    "-".repeat(steps.get(i).getMinuend().toString().length())));
        }
        Integer nextSpacesCorrection = steps.get(steps.size() - 1).getMinuend().toString().length() - remainderOfDivision.toString().length();
        nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
        resultString
            .append(String.format("%s %d\n",
                " ".repeat(nextLineSpaces),
                remainderOfDivision));

        return resultString.toString();
    }

}
