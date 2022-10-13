package com.foxminded.isaievdenys.anagrams;

import static java.lang.Character.isLetter;

public class AnagramMaker {

    private static final String SPACES_REGEX_DELIMITER = "\\s+";
    private static final String SPACE_DELIMITER = " ";

    private AnagramMaker() {
    }

    public static String makeAnagram(String sourceString) {
        validateSentence(sourceString);

        String[] words = sourceString.split(SPACES_REGEX_DELIMITER);
        String[] reversedWords = new String[words.length];

        for (int i = 0; i < words.length; i++) {
            reversedWords[i] = reverseWord(words[i]);
        }
        return concat(reversedWords);
    }

    private static void validateSentence(String sentence) {
        if (sentence == null) {
            throw new IllegalArgumentException("sentence is null");
        }
        if (sentence.isEmpty()) {
            throw new IllegalArgumentException("sentence is empty");
        }
        if (sentence.trim().isEmpty()) {
            throw new IllegalArgumentException("sentence contains only tabs and/or spaces");
        }
    }

    private static String concat(String[] words) {
        return String.join(SPACE_DELIMITER, words);
    }

    private static String reverseWord(String sourceWord) {
        char[] chars = sourceWord.toCharArray();
        int startIndex = 0;
        int endIndex = sourceWord.length() - 1;

        while (startIndex < endIndex) {
            if (isLetter(chars[startIndex]) && isLetter(chars[endIndex])) {
                char tempChar = chars[startIndex];
                chars[startIndex] = chars[endIndex];
                chars[endIndex] = tempChar;
                startIndex++;
                endIndex--;
            }
            if (!isLetter(chars[startIndex])) {
                startIndex++;
            }
            if (!isLetter(chars[endIndex])) {
                endIndex--;
            }
        }
        return new String(chars);
    }

}
