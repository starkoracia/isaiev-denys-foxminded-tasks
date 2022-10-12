package com.foxminded.isaievdenys.anagrams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramMaker {

    private AnagramMaker() {
    }

    public static String makeAnagram(String sourceString) {
        StringBuilder outputString = new StringBuilder();
        String[] words = sourceString.split("\\s+");

        Matcher spaceMatcher = Pattern.compile("\\s+").matcher(sourceString);
        for (String word : words) {
            outputString.append(reverseWordExceptNonLetterSymbols(word));
            if (spaceMatcher.find()) {
                outputString.append(spaceMatcher.group());
            }
        }
        return outputString.toString();
    }

    private static String reverseWordExceptNonLetterSymbols(String sourceWord) {
        StringBuilder outputWord = new StringBuilder(cutOutNonLetterSymbols(sourceWord));
        outputWord.reverse();
        insertNonLetterSymbols(outputWord, sourceWord);
        return outputWord.toString();
    }

    private static void insertNonLetterSymbols(StringBuilder outputStringBuilder, String sourceWord) {
        Matcher nonLetterSymbolsMatcher = Pattern.compile("[^a-zA-Z]+").matcher(sourceWord);
        while (nonLetterSymbolsMatcher.find()) {
            outputStringBuilder.insert(nonLetterSymbolsMatcher.start(), nonLetterSymbolsMatcher.group());
        }
    }

    private static String cutOutNonLetterSymbols(String word) {
        return String.join("", word.split("[^a-zA-Z]+"));
    }

}
