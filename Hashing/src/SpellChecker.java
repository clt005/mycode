/*
 * Name: Clayton Hammen Tan
 * PID: A17819097
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * Spell Checker Implementation
 *
 * @author Clayton Hammen Tan
 * @since A17819097
 */

public class SpellChecker {

    public KeyedSet dictWords;

    /**
     * Reads the dictionary from a given reader and stores the words in a chosen data structure.
     *
     * @param reader       Reader object to read the dictionary file
     * @param useHashTable True if MyHashTable should be used, false for MyBloomFilter
     */
    public void readDictionary(Reader reader, boolean useHashTable) {
        if (useHashTable) {
            dictWords = new MyHashTable();
        } else {
            dictWords = new MyBloomFilter();
        }
        Scanner scanner = new Scanner(reader);
        while (scanner.hasNextLine()) {
            String word = scanner.nextLine().trim().toLowerCase();
            dictWords.insert(word);
        }
    }

    /**
     * Checks for possible correct words by replacing each letter with every letter from
     * 'a' to 'z'.
     *
     * @param word The input word to check
     * @return List of possible correct words by replacing one letter
     */

    private LinkedList<String> checkWrongLetter(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            char oglChar = charArray[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == oglChar) {
                    continue;
                } else {
                    charArray[i] = c;
                    String newWord = new String(charArray);
                    if (dictWords.lookup(newWord)) {
                        possibleWords.add(newWord);
                    }
                }
            }
            charArray[i] = oglChar;
        }
        return possibleWords;
    }

    /**
     * Checks for possible correct words by inserting each letter from
     * 'a' to 'z' at each position.
     *
     * @param word The input word to check
     * @return List of possible correct words by inserting one letter
     */
    private LinkedList<String> checkInsertedLetter(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                String newWord = word.substring(0, i) + c + word.substring(i);
                if (dictWords.lookup(newWord)) {
                    possibleWords.add(newWord);
                }
            }
        }
        return possibleWords;
    }

    /**
     * Checks for possible correct words by deleting each letter one by one.
     *
     * @param word The input word to check
     * @return List of possible correct words by deleting one letter
     */
    private LinkedList<String> checkDeleted(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        for (int i = 0; i < word.length(); i++) {
            String newWord = word.substring(0, i) + word.substring(i + 1);
            if (dictWords.lookup(newWord)) {
                possibleWords.add(newWord);
            }
        }
        return possibleWords;
    }

    /**
     * Checks for possible correct words by swapping each pair of adjacent letters.
     *
     * @param word The input word to check
     * @return List of possible correct words by swapping adjacent letters
     */
    private LinkedList<String> checkTransposedLetter(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        char[] charArray = word.toCharArray();
        for (int i = 0; i < charArray.length - 1; i++) {
            char tempChar = charArray[i];
            charArray[i] = charArray[i + 1];
            charArray[i + 1] = tempChar;
            String newWord = new String(charArray);
            if (dictWords.lookup(newWord)) {
                possibleWords.add(newWord);
            }
            charArray[i + 1] = charArray[i];
            charArray[i] = tempChar;
        }
        return possibleWords;
    }

    /**
     * Checks for possible correct words by inserting a space and checking each resulting part.
     *
     * @param word The input word to check
     * @return List of possible correct words by inserting a space
     */
    private LinkedList<String> checkInsertSpace(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        for (int i = 1; i < word.length(); i++) {
            String firstHalf = word.substring(0, i);
            String secondHalf = word.substring(i);
            if (dictWords.lookup(firstHalf)) {
                possibleWords.add(firstHalf);
            }
            if (dictWords.lookup(secondHalf)) {
                possibleWords.add(secondHalf);
            }
        }
        return possibleWords;
    }

    /**
     * Collects all possible suggestions for a misspelled word.
     *
     * @param word The input word to check
     * @return Array of possible correct words
     */
    private String[] checkWord(String word) {
        LinkedList<String> possibleWords = new LinkedList<>();
        possibleWords.addAll(checkInsertSpace(word));
        possibleWords.addAll(checkTransposedLetter(word));
        possibleWords.addAll(checkDeleted(word));
        possibleWords.addAll(checkInsertedLetter(word));
        possibleWords.addAll(checkWrongLetter(word));
        return possibleWords.toArray(new String[possibleWords.size()]);
    }

    /**
     * Build the dictionary, go through the provided words,
     * find any possible matches if the provided word is misspelled and print.
     * You should use a while loop along with the hasNext() and nextLine() methods for the
     * Scanner class.
     *
     * @param args The following indices of args correspond with these variables:
     * args[0]: Either 0 or 1 (0 to indicate that a MyHashTable should be used and 1 for
     * MyBloomFilter)
     * args[1]: The path to the file containing dictionary words
     * args[2]: The path to the file containing input words to test
     */
    public static void main(String[] args) {
        // args[0]: 0 if we should use a MyHashTable and 1 for a MyBloomFilter
        // args[1]: path to dict file
        // args[2]: path to input file

        SpellChecker checker = new SpellChecker();

        File dictionary = new File(args[1]);
        try {
            Reader reader = new FileReader(dictionary);
            if (args[0].equals("0")) {
                checker.readDictionary(reader, true);
            } else if (args[0].equals("1")) {
                checker.readDictionary(reader, false);
            }
        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + dictionary);
            System.exit(1);
        }

        File inputFile = new File(args[2]);
        try {
            Scanner input = new Scanner(inputFile); // Reads list of words

            while (input.hasNextLine()) {
                String inputWord = input.nextLine().trim().toLowerCase();
                if (checker.dictWords.lookup(inputWord)) {
                    System.out.println(inputWord + ": ok");
                } else {
                    String[] possibleWords = checker.checkWord(inputWord);
                    if (possibleWords.length == 0) {
                        System.out.println(inputWord + ": not found");
                    } else {
                        System.out.println(
                                inputWord + ": " + String.join(" ", possibleWords));
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.err.println("Failed to open " + inputFile);
            System.exit(1);
        }
    }
}
