package com.foxminded.isaievdenys.integerdivision.domain;

import java.util.List;
import java.util.Objects;

public class DivisionResult {
    private final List<DivisionStep> steps;
    private final Integer dividend;
    private final Integer divisor;

    public DivisionResult(List<DivisionStep> steps, Integer dividend, Integer divisor) {
        this.steps = steps;
        this.dividend = dividend;
        this.divisor = divisor;
    }

    public List<DivisionStep> getSteps() {
        return steps;
    }

    public Integer getDividend() {
        return dividend;
    }

    public Integer getDivisor() {
        return divisor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisionResult that = (DivisionResult) o;
        return Objects.equals(steps, that.steps) && Objects.equals(dividend, that.dividend) && Objects.equals(divisor, that.divisor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(steps, dividend, divisor);
    }

    @Override
    public String toString() {
        return "DivisionResult{" +
            "steps=" + steps +
            ", dividend=" + dividend +
            ", divisor=" + divisor +
            '}';
    }

}
