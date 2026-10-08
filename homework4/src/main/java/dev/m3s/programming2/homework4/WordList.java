package dev.m3s.programming2.homework4;

import java.io.*;
import java.util.*;

class WordList {
    private List<String> words;

    public WordList(String fileName) {
        words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim().toLowerCase());
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error: Could not read the file.");
        }
    }

    public List<String> giveWords() {
        return words;
    }

    public String getRandomWord() {
        if (words.isEmpty()) {
            throw new IllegalStateException("No words available in the file.");
        }
        Random random = new Random();
        return words.get(random.nextInt(words.size()));
    }

    public WordList theWordsOfLength(int length) {
        WordList filteredList = new WordList("");
        for (String word : words) {
            if (word.length() == length) {
                filteredList.words.add(word);
            }
        }
        return filteredList;
    }

    public WordList theWordsWithCharacters(String someString) {
        WordList filteredList = new WordList("");
        String regex = someString.replace("_", ".");
        for (String word : words) {
            if (word.matches(regex)) {
                filteredList.words.add(word);
            }
        }
        return filteredList;
    }
}
