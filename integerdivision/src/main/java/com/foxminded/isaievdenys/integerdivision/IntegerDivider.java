package com.foxminded.isaievdenys.integerdivision;

import java.util.ArrayList;
import java.util.List;

public class IntegerDivider {

    private IntegerDivider() {
    }

    public static void makeDivision(Integer dividend, Integer divisor) {
        if(dividend == null) {
            throw new IllegalArgumentException("dividend is null");
        }
        if(divisor == null) {
            throw new IllegalArgumentException("divisor is null");
        }
        if (divisor == 0) {
            throw new IllegalArgumentException("divisor can not be 0");
        }
        List<Integer> minuends = calculateMinuends(dividend, divisor);
        List<Integer> subtrahends = calculateSubtrahends(minuends, divisor);
        String divisionResultString = makeDivisionResultString(dividend, divisor, minuends, subtrahends);
        System.out.println(divisionResultString);
    }

    private static String makeDivisionResultString(Integer dividend, Integer divisor, List<Integer> minuends, List<Integer> subtrahends) {
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
                subtrahends.get(0),
                " ".repeat(dividend.toString().length() - subtrahends.get(0).toString().length() - nextLineSpaces),
                "-".repeat(quotient.toString().length())))
            .append(String.format(" %s%s%s|%d\n",
                " ".repeat(nextLineSpaces),
                "-".repeat(subtrahends.get(0).toString().length()),
                " ".repeat(dividend.toString().length() - subtrahends.get(0).toString().length() - nextLineSpaces),
                quotient));
        for (int i = 1; i < subtrahends.size(); i++) {
            Integer nextReminderOfDivision = minuends.get(i - 1) % subtrahends.get(i - 1);
            Integer nextSpacesCorrection = minuends.get(i - 1).toString().length() - nextReminderOfDivision.toString().length();
            nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
            resultString
                .append(String.format("%s_%d\n",
                    " ".repeat(nextLineSpaces),
                    minuends.get(i)))
                .append(String.format("%s %d\n",
                    " ".repeat(nextLineSpaces),
                    subtrahends.get(i)))
                .append(String.format("%s%s\n",
                    " ".repeat(nextLineSpaces + 1),
                    "-".repeat(minuends.get(i).toString().length())));
        }
        Integer nextSpacesCorrection = minuends.get(minuends.size() - 1).toString().length() - remainderOfDivision.toString().length();
        nextLineSpaces = nextLineSpaces + nextSpacesCorrection;
        resultString
            .append(String.format("%s %d\n",
                " ".repeat(nextLineSpaces),
                remainderOfDivision));

        return resultString.toString();
    }

    private static List<Integer> calculateSubtrahends(List<Integer> minuends, Integer divisor) {
        List<Integer> subtrahends = new ArrayList<>();
        for (Integer minuend : minuends) {
            int remainderOfDivision = minuend % divisor;
            subtrahends.add(minuend - remainderOfDivision);
        }
        return subtrahends;
    }

    private static List<Integer> calculateMinuends(Integer dividend, Integer divisor) {
        Integer absDividend = Math.abs(dividend);
        Integer absDivisor = Math.abs(divisor);
        List<Integer> minuends = new ArrayList<>();
        String[] dividendDigits = absDividend.toString().split("");
        Integer nextMinuend = Integer.parseInt(dividendDigits[0]);
        for (int i = 0; i < dividendDigits.length - 1; ) {
            if (nextMinuend < absDivisor) {
                nextMinuend = Integer.parseInt(nextMinuend + dividendDigits[i + 1]);
                i++;
            }
            if (nextMinuend > absDivisor) {
                minuends.add(nextMinuend);
                nextMinuend = nextMinuend % absDivisor;
            }
        }
        return minuends;
    }

}
