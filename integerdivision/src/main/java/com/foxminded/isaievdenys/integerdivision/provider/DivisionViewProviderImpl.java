package com.foxminded.isaievdenys.integerdivision.provider;

import com.foxminded.isaievdenys.integerdivision.domain.DivisionResult;

public class DivisionViewProviderImpl implements DivisionViewProvider {

    @Override
    public String provideView(DivisionResult divisionResult) {
        if (Math.abs(divisionResult.getDividend()) < Math.abs(divisionResult.getDivisor())) {
            return String.format("%d / %d = 0", divisionResult.getDividend(), divisionResult.getDivisor());
        }
        if (Math.abs(divisionResult.getDividend()) == Math.abs(divisionResult.getDivisor())) {
            return String.format("%d / %d = 1", divisionResult.getDividend(), divisionResult.getDivisor());
        }
        Integer quotient = divisionResult.getDividend() / divisionResult.getDivisor();
        Integer remainderOfDivision = Math.abs(divisionResult.getDividend() % divisionResult.getDivisor());
        StringBuilder resultString = new StringBuilder();
        int nextLineSpaces = divisionResult.getDividend() < 0 ? 1 : 0;

        resultString
            .append(String.format("_%d|%d\n", divisionResult.getDividend(), divisionResult.getDivisor()))
            .append(String.format(" %s%d%s|%s\n",
                " ".repeat(nextLineSpaces),
                divisionResult.getSteps().get(0).getSubtrahend(),
                " ".repeat(divisionResult.getDividend().toString().length() - divisionResult.getSteps().get(0).getSubtrahend().toString().length() - nextLineSpaces),
                "-".repeat(quotient.toString().length())))
            .append(String.format(" %s%s%s|%d\n",
                " ".repeat(nextLineSpaces),
                "-".repeat(divisionResult.getSteps().get(0).getSubtrahend().toString().length()),
                " ".repeat(divisionResult.getDividend().toString().length() - divisionResult.getSteps().get(0).getSubtrahend().toString().length() - nextLineSpaces),
                quotient));
        for (int i = 1; i < divisionResult.getSteps().size(); i++) {
            Integer nextReminderOfDivision = divisionResult.getSteps().get(i - 1).getMinuend() % divisionResult.getSteps().get(i - 1).getSubtrahend();
            Integer nextSpacesCorrection = divisionResult.getSteps().get(i - 1).getMinuend().toString().length() - nextReminderOfDivision.toString().length();
            nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
            resultString
                .append(String.format("%s_%d\n",
                    " ".repeat(nextLineSpaces),
                    divisionResult.getSteps().get(i).getMinuend()))
                .append(String.format("%s %d\n",
                    " ".repeat(nextLineSpaces),
                    divisionResult.getSteps().get(i).getSubtrahend()))
                .append(String.format("%s%s\n",
                    " ".repeat(nextLineSpaces + 1),
                    "-".repeat(divisionResult.getSteps().get(i).getMinuend().toString().length())));
        }
        Integer nextSpacesCorrection = divisionResult.getSteps().get(divisionResult.getSteps().size() - 1).getMinuend().toString().length() - remainderOfDivision.toString().length();
        nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
        resultString
            .append(String.format("%s %d\n",
                " ".repeat(nextLineSpaces),
                remainderOfDivision));

        return resultString.toString();
    }

}
