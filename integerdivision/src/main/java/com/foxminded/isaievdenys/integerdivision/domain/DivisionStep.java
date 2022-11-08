package com.foxminded.isaievdenys.integerdivision.domain;

import java.util.Objects;

public class DivisionStep {
    private final Integer minuend;
    private final Integer subtrahend;

    public DivisionStep(Integer minuend, Integer subtrahend) {
        this.minuend = minuend;
        this.subtrahend = subtrahend;
    }

    public Integer getMinuend() {
        return minuend;
    }

    public Integer getSubtrahend() {
        return subtrahend;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DivisionStep that = (DivisionStep) o;
        return Objects.equals(minuend, that.minuend) && Objects.equals(subtrahend, that.subtrahend);
    }

    @Override
    public int hashCode() {
        return Objects.hash(minuend, subtrahend);
    }

    @Override
    public String toString() {
        return "DivisionStep{" +
            "minuend=" + minuend +
            ", subtrahend=" + subtrahend +
            '}';
    }

}
