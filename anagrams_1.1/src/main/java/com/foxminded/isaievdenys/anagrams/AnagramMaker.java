package com.foxminded.isaievdenys.anagrams;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AnagramMaker {

    private static final String SPASE_DELIMITER = "\\s+";
    private static final String NON_TAB_AND_SPASE_REGEX = "[^\\s\\t]";

    private AnagramMaker() {
    }

    public static String makeAnagram(String sourceString) {
        validateSentence(sourceString);

        String[] words = sourceString.split(SPASE_DELIMITER);
        String[] reversedWords = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            reversedWords[i] = reverseWord(words[i]);
        }
        return concat(reversedWords, sourceString);
    }

    private static void validateSentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("sentence is null");
        }
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("sentence is empty");
        }
        if (isOnlyTabAndSpace(sentence)) {
            throw new IllegalArgumentException("sentence contains only tabs and/or spaces");
        }
    }

    private static boolean isOnlyTabAndSpace(String sentence) {
        Matcher nonTabAndSpaceMatcher = Pattern
                .compile(NON_TAB_AND_SPASE_REGEX)
                .matcher(sentence);
        return !nonTabAndSpaceMatcher.find();
    }

    private static String concat(String[] reversedWords, String sourceString) {
        StringBuilder concatenatedString = new StringBuilder();
        Matcher spaceMatcher = Pattern
                .compile(SPASE_DELIMITER)
                .matcher(sourceString);

        for (String word : reversedWords) {
            concatenatedString.append(word);
            if (spaceMatcher.find()) {
                concatenatedString.append(spaceMatcher.group());
            }
        }
        return concatenatedString.toString();
    }

    private static String reverseWord(String sourceWord) {
        StringBuilder reversedWordBuilder = new StringBuilder(cutOutNonLetterSymbols(sourceWord));
        reversedWordBuilder.reverse();
        insertNonLetterSymbols(reversedWordBuilder, sourceWord);
        return reversedWordBuilder.toString();
    }

    private static void insertNonLetterSymbols(StringBuilder outputStringBuilder, String sourceWord) {
        Matcher nonLetterSymbolsMatcher = Pattern
                .compile("[^a-zA-Z]+")
                .matcher(sourceWord);
        while (nonLetterSymbolsMatcher.find()) {
            outputStringBuilder.insert(nonLetterSymbolsMatcher.start(), nonLetterSymbolsMatcher.group());
        }
    }

    private static String cutOutNonLetterSymbols(String word) {
        return String.join("", word.split("[^a-zA-Z]+"));
    }

}
