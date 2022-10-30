package com.foxminded.isaievdenys.integerdivision;

public class AppTester {

    private AppTester() {
    }

    public static void runTest() {
        IntegerDivider.makeDivision(-127891, -31);
        IntegerDivider.makeDivision(127891, -5);
        IntegerDivider.makeDivision(-5325291, 1212);
        IntegerDivider.makeDivision(5325291, 111);
        IntegerDivider.makeDivision(111, 5325291);
    }

}
