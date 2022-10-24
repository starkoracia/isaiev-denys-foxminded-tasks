package com.foxminded.isaievdenys.anagrams;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTesterTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    void runTest_shouldPrintTestStringsInConsole_whenCalls() {
        String expected =
            """
                Test string 1: @n@gr@ms t3st str!ng
                Reversed test string 1: @s@mr@gn t3st gnr!ts

                Test string 2: th!s !s the w@1y
                Reversed test string 2: sh!t !s eht y@1w

                """;
        AppTester.runTest();
        assertEquals(expected, outContent.toString());
    }

}
