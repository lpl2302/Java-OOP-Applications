package dev.m3s.programming2.homework4;

import java.io.*;
import java.util.*;

class Hangman {
    private String wordToGuess;
    private int guessChanceLeft;
    private List<Character> guesses;

    public Hangman(WordList wordList, int maxGuesses) {
        wordToGuess = wordList.getRandomWord().toLowerCase();
        guessChanceLeft = maxGuesses;
        guesses = new ArrayList<>();
    }

    public String getMaskedWord() {
        StringBuilder maskedWord = new StringBuilder();
        for (char letter : wordToGuess.toCharArray()) {
            if (guesses.contains(letter)) {
                maskedWord.append(letter);
            } else {
                maskedWord.append('*');
            }
        }
        return maskedWord.toString();
    }

    public boolean guess(Character c) {
        c = Character.toLowerCase(c);
        if (!guesses.contains(c)) {
            guesses.add(c);
            if (!wordToGuess.contains(String.valueOf(c))) {
                guessChanceLeft = Math.max(guessChanceLeft - 1, 0);
                return false;
            }
        }
        return wordToGuess.contains(String.valueOf(c));
    }

    public List<Character> guesses() {
        return guesses;
    }

    public int guessesLeft() {
        return guessChanceLeft;
    }

    public boolean theEnd() {
        return guessChanceLeft <= 0 || isWin();
    }

    public boolean isWin() {
        for (char letter : wordToGuess.toCharArray()) {
            if (!guesses.contains(letter)) {
                return false;
            }
        }
        return true;
    }

    public String word() {
        return wordToGuess;
    }
}