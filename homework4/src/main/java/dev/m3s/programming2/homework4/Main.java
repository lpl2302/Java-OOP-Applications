package dev.m3s.programming2.homework4;

import java.util.Scanner;

// Main class to run the game
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        WordList words = new WordList("words.txt");
        Hangman hangman = new Hangman(words, 5);

        while (!hangman.theEnd()) {
            System.out.println("\nThe hidden word: " + hangman.getMaskedWord());
            System.out.println("Guesses left: " + hangman.guessesLeft());
            System.out.println("Guessed letters: " + hangman.guesses());
            System.out.print("Guess a letter: ");

            String input = scanner.nextLine();
            if (input.length() == 1 && Character.isLetter(input.charAt(0))) {
                hangman.guess(input.charAt(0));
            } else {
                System.out.println("Invalid input. Please enter a single letter.");
            }
        }

        scanner.close();

        if (hangman.isWin()) {
            System.out.println("Congratulations! You won! The word was: " + hangman.word());
        } else {
            System.out.println("Game over! The word was: " + hangman.word());
        }
    }
}