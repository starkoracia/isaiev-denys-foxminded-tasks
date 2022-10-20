package com.foxminded.isaievdenys.anagrams;

import org.junit.jupiter.api.Test;

import static com.foxminded.isaievdenys.anagrams.AnagramMaker.makeAnagram;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AnagramMakerTest {

    @Test
    void makeAnagram_shouldReverseAllLetterSymbolsInWords_whenGetNonEmptyString() {
        String testString = "te1st @1st5ri--ng";
        String expectedTestString = "ts1et @1gn5ir--ts";
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReverseAllLetterSymbolsInWordsWithOnlyOneSpaceDelimiter_whenGetNonEmptyString() {
        String testString = "te1st         @1st5ri--ng";
        String expectedTestString = "ts1et @1gn5ir--ts";
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReverseAllLetterSymbolsInWords_whenGetOneWordWithDifferentLetters() {
        String testString = "@1st5ri--ng";
        String expectedTestString = "@1gn5ir--ts";
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReturnTheSameString_whenGetStringWithOneCharacter() {
        String testString = "f";
        String expectedTestString = testString;
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReturnTheSameString_whenGetStringWithMultipleSameLetter() {
        String testString = "fffffffffffff";
        String expectedTestString = testString;
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReturnTheSameString_whenGetStringWithOnlySymbols() {
        String testString = "167@4 !%$&";
        String expectedTestString = testString;
        assertEquals(expectedTestString, makeAnagram(testString));
    }

    @Test
    void makeAnagram_shouldReverseAllLetterSymbolsInWords_whenGetTheSameCharacterInLowerAndUpperCasesMixedInTheInput() {
        String testString = "fFffffffffffF";
        String expectedTestString = "FffffffffffFf";
        assertEquals(expectedTestString, makeAnagram(testString));
    }


    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithOnlyTabsAndOrSpacesMessage_whenGetSingleSpace() {
        String singleSpace = " ";
        String expectedExceptionMessage = "sentence contains only tabs and/or spaces";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(singleSpace);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithSentenceIsEmptyMessage_whenGetEmptyString() {
        String emptyString = "";
        String expectedExceptionMessage = "sentence is empty";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(emptyString);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithOnlyTabsAndOrSpacesMessage_whenGetSeveralSpaces() {
        String severalSpaces = "     ";
        String expectedExceptionMessage = "sentence contains only tabs and/or spaces";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(severalSpaces);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithSentenceIsNullMessage_whenGetNull() {
        String nullString = null;
        String expectedExceptionMessage = "sentence is null";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(nullString);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithOnlyTabsAndOrSpacesMessage_whenGetSeveralTabs() {
        String severalSpaces = "\t\t\t";
        String expectedExceptionMessage = "sentence contains only tabs and/or spaces";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(severalSpaces);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

    @Test
    void makeAnagram_shouldThrowIllegalArgumentExceptionWithOnlyTabsAndOrSpacesMessage_whenGetSeveralTabsAndSeveralSpaces() {
        String severalSpaces = "\t  \t  \t ";
        String expectedExceptionMessage = "sentence contains only tabs and/or spaces";
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            makeAnagram(severalSpaces);
        });
        assertEquals(expectedExceptionMessage, exception.getMessage());
    }

}
