package com.foxminded.isaievdenys.anagrams;

public class AppTester {

    private AppTester() {
    }

    public static void runTest() {
        String testString = "@n@gr@ms t3st str!ng";
        System.out.printf("Test string 1: %s\n", testString);
        System.out.printf("Reversed test string 1: %s\n\n", AnagramMaker.makeAnagram(testString));

        String testString2 = "th!s !s the w@y";
        System.out.printf("Test string 2: %s\n", testString2);
        System.out.printf("Reversed test string 2: %s\n\n", AnagramMaker.makeAnagram(testString2));
    }

}
